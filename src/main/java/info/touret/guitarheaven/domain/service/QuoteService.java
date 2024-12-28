package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.port.QuotePort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Manage Quotes
 */
public class QuoteService {

    public static final int SUPPLY_CHAIN_THRESHOLD = 5;
    private final GuitarService guitarService;
    private final DiscountService discountService;
    private final QuotePort quotePort;
    private final SupplyChainPort supplyChainPort;
    private final OrderRequestService orderRequestService;

    /**
     * Constructor. All of these fields are normally injected by CDI.
     * @param guitarService
     * @param discountService
     * @param quotePort
     * @param supplyChainPort
     * @param orderRequestService
     */
    public QuoteService(GuitarService guitarService, DiscountService discountService, QuotePort quotePort, SupplyChainPort supplyChainPort, OrderRequestService orderRequestService) {
        this.guitarService = guitarService;
        this.discountService = discountService;
        this.quotePort = quotePort;
        this.supplyChainPort = supplyChainPort;
        this.orderRequestService = orderRequestService;
    }

    /**
     * Finds all the quotes
     *
     * @return The list of quotes
     */
    public List<Quote> findAll() {
        return quotePort.getQuotes();
    }

    /**
     * Creates a quote. Before saving it, it calculates the discount comparing to the priceInUSD of the market and the order request. Then, if needed, it broadcasts to a Supply Chain Backoffice, the commands for new furniture.
     *
     * @param quote The quote to save
     * @return The UUID of the new quote
     */
    public UUID createQuote(Quote quote) {
        OrderRequest orderRequest = orderRequestService.findByUUID(quote.orderId()).orElseThrow(() -> new EntityNotFoundException("Invalid Order:" + quote.orderId()));
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());
        if(relatedGuitars==null || relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Quote creation error -> Invalid Order:" + quote.orderId());
        }
        double totalPrice = relatedGuitars.stream().mapToDouble(Guitar::priceInUSD).sum();
        // if the requested discount is below the market, we only apply it
        double discount = discountService.getTotalDiscount(relatedGuitars);
        if (discount > orderRequest.discountRequested()) {
            discount = orderRequest.discountRequested();
        }
        // ask for suppliers for furniture
        relatedGuitars.forEach(this::checkAndSupplyForNewFurniture);
        Quote quoteToCreate = new Quote(UUID.randomUUID(), orderRequest.orderId(), discount, totalPrice - discount, OffsetDateTime.now());
        quotePort.saveQuote(quoteToCreate);
        return quoteToCreate.quoteId();
    }

    private void checkAndSupplyForNewFurniture(Guitar guitar) {
        if (guitar.stock() < SUPPLY_CHAIN_THRESHOLD) {
            supplyChainPort.requestForAdditionalGuitars(guitar.name(), SUPPLY_CHAIN_THRESHOLD - guitar.stock());
        }
    }
}

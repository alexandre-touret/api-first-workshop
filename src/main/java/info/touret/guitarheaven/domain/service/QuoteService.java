package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.port.QuotePort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteService.class);

    /**
     * Constructor. All of these fields are normally injected by CDI.
     *
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
     * Creates a quote. Before saving it, it calculates the discountInUSD comparing to the priceInUSD of the market and the order request. Then, if needed, it broadcasts to a Supply Chain Backoffice, the commands for new furniture.
     *
     * @param quote The quote to save
     * @return The UUID of the new quote
     */
    public UUID createQuote(Quote quote) {
        LOGGER.info("Creating quote for order {}", quote.orderId());
        OrderRequest orderRequest = orderRequestService.findByUUID(quote.orderId()).orElseThrow(() -> new EntityNotFoundException("Invalid Order:" + quote.orderId()));
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());
        if (relatedGuitars == null || relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Quote creation error: The order {} is  invalid" + quote.orderId());
        }
        double totalPriceInUSD = relatedGuitars.stream().mapToDouble(Guitar::priceInUSD).sum();
        // if the requested discountInUSD is below the market, we only apply it
        double discountInUSD = discountService.getTotalDiscount(relatedGuitars);
        if (discountInUSD > orderRequest.discountRequestedInUSD()) {
            discountInUSD = orderRequest.discountRequestedInUSD();
        }
        LOGGER.info("Discount calculated for order {}: USD {} ", quote.orderId(), discountInUSD);
        // ask for suppliers for furniture
        relatedGuitars.forEach(this::checkAndSupplyForNewFurniture);
        Quote quoteToCreate = new Quote(UUID.randomUUID(), orderRequest.orderId(), discountInUSD, totalPriceInUSD - discountInUSD, OffsetDateTime.now());
        LOGGER.info("Saving quote {}:", quote.quoteId());
        quotePort.saveQuote(quoteToCreate);
        return quoteToCreate.quoteId();
    }

    private void checkAndSupplyForNewFurniture(Guitar guitar) {
        if (guitar.stock() < SUPPLY_CHAIN_THRESHOLD) {
            LOGGER.info("Requesting for more guitars: {}", guitar);
            supplyChainPort.requestForAdditionalGuitars(guitar.name(), SUPPLY_CHAIN_THRESHOLD - guitar.stock());
        }
    }
}

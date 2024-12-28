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

public class QuoteService {

    public static final int SUPPLY_CHAIN_THRESHOLD = 5;
    private final GuitarService guitarService;
    private final DiscountService discountService;
    private final QuotePort quotePort;
    private final SupplyChainPort supplyChainPort;
    private final OrderService orderService;

    public QuoteService(GuitarService guitarService, DiscountService discountService, QuotePort quotePort, SupplyChainPort supplyChainPort, OrderService orderService) {
        this.guitarService = guitarService;
        this.discountService = discountService;
        this.quotePort = quotePort;
        this.supplyChainPort = supplyChainPort;
        this.orderService = orderService;
    }

    public List<Quote> findAll() {
        return quotePort.getQuotes();
    }

    public UUID createQuote(Quote quote) {
        OrderRequest orderRequest = orderService.findById(quote.orderId()).orElseThrow(() -> new EntityNotFoundException("Invalid Order:" + quote.orderId()));
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());

        double totalPrice = relatedGuitars.stream().mapToDouble(Guitar::price).sum();
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

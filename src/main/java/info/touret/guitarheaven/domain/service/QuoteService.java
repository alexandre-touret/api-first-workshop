package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
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

    public QuoteService(GuitarService guitarService, DiscountService discountService, QuotePort quotePort, SupplyChainPort supplyChainPort) {
        this.guitarService = guitarService;
        this.discountService = discountService;
        this.quotePort = quotePort;
        this.supplyChainPort = supplyChainPort;
    }

    public UUID createQuote(Quote quote) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByIds(quote.order().guitars().stream().mapToLong(Guitar::id).boxed().toList());

        double totalPrice = relatedGuitars.stream().mapToDouble(Guitar::price).sum();
        // if the requested discount is below the market, we only apply it
        double discount = discountService.getTotalDiscount(relatedGuitars);
        if (discount > quote.order().discountRequested()) {
            discount = quote.order().discountRequested();
        }
        // ask for suppliers for furniture
        relatedGuitars.forEach(this::checkAndSupplyForNewFurniture);
        Quote quoteToCreate = new Quote(UUID.randomUUID(), quote.order(), discount, totalPrice - discount, OffsetDateTime.now());
        quotePort.saveQuote(quoteToCreate);
        return quoteToCreate.quoteId();
    }

    private void checkAndSupplyForNewFurniture(Guitar guitar) {
        if (guitar.stock() < SUPPLY_CHAIN_THRESHOLD) {
            supplyChainPort.requestForAdditionalGuitars(guitar.name(), SUPPLY_CHAIN_THRESHOLD - guitar.stock());
        }
    }
}

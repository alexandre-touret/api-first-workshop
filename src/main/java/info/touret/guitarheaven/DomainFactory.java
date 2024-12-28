package info.touret.guitarheaven;

import info.touret.guitarheaven.domain.port.*;
import info.touret.guitarheaven.domain.service.DiscountService;
import info.touret.guitarheaven.domain.service.GuitarService;
import info.touret.guitarheaven.domain.service.OrderRequestService;
import info.touret.guitarheaven.domain.service.QuoteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

/**
 * Factory for domain classes
 */
@ApplicationScoped
public class DomainFactory {

    @ApplicationScoped
    @Produces
    GuitarService createGuitarService(GuitarPort guitarPort) {
        return new GuitarService(guitarPort);
    }

    @ApplicationScoped
    @Produces
    DiscountService createDiscountService(SupplierCatalogPort supplierCatalogPort) {
        return new DiscountService(supplierCatalogPort);
    }

    @ApplicationScoped
    @Produces
    OrderRequestService createOrderService(GuitarService guitarService, OrderRequestPort orderRequestPort) {
        return new OrderRequestService(guitarService, orderRequestPort);
    }

    @ApplicationScoped
    @Produces
    QuoteService createQuoteService(GuitarService guitarService, QuotePort quotePort, DiscountService discountService, SupplyChainPort supplyChainPort, OrderRequestService orderRequestService) {
        return new QuoteService(guitarService, discountService, quotePort, supplyChainPort, orderRequestService);
    }
}

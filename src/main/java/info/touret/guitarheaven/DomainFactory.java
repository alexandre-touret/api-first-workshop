package info.touret.guitarheaven;

import info.touret.guitarheaven.domain.service.DiscountService;
import info.touret.guitarheaven.domain.port.GuitarPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import info.touret.guitarheaven.domain.port.SupplierCatalogPort;
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
}

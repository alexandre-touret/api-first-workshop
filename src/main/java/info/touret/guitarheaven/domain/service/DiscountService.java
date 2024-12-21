package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DiscountService {

    private final SupplierCatalogPort supplierCatalogPort;

    @Inject
    public DiscountService(SupplierCatalogPort supplierCatalogPort) {
        this.supplierCatalogPort = supplierCatalogPort;
    }

    public Double getDiscount(Guitar guitar) {
        // If there is no price stored, we assume the average price is the same as the value stored in our system
        Double averagePrice = supplierCatalogPort.getAverageGuitarPrice(guitar.name()).orElse(guitar.price());
        double difference = guitar.price() - averagePrice;
        if (difference <= 0) {
            return 0.0;
        } else {
            return difference * 0.5;
        }
    }
}

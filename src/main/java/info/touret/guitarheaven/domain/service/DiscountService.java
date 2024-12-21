package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.port.SupplierCatalogPort;

import java.util.List;

public class DiscountService {

    private final SupplierCatalogPort supplierCatalogPort;

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

    public Double getTotalDiscount(List<Guitar> guitars) {
        return guitars.stream().mapToDouble(this::getDiscount).sum();
    }

}

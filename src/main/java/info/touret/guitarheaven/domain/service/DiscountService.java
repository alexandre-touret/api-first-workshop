package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.port.SupplierCatalogPort;

import java.util.List;

/**
 * Uses to calculate the discount for a order request.
 */
public class DiscountService {

    private final SupplierCatalogPort supplierCatalogPort;

    /**
     * @param supplierCatalogPort The SupplierCatalogPort
     */
    public DiscountService(SupplierCatalogPort supplierCatalogPort) {
        this.supplierCatalogPort = supplierCatalogPort;
    }

    /**
     * Calculate the discount fetching data from Ebay. After getting an average of the priceInUSD of the market, we compare it with the internal priceInUSD and propose a discount in the middle if needed.
     * @param guitar The guitar to evaluate 
     * @return The potential discount  
     * @see SupplierCatalogPort#getAverageGuitarPrice(String)
     */
    public Double getDiscount(Guitar guitar) {
        // If there is no priceInUSD stored, we assume the average priceInUSD is the same as the value stored in our system
        Double averagePrice = supplierCatalogPort.getAverageGuitarPrice(guitar.name()).orElse(guitar.priceInUSD());
        double difference = guitar.priceInUSD() - averagePrice;
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

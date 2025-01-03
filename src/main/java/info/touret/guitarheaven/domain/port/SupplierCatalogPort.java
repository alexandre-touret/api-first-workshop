package info.touret.guitarheaven.domain.port;

import java.util.OptionalDouble;

/**
 * Specifies the interactions with the supplier catalog
 */
public interface SupplierCatalogPort {

    /**
     * @param guitarName The name of the guitar to be searched
     * @return The average priceInUSD if corresponding guitars exist, <code>OptionalDouble.empty()</code>
     */
    OptionalDouble getAverageGuitarPrice(String guitarName);
}

package info.touret.guitarheaven.infrastructure.ebay;


import info.touret.guitarheaven.domain.service.SupplierCatalogPort;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.OptionalDouble;

@ApplicationScoped
public class EbayDiscounterAdapter implements SupplierCatalogPort {

    @RestClient
    private EbayClient ebayClient;

    @Override
    public OptionalDouble getAverageGuitarPrice(String guitarName) {

        var searchPagedCollection = ebayClient.searchByName(guitarName);
        if (searchPagedCollection.total() > 1) {
            return searchPagedCollection.itemSummaries()
                    .stream()
                    .mapToDouble(value -> value.price().value())
                    .average();
        } else return OptionalDouble.empty();
    }
}

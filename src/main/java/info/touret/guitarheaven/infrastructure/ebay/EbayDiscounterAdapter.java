package info.touret.guitarheaven.infrastructure.ebay;


import info.touret.guitarheaven.domain.port.SupplierCatalogPort;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.OptionalDouble;

@ApplicationScoped
public class EbayDiscounterAdapter implements SupplierCatalogPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayDiscounterAdapter.class);
    public static final int SEARCH_THRESHOLD = 1;
    @RestClient
    private EbayClient ebayClient;

    @Override
    public OptionalDouble getAverageGuitarPrice(String guitarName) {
        LOGGER.info("Searching Price on EBay for guitar {}", guitarName);
        var searchPagedCollection = ebayClient.searchByName(guitarName);

        if (searchPagedCollection.total() > SEARCH_THRESHOLD) {
            return searchPagedCollection.itemSummaries()
                    .stream()
                    .mapToDouble(value -> value.price().value())
                    .average();
        } else return OptionalDouble.empty();
    }
}

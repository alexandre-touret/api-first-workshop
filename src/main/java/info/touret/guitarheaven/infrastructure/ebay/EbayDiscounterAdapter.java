package info.touret.guitarheaven.infrastructure.ebay;


import info.touret.guitarheaven.domain.port.SupplierCatalogPort;
import info.touret.guitarheaven.infrastructure.ebay.api.ItemSummaryApi;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.OptionalDouble;

@ApplicationScoped
public class EbayDiscounterAdapter implements SupplierCatalogPort {

    public static final int SEARCH_THRESHOLD = 1;
    @RestClient
    private ItemSummaryApi ebayClient;

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == 400) {
            return new RuntimeException("The remote service responded with HTTP 400");
        }
        // Disabling some issues with the EBAY Mock
        return null;
    }

    @Override
    public OptionalDouble getAverageGuitarPrice(String guitarName) {

        var searchPagedCollection = ebayClient.search(guitarName, "foo", "foo", "foo");

        if (searchPagedCollection.getTotal() > SEARCH_THRESHOLD) {
            return searchPagedCollection.getItemSummaries()
                    .stream()
                    .mapToDouble(value -> value.getPrice().getValue().doubleValue())
                    .average();
        } else return OptionalDouble.empty();
    }
}

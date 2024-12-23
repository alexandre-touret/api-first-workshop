package info.touret.guitarheaven.infrastructure.ebay;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

/**
 * EBAY API REST Client (strongly simplified)
 */
@RegisterRestClient
@Path("/item_summary/search")
public interface EbayClient {

    @GET
    SearchPagedCollection searchByName(@RestQuery("q") String query);

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == 400) {
            return new RuntimeException("The remote service responded with HTTP 400");
        }
        return null;
    }
}

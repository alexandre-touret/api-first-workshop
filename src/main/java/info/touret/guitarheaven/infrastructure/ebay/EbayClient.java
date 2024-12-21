package info.touret.guitarheaven.infrastructure.ebay;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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

}

package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.dto.QuoteDto;
import info.touret.guitarheaven.application.mapper.QuoteMapper;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.service.QuoteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
@Path("/quotes")
public class QuoteResource {


    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @Inject
    public QuoteResource(QuoteService quoteService, QuoteMapper quoteMapper) {
        this.quoteService = quoteService;
        this.quoteMapper = quoteMapper;
    }

    @Operation(summary = "Creates a quote")
    @APIResponse(responseCode = "201", description = "Success ")
    @APIResponse(responseCode = "400", description = "Request invalid ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @ResponseStatus(201)
    @POST
    public Map<String, UUID> createQuote(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = QuoteDto.class))) QuoteDto quoteDto) {
        return Map.of("quoteId", quoteService.createQuote(quoteMapper.fromDto(quoteDto)));
    }

    @Operation(summary = "Gets all quotes")
    @APIResponse(responseCode = "200", description = "Success ")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @GET
    public List<Quote> findAll() {
        return quoteService.findAll();
    }
}

package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.generated.model.*;
import info.touret.guitarheaven.application.generated.resource.QuotesApi;
import info.touret.guitarheaven.application.mapper.QuoteMapper;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.service.QuoteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
public class QuoteResource implements QuotesApi{


    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @Inject
    public QuoteResource(QuoteService quoteService, QuoteMapper quoteMapper) {
        this.quoteService = quoteService;
        this.quoteMapper = quoteMapper;
    }


    @Override
    public Response createQuote(QuoteDto quoteDto) {
        return Response.status(201).entity(Map.of("quoteId", quoteService.createQuote(quoteMapper.fromDto(quoteDto)))).build();
    }

    @Override
    public Response findAll() {
        return Response.ok(quoteService.findAll()).build();
    }
}

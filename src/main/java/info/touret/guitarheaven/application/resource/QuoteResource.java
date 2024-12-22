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
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

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

    @ResponseStatus(201)
    @POST
    public void createQuote(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = QuoteDto.class))) QuoteDto quoteDto) {
        quoteService.createQuote(quoteMapper.fromDto(quoteDto));
    }

    @GET
    public List<Quote> findAll() {
        return quoteService.findAll();
    }
}

package info.touret.guitarheaven.application.resource;

import info.touret.guitarheaven.application.generated.model.QuoteDto;
import info.touret.guitarheaven.application.generated.resource.QuotesApi;
import info.touret.guitarheaven.application.mapper.QuoteMapper;
import info.touret.guitarheaven.domain.service.QuoteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@ApplicationScoped
public class QuoteResource implements QuotesApi {


    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @Inject
    public QuoteResource(QuoteService quoteService, QuoteMapper quoteMapper) {
        this.quoteService = quoteService;
        this.quoteMapper = quoteMapper;
    }


    @Override
    public Response createQuote(QuoteDto quoteDto) {
        return Response.ok(Map.of("quoteId", quoteService.createQuote(quoteMapper.fromDto(quoteDto)))).build();
    }

    @Override
    public Response findAll() {
        return Response.ok(quoteService.findAll()).build();
    }
}

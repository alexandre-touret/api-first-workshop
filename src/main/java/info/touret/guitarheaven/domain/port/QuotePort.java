package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Quote;

import java.util.List;


public interface QuotePort {
    void saveQuote(Quote quote);

    List<Quote> getQuotes();
}

package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Quote;


public interface QuotePort {
    void saveQuote(Quote quote);
}

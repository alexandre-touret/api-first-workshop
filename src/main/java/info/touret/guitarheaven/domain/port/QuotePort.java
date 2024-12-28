package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.Quote;

import java.util.List;


/**
 * Port for Quote infrastructure
 */
public interface QuotePort {
    /**
     * @param quote The quote to save
     */
    void saveQuote(Quote quote);

    /**
     * @return The list of quotes
     */
    List<Quote> getQuotes();
}

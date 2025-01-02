package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.port.QuotePort;
import info.touret.guitarheaven.infrastructure.database.mapper.QuoteEntityMapper;
import info.touret.guitarheaven.infrastructure.database.repository.OrderRepository;
import info.touret.guitarheaven.infrastructure.database.repository.QuoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class QuoteDBAdapter implements QuotePort {

    private final QuoteRepository quoteRepository;
    private final QuoteEntityMapper quoteEntityMapper;
    private final OrderRepository orderRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteDBAdapter.class);

    @Inject
    public QuoteDBAdapter(QuoteRepository quoteRepository, QuoteEntityMapper quoteEntityMapper, OrderRepository orderRepository) {
        this.quoteRepository = quoteRepository;
        this.quoteEntityMapper = quoteEntityMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public void saveQuote(Quote quote) {
        LOGGER.info("Saving Quote {}", quote.quoteId());
        var quoteEntity = quoteEntityMapper.toQuoteEntity(quote);
        var orderEntity = orderRepository.findByUUID(quote.orderId()).orElseThrow(() -> new EntityNotFoundException("Invalid Quote Order :" + quote.orderId()));
        LOGGER.info("Found related Order {}", orderEntity.getOrderId());
        quoteEntity.setOrder(orderEntity);
        quoteRepository.persist(quoteEntity);
    }

    @Override
    public List<Quote> getQuotes() {
        return quoteEntityMapper.toQuotes(quoteRepository.findAll().list());
    }
}

package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.infrastructure.database.adapter.QuoteDBAdapter;
import info.touret.guitarheaven.infrastructure.database.repository.QuoteRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static info.touret.guitarheaven.domain.model.Guitar.TYPE.ELECTRIC;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QuoteDBAdapterTest {

    @Inject
    QuoteDBAdapter quoteDBAdapter;

    @Inject
    QuoteRepository quoteRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_create_a_quote_successfully() {
        Order order = new Order(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), List.of(new Guitar(999L, "ES 335", ELECTRIC, 2500d, 10)), 10, OffsetDateTime.now());
        Quote quote = new Quote(UUID.randomUUID(), order, 0D, 2500d, OffsetDateTime.now());
        assertDoesNotThrow(() -> quoteDBAdapter.saveQuote(quote));
        assertEquals(1, quoteRepository.listAll().size());
    }

    @Test
    void should_throw_a_GOE() {
        Order order = new Order(UUID.fromString("392a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), List.of(new Guitar(999L, "ES 335", ELECTRIC, 2500d, 10)), 10, OffsetDateTime.now());
        Quote quote = new Quote(UUID.randomUUID(), order, 0D, 2500d, OffsetDateTime.now());
        assertThrows(GuitarOrderException.class, () -> quoteDBAdapter.saveQuote(quote));
    }

}

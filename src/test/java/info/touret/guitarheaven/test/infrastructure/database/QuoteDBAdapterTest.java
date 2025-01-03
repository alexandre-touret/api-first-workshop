package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.infrastructure.database.adapter.QuoteDBAdapter;
import info.touret.guitarheaven.infrastructure.database.repository.QuoteRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        Quote quote = new Quote(UUID.randomUUID(), UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), 0D, 2500d, OffsetDateTime.now());
        assertDoesNotThrow(() -> quoteDBAdapter.saveQuote(quote));
    }

    @Test
    void should_throw_a_GOE() {
        Quote quote = new Quote(UUID.randomUUID(), UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), 0D, 2500d, OffsetDateTime.now());
        assertThrows(EntityNotFoundException.class, () -> quoteDBAdapter.saveQuote(quote));
    }

    @Test
    void should_get_list() {
        assertFalse(quoteDBAdapter.getQuotes().isEmpty());
    }
}

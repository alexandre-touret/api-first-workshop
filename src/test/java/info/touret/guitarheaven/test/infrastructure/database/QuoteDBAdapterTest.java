package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Order;
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
import java.util.List;
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
    @org.junit.jupiter.api.Order(2)
    @Test
    void should_create_a_quote_successfully() {
        Order order = new Order(UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), List.of(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585")), 10, OffsetDateTime.now());
        Quote quote = new Quote(UUID.randomUUID(), UUID.fromString("292a485f-a56a-4938-8f1a-bbbbbbbbbbb1"), 0D, 2500d, OffsetDateTime.now());
        assertDoesNotThrow(() -> quoteDBAdapter.saveQuote(quote));
        assertEquals(1, quoteRepository.listAll().size());
    }
    @org.junit.jupiter.api.Order(3)
    @Test
    void should_throw_a_GOE() {
        Quote quote = new Quote(UUID.randomUUID(), UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), 0D, 2500d, OffsetDateTime.now());
        assertThrows(GuitarOrderException.class, () -> quoteDBAdapter.saveQuote(quote));
    }
    @org.junit.jupiter.api.Order(1)
    @Test
    void should_get_empty_list() {
        assertTrue(quoteDBAdapter.getQuotes().isEmpty());

    }


}

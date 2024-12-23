package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.model.Quote;
import info.touret.guitarheaven.domain.port.QuotePort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;
import info.touret.guitarheaven.domain.service.DiscountService;
import info.touret.guitarheaven.domain.service.GuitarService;
import info.touret.guitarheaven.domain.service.OrderService;
import info.touret.guitarheaven.domain.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    GuitarService guitarService;
    @Mock
    DiscountService discountService;
    @Mock
    SupplyChainPort supplyChainPort;
    @Mock
    QuotePort quotePort;

    QuoteService quoteService;

    @Mock
    OrderService orderService;

    @BeforeEach
    void setUp() {
        quoteService = new QuoteService(guitarService, discountService, quotePort, supplyChainPort, orderService);
    }

    @Test
    void should_create_a_quote_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        var orderId = UUID.randomUUID();
        when(orderService.findById(any(UUID.class))).thenReturn(Optional.of(new Order(orderId, List.of(guitarId), 10, OffsetDateTime.now())));
        Quote quote = new Quote(null, orderId, null, null, null);
        assertNotNull(quoteService.createQuote(quote));
    }

    @Test
    void should_create_a_quote_and_ask_for_furniture_successfully() {
        var guitarId = UUID.randomUUID();

        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 2));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        var orderId = UUID.randomUUID();
        when(orderService.findById(any(UUID.class))).thenReturn(Optional.of(new Order(orderId, List.of(guitarId), 10, OffsetDateTime.now())));
        Quote quote = new Quote(null, orderId, null, null, null);
        ArgumentCaptor<Integer> numberOfGuitarsRequestedArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        assertNotNull(quoteService.createQuote(quote));
        verify(supplyChainPort).requestForAdditionalGuitars(eq("Gibson ES 335"), numberOfGuitarsRequestedArgumentCaptor.capture());
    }

    @Test
    void should_create_a_quote_and_apply_requested_discount_successfully() {
        var guitarId = UUID.randomUUID();

        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 2));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        when(discountService.getTotalDiscount(eq(guitars))).thenReturn(20D);
        var orderId = UUID.randomUUID();

        Order order = new Order(orderId, List.of(guitarId), 10D, OffsetDateTime.now());
        Quote quote = new Quote(null, orderId, null, null, null);
        ArgumentCaptor<Quote> quoteArgumentCaptor = ArgumentCaptor.forClass(Quote.class);
        when(orderService.findById(any(UUID.class))).thenReturn(Optional.of(order));
        assertNotNull(quoteService.createQuote(quote));
        verify(quotePort).saveQuote(quoteArgumentCaptor.capture());
        assertEquals(10D, quoteArgumentCaptor.getValue().discount());
    }

    @Test
    void should_create_a_quote_and_apply_ebay_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 2));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        when(discountService.getTotalDiscount(eq(guitars))).thenReturn(20D);
        var orderId = UUID.randomUUID();

        Order order = new Order(orderId, List.of(guitarId), 30D, OffsetDateTime.now());
        Quote quote = new Quote(null, orderId, null, null, null);
        when(orderService.findById(any(UUID.class))).thenReturn(Optional.of(order));
        ArgumentCaptor<Quote> quoteArgumentCaptor = ArgumentCaptor.forClass(Quote.class);
        assertNotNull(quoteService.createQuote(quote));
        verify(quotePort).saveQuote(quoteArgumentCaptor.capture());
        assertEquals(20D, quoteArgumentCaptor.getValue().discount());
    }

    @Test
    void should_create_and_throw_ENFE() {
        var guitarId = UUID.randomUUID();
        when(orderService.findById(any(UUID.class))).thenReturn(Optional.empty());
        var orderId = UUID.randomUUID();
        Quote quote = new Quote(null, orderId, null, null, null);
        assertThrows(EntityNotFoundException.class, ()-> quoteService.createQuote(quote));
    }
}

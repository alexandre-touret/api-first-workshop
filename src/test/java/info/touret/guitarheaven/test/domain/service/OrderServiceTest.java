package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import info.touret.guitarheaven.domain.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    OrderService orderService;

    @Mock
    GuitarService guitarService;

    @Mock
    OrderPort orderPort;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(guitarService, orderPort);
    }

    @Test
    void should_create_an_order_no_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        Order order = new Order(null, List.of(guitarId), 100D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
    }

    @Test
    void should_create_an_order_with_requested_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        Order order = new Order(null, List.of(guitarId), 100D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        verify(orderPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(100D, orderArgumentCaptor.getValue().discountRequested());
    }

    @Test
    void should_create_an_order_with_found_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        Order order = new Order(null, List.of(guitarId), 300D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        verify(orderPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(300D, orderArgumentCaptor.getValue().discountRequested());
    }

    @Test
    void should_throw_GOE() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 5));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(List.of());
        Order order = new Order(null, List.of(guitarId), 300D, OffsetDateTime.now());
        assertThrowsExactly(GuitarOrderException.class, () -> orderService.order(order));
    }


}

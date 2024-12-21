package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import info.touret.guitarheaven.domain.port.SupplyChainPort;
import info.touret.guitarheaven.domain.service.DiscountService;
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
    DiscountService discountService;
    @Mock
    SupplyChainPort supplyChainPort;
    @Mock
    OrderPort orderPort;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(guitarService, discountService, supplyChainPort, orderPort);
    }

    @Test
    void should_create_an_order_no_discount_successfully() {
        List<Guitar> guitars = List.of(new Guitar(999L, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByIds(List.of(999L))).thenReturn(guitars);
        when(discountService.getTotalDiscount(guitars)).thenReturn(0D);
        Order order = new Order(null, guitars, 100D, 2500D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        assertDoesNotThrow(() -> UUID.fromString(orderId));
    }

    @Test
    void should_create_an_order_with_requested_discount_successfully() {
        List<Guitar> guitars = List.of(new Guitar(999L, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByIds(List.of(999L))).thenReturn(guitars);
        when(discountService.getTotalDiscount(guitars)).thenReturn(200D);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        Order order = new Order(null, guitars, 100D, 2500D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        assertDoesNotThrow(() -> UUID.fromString(orderId));
        verify(orderPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(100D, orderArgumentCaptor.getValue().discountRequested());
    }

    @Test
    void should_create_an_order_with_found_discount_successfully() {
        List<Guitar> guitars = List.of(new Guitar(999L, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByIds(List.of(999L))).thenReturn(guitars);
        when(discountService.getTotalDiscount(guitars)).thenReturn(200D);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        Order order = new Order(null, guitars, 300D, 2500D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        assertDoesNotThrow(() -> UUID.fromString(orderId));
        verify(orderPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(200D, orderArgumentCaptor.getValue().discountRequested());
    }

    @Test
    void should_throw_GOE() {
        List<Guitar> guitars = List.of(new Guitar(99L, "ES 135", Guitar.TYPE.ELECTRIC, 1500D, 5));
        when(guitarService.findGuitarsByIds(anyList())).thenReturn(List.of());
        Order order = new Order(null, guitars, 300D, 2500D, OffsetDateTime.now());
        assertThrowsExactly(GuitarOrderException.class, () -> orderService.order(order));
    }

    @Test
    void should_request_supply_chain() {
        List<Guitar> guitars = List.of(new Guitar(999L, "ES 335", Guitar.TYPE.ELECTRIC, 2500D, 1));
        when(guitarService.findGuitarsByIds(List.of(999L))).thenReturn(guitars);
        when(discountService.getTotalDiscount(guitars)).thenReturn(200D);
        ArgumentCaptor<String> guitarNameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> numberOfGuitarsRequestedArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Order order = new Order(null, guitars, 100D, 2500D, OffsetDateTime.now());
        var orderId = orderService.order(order);
        assertNotNull(orderId);
        assertDoesNotThrow(() -> UUID.fromString(orderId));
        verify(supplyChainPort).requestForAdditionalGuitars(guitarNameArgumentCaptor.capture(), numberOfGuitarsRequestedArgumentCaptor.capture());
        assertEquals(4, numberOfGuitarsRequestedArgumentCaptor.getValue());
    }

}

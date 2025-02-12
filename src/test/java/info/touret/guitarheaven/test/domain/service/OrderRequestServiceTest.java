package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.port.OrderRequestPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import info.touret.guitarheaven.domain.service.OrderRequestService;
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
class OrderRequestServiceTest {

    OrderRequestService orderRequestService;

    @Mock
    GuitarService guitarService;

    @Mock
    OrderRequestPort orderRequestPort;

    @BeforeEach
    void setUp() {
        orderRequestService = new OrderRequestService(guitarService, orderRequestPort);
    }

    @Test
    void should_create_an_create_no_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        OrderRequest orderRequest = new OrderRequest(null, List.of(guitarId), 100D, OffsetDateTime.now());
        var orderId = orderRequestService.create(orderRequest);
        assertNotNull(orderId);
    }

    @Test
    void should_create_an_create_with_requested_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        ArgumentCaptor<OrderRequest> orderArgumentCaptor = ArgumentCaptor.forClass(OrderRequest.class);
        OrderRequest orderRequest = new OrderRequest(null, List.of(guitarId), 100D, OffsetDateTime.now());
        var orderId = orderRequestService.create(orderRequest);
        assertNotNull(orderId);
        verify(orderRequestPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(100D, orderArgumentCaptor.getValue().discountRequestedInUSD());
    }

    @Test
    void should_create_an_create_with_found_discount_successfully() {
        var guitarId = UUID.randomUUID();
        List<Guitar> guitars = List.of(new Guitar(999L, guitarId, "Gibson ES 335", Guitar.TYPE.ELECTRIC, 2500D, 10));
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(guitars);
        ArgumentCaptor<OrderRequest> orderArgumentCaptor = ArgumentCaptor.forClass(OrderRequest.class);
        OrderRequest orderRequest = new OrderRequest(null, List.of(guitarId), 300D, OffsetDateTime.now());
        var orderId = orderRequestService.create(orderRequest);
        assertNotNull(orderId);
        verify(orderRequestPort).saveOrder(orderArgumentCaptor.capture());
        assertEquals(300D, orderArgumentCaptor.getValue().discountRequestedInUSD());
    }

    @Test
    void should_throw_ENFE() {
        var guitarId = UUID.randomUUID();
        when(guitarService.findGuitarsByGuitarIds(anyList())).thenReturn(List.of());
        OrderRequest orderRequest = new OrderRequest(null, List.of(guitarId), 300D, OffsetDateTime.now());
        assertThrowsExactly(EntityNotFoundException.class, () -> orderRequestService.create(orderRequest));
    }


}

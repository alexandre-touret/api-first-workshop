package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.port.OrderPort;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderService {

    private final GuitarService guitarService;
    private final OrderPort orderPort;

    @Inject
    public OrderService(GuitarService guitarService, OrderPort orderPort) {
        this.guitarService = guitarService;
        this.orderPort = orderPort;
    }

    public UUID create(OrderRequest orderRequest) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());
        if (relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Invalid Guitar List for Order " + orderRequest);
        } else if (orderRequest.orderId() != null && orderPort.findOrderByUUID(orderRequest.orderId()).isPresent()) {
            throw new EntityNotFoundException("The Order " + orderRequest.orderId() + " already exists");
        } else {
            OrderRequest finalOrderRequest = new OrderRequest(UUID.randomUUID(), relatedGuitars.stream().map(Guitar::guitarId).toList(), orderRequest.discountRequested(), OffsetDateTime.now());
            orderPort.saveOrder(finalOrderRequest);
            return finalOrderRequest.orderId();
        }
    }

    public List<OrderRequest> findAllOrders() {
        return orderPort.findAllOrders();
    }


    public Optional<OrderRequest> findById(UUID orderId) {
        return orderPort.findOrderByUUID(orderId);
    }
}

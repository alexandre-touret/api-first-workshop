package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
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

    public UUID create(Order order) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(order.guitarIds());
        if (relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Invalid Guitar List for Order " + order);
        } else if (order.orderId() != null && orderPort.findOrderByUUID(order.orderId()).isPresent()) {
            throw new EntityNotFoundException("The Order " + order.orderId() + " already exists");
        } else {
            Order finalOrder = new Order(UUID.randomUUID(), relatedGuitars.stream().map(Guitar::guitarId).toList(), order.discountRequested(), OffsetDateTime.now());
            orderPort.saveOrder(finalOrder);
            return finalOrder.orderId();
        }
    }

    public List<Order> findAllOrders() {
        return orderPort.findAllOrders();
    }


    public Optional<Order> findById(UUID orderId) {
        return orderPort.findOrderByUUID(orderId);
    }
}

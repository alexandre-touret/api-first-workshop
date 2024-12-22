package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.GuitarOrderException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Order;
import info.touret.guitarheaven.domain.port.OrderPort;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private final GuitarService guitarService;
    private final OrderPort orderPort;

    @Inject
    public OrderService(GuitarService guitarService, OrderPort orderPort) {
        this.guitarService = guitarService;
        this.orderPort = orderPort;
    }

    public UUID order(Order order) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByIds(order.guitars().stream().mapToLong(Guitar::id).boxed().toList());
        if (relatedGuitars.isEmpty()) {
            throw new GuitarOrderException("Invalid Guitar List for Order " + order);
        } else if (order.orderId() != null && orderPort.findOrderByUUID(order.orderId()).isPresent()) {
            throw new GuitarOrderException("The Order " + order.orderId() + " already exists");
        } else {
            Order finalOrder = new Order(UUID.randomUUID(), relatedGuitars, order.discountRequested(), OffsetDateTime.now());
            orderPort.saveOrder(finalOrder);
            return finalOrder.orderId();
        }
    }


}

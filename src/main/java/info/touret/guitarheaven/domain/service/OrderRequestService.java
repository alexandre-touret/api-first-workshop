package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.port.OrderRequestPort;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 */
public class OrderRequestService {

    private final GuitarService guitarService;
    private final OrderRequestPort orderRequestPort;

    @Inject
    public OrderRequestService(GuitarService guitarService, OrderRequestPort orderRequestPort) {
        this.guitarService = guitarService;
        this.orderRequestPort = orderRequestPort;
    }

    /**
     * Creates an order request and stores it
     * @param orderRequest The order to create
     * @return the UUID of the new Order Request
     */
    public UUID create(OrderRequest orderRequest) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());
        if (relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Invalid Guitar List for Order " + orderRequest);
        } else if (orderRequest.orderId() != null && orderRequestPort.findOrderByUUID(orderRequest.orderId()).isPresent()) {
            throw new EntityNotFoundException("The Order " + orderRequest.orderId() + " already exists");
        } else {
            OrderRequest finalOrderRequest = new OrderRequest(UUID.randomUUID(), relatedGuitars.stream().map(Guitar::guitarId).toList(), orderRequest.discountRequested(), OffsetDateTime.now());
            orderRequestPort.saveOrder(finalOrderRequest);
            return finalOrderRequest.orderId();
        }
    }

    /**
     * Finds all the orders
     * @return The list of orders
     */
    public List<OrderRequest> findAllOrders() {
        return orderRequestPort.findAllOrders();
    }


    /**
     * Finds by UUID
     * @param orderId
     * @return
     */
    public Optional<OrderRequest> findByUUID(UUID orderId) {
        return orderRequestPort.findOrderByUUID(orderId);
    }
}

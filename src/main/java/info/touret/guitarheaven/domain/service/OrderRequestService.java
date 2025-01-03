package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.OrderRequest;
import info.touret.guitarheaven.domain.port.OrderRequestPort;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Manages Order Requests
 */
public class OrderRequestService {

    private final GuitarService guitarService;
    private final OrderRequestPort orderRequestPort;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRequestService.class);

    @Inject
    public OrderRequestService(GuitarService guitarService, OrderRequestPort orderRequestPort) {
        this.guitarService = guitarService;
        this.orderRequestPort = orderRequestPort;
    }

    /**
     * Creates an order request and stores it
     *
     * @param orderRequest The order to create
     * @return the UUID of the new Order Request
     */
    public UUID create(OrderRequest orderRequest) {
        List<Guitar> relatedGuitars = guitarService.findGuitarsByGuitarIds(orderRequest.guitarIds());
        LOGGER.info("Creating order request for {} guitar(s)", orderRequest.guitarIds().size());
        if (relatedGuitars.isEmpty()) {
            throw new EntityNotFoundException("Invalid Guitar List for Order " + orderRequest);
        } else if (orderRequest.orderId() != null && orderRequestPort.findOrderByUUID(orderRequest.orderId()).isPresent()) {
            throw new IllegalArgumentException("The Order " + orderRequest.orderId() + " already exists");
        } else {
            OrderRequest finalOrderRequest = new OrderRequest(UUID.randomUUID(), relatedGuitars.stream().map(Guitar::guitarId).toList(), orderRequest.discountRequestedInUSD(), OffsetDateTime.now());
            LOGGER.info("Saving order {}", orderRequest.orderId());
            orderRequestPort.saveOrder(finalOrderRequest);
            LOGGER.debug("Saved order {}", orderRequest.orderId());
            return finalOrderRequest.orderId();
        }
    }

    /**
     * Finds all the orders
     *
     * @return The list of orders
     */
    public List<OrderRequest> findAllOrders() {
        return orderRequestPort.findAllOrders();
    }


    /**
     * Finds by UUID
     *
     * @param orderId: The ID
     * @return The Optional Order request
     */
    public Optional<OrderRequest> findByUUID(UUID orderId) {
        return orderRequestPort.findOrderByUUID(orderId);
    }
}

package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.OrderRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port for orders
 */
public interface OrderRequestPort {
    /**
     *      * @return a list of orders requests. May be empty if no results are found
     */
    List<OrderRequest> findAllOrders();

    /**
     * @param orderRequest
     */
    void saveOrder(OrderRequest orderRequest);

    /**
     * @param id
     * @return
     */
    Optional<OrderRequest> findOrderByUUID(UUID id);
}

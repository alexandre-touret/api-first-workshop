package info.touret.guitarheaven.domain.port;

import info.touret.guitarheaven.domain.model.OrderRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPort {
    List<OrderRequest> findAllOrders();

    void saveOrder(OrderRequest orderRequest);

    Optional<OrderRequest> findOrderByUUID(UUID id);
}

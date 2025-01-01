package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.OrderRequestEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<OrderRequestEntity> {

    public Optional<OrderRequestEntity> findByUUID(UUID uuid) {
        return list("orderId = ?1", uuid).stream().findFirst();
    }

    public List<OrderRequestEntity> findAllOrders() {
        return list("select order from OrderRequestEntity order join order.guitars").stream().toList();
    }
}

package info.touret.guitarheaven.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Table(name = "GuitarOrder")
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "orders")
    private Set<GuitarEntity> guitars;

    private UUID orderId;

    private Double discountRequested;

    private Double totalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GuitarEntity> getGuitars() {
        return guitars;
    }

    public void setGuitars(Set<GuitarEntity> guitars) {
        this.guitars = guitars;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createAt) {
        this.createdAt = createAt;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Double getDiscountRequested() {
        return discountRequested;
    }

    public void setDiscountRequested(Double discountRequested) {
        this.discountRequested = discountRequested;
    }
}
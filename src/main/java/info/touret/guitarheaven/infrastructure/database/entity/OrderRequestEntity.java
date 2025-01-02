package info.touret.guitarheaven.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Table(name = "GuitarOrder", uniqueConstraints = {@UniqueConstraint(name = "orderIdUnique", columnNames = "orderId")})
@Entity
public class OrderRequestEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "Guitar_GuitarOrder",
            joinColumns = { @JoinColumn(name = "orders_id") },
            inverseJoinColumns = { @JoinColumn(name = "guitars_id") }
    )
    private Set<GuitarEntity> guitars;

    private UUID orderId;

    private Double discountRequested;


    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @OneToOne
    private QuoteEntity quote;

    public OrderRequestEntity() {
    }

    public OrderRequestEntity(Long id, Set<GuitarEntity> guitars, UUID orderId, Double discountRequested,  OffsetDateTime createdAt, QuoteEntity quote) {
        this.id = id;
        this.guitars = guitars;
        this.orderId = orderId;
        this.discountRequested = discountRequested;
        this.createdAt = createdAt;
        this.quote = quote;
    }

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

    @Override
    public String toString() {
        return "OrderRequestEntity{" +
                "id=" + id +
                ", guitars=" + guitars +
                ", orderId=" + orderId +
                ", discountRequested=" + discountRequested +
                ", createdAt=" + createdAt +
                ", quote=" + quote +
                '}';
    }
}

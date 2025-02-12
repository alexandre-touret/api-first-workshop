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

    @ManyToMany( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Guitar_GuitarOrder",
            joinColumns = { @JoinColumn(name = "orders_id") },
            inverseJoinColumns = { @JoinColumn(name = "guitars_id") }
    )
    private Set<GuitarEntity> guitars;

    private UUID orderId;

    private Double discountRequestedInUSD;


    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @OneToOne
    private QuoteEntity quote;

    public OrderRequestEntity() {
    }

    public OrderRequestEntity(Long id, Set<GuitarEntity> guitars, UUID orderId, Double discountRequestedInUSD, OffsetDateTime createdAt, QuoteEntity quote) {
        this.id = id;
        this.guitars = guitars;
        this.orderId = orderId;
        this.discountRequestedInUSD = discountRequestedInUSD;
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

    public Double getDiscountRequestedInUSD() {
        return discountRequestedInUSD;
    }

    public void setDiscountRequestedInUSD(Double discountRequested) {
        this.discountRequestedInUSD = discountRequested;
    }

    @Override
    public String toString() {
        return "OrderRequestEntity{" +
                "id=" + id +
                ", guitars=" + guitars +
                ", orderId=" + orderId +
                ", discountRequestedInUSD=" + discountRequestedInUSD +
                ", createdAt=" + createdAt +
                ", quote=" + quote +
                '}';
    }
}

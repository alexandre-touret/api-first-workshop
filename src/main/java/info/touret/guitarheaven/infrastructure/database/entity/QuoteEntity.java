package info.touret.guitarheaven.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "GuitarQuote", uniqueConstraints = {@UniqueConstraint(name = "quoteIdUnique", columnNames = "quoteId")})
public class QuoteEntity {

    @Id
    @GeneratedValue
    private Long id;

    private UUID quoteId;

    @OneToOne(mappedBy = "quote")
    private OrderEntity order;

    @Min(0)
    private Double totalPriceWithDiscount;

    @Min(0)
    private Double discount;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(UUID quoteId) {
        this.quoteId = quoteId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Double getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    public void setTotalPriceWithDiscount(Double totalPriceWithDiscount) {
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

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
    private OrderRequestEntity order;

    @Min(0)
    private Double totalPriceWithDiscountInUSD;

    @Min(0)
    private Double discountInUSD;

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

    public OrderRequestEntity getOrder() {
        return order;
    }

    public void setOrder(OrderRequestEntity order) {
        this.order = order;
    }

    public Double getTotalPriceWithDiscountInUSD() {
        return totalPriceWithDiscountInUSD;
    }

    public void setTotalPriceWithDiscountInUSD(Double totalPriceWithDiscount) {
        this.totalPriceWithDiscountInUSD = totalPriceWithDiscount;
    }

    public Double getDiscountInUSD() {
        return discountInUSD;
    }

    public void setDiscountInUSD(Double discount) {
        this.discountInUSD = discount;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

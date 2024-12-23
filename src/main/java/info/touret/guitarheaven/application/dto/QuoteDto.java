package info.touret.guitarheaven.application.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record QuoteDto(UUID quoteId, @org.hibernate.validator.constraints.UUID UUID orderId, Double discount, Double totalPriceWithDiscount,
                       OffsetDateTime createdAt) {
}

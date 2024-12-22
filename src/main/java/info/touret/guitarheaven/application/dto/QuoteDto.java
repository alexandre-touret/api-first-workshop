package info.touret.guitarheaven.application.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record QuoteDto(UUID quoteId, UUID orderIds, Double discount, Double totalPriceWithDiscount,
                       OffsetDateTime createdAt) {
}

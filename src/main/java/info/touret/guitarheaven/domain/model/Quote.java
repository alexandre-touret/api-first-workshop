package info.touret.guitarheaven.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Quote(UUID quoteId, UUID orderId, Double discount, Double totalPriceWithDiscount,
                    OffsetDateTime createdAt) {
}

package info.touret.guitarheaven.domain.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Order(UUID orderId, List<UUID> guitarIds, double discountRequested,
                    OffsetDateTime createdAt) {
}

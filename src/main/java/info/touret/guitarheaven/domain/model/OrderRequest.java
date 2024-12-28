package info.touret.guitarheaven.domain.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * An order request
 * @param orderId : The UUID of the order request
 * @param guitarIds : The list of guitars wished
 * @param discountRequested: The discount requested by the customer
 * @param createdAt: The timestamp
 */
public record OrderRequest(UUID orderId, List<UUID> guitarIds, double discountRequested,
                           OffsetDateTime createdAt) {
}
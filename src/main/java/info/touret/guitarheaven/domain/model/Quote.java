package info.touret.guitarheaven.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Quote provided for an order
 *
 * @param quoteId                : The UUID of the quote
 * @param orderId                : the UUID of the order
 * @param discount:              The discount calculated
 * @param totalPriceWithDiscount : The final priceInUSD
 * @param createdAt:             The timstamp of the creation
 */
public record Quote(UUID quoteId, UUID orderId, Double discount, Double totalPriceWithDiscount,
                    OffsetDateTime createdAt) {
}

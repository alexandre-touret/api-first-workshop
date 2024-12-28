package info.touret.guitarheaven.domain.model;

import java.util.UUID;

/**
 * Guitar Domain class
 * @param id : The ID
 * @param guitarId: The UUID
 * @param name: The name of the guitar
 * @param type: The type of the guitar
 * @param priceInUSD: The price in USD
 * @param stock: The current stock
 */
public record Guitar(Long id, UUID guitarId, String name, TYPE type, Double priceInUSD, int stock) {
    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY,JAZZ
    }
}

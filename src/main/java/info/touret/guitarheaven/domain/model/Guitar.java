package info.touret.guitarheaven.domain.model;

import java.util.UUID;

public record Guitar(Long id, UUID guitarId, String name, TYPE type, Double price, int stock) {
    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY,JAZZ
    }
}

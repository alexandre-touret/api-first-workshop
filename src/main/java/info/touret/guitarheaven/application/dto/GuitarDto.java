package info.touret.guitarheaven.application.dto;

import java.util.UUID;

public record GuitarDto(UUID guitarId, String name, TYPE type, Double price, int stock) {

    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY, JAZZ
    }

}

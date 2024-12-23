package info.touret.guitarheaven.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record GuitarDto(UUID guitarId, @NotBlank String name, @NotBlank TYPE type, @Min(0) Double price, @Min(0) int stock) {

    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY, JAZZ
    }

}

package info.touret.guitarheaven.application.dto;

public record GuitarDto(Long id, String name, TYPE type, Double price, int stock) {

    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY
    }

}

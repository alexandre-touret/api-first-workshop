package info.touret.guitarheaven.domain.model;

public record Guitar(Long id, String name, TYPE type, Double price, int stock) {
    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY,JAZZ
    }
}

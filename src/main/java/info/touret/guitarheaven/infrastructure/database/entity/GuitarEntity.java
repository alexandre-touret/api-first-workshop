package info.touret.guitarheaven.infrastructure.database.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Table(name = "Guitar",uniqueConstraints = {@UniqueConstraint(name = "guitarIdUnique",columnNames = "guitarId")})
@Entity
public class GuitarEntity {
    public enum TYPE {
        ELECTRIC, CLASSIC, FOLK, GIPSY, JAZZ
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TYPE type;
    private Double price;
    private int stock;

    private UUID guitarId;

    public UUID getGuitarId() {
        return guitarId;
    }

    public void setGuitarId(UUID guitarId) {
        this.guitarId = guitarId;
    }

    @ManyToMany()
    private Set<OrderEntity> orders;

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

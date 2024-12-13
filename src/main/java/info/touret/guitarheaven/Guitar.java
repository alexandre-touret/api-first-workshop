package info.touret.guitarheaven;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Guitar extends PanacheEntity {
    public String name;
    public String type;
    public Double price;
    public int stock;
}

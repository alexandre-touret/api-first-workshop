package info.touret.guitarheaven.infrastructure.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<GuitarEntity> guitars;

    private Double totalPrice;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GuitarEntity> getGuitars() {
        return guitars;
    }

    public void setGuitars(List<GuitarEntity> guitars) {
        this.guitars = guitars;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }
}

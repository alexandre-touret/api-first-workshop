package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<GuitarEntity> {

    public List<GuitarEntity> findInIds(List<Long> ids) {
        return this.list("id in ?1", ids);
    }
}

package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<GuitarEntity> {
}

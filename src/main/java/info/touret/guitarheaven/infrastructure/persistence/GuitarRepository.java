package info.touret.guitarheaven.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<GuitarEntity> {



}

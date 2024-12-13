package info.touret.guitarheaven.repository;

import info.touret.guitarheaven.model.Guitar;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<Guitar> {
}

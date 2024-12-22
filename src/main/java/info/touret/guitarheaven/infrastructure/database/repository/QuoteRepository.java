package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.QuoteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuoteRepository implements PanacheRepository<QuoteEntity> {
}

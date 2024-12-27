package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<GuitarEntity> {

    public List<GuitarEntity> findInIds(List<Long> ids) {
        return this.list("id in ?1", ids);
    }

    public List<GuitarEntity> findGuitarsyUUIDs(List<UUID> uuids) {
        return this.find("guitarId in ?1", uuids).list();
    }

    public Optional<GuitarEntity> findByUUID(UUID uuid) {
        return this.find("guitarId = ?1", uuid).stream().findFirst();
    }
}

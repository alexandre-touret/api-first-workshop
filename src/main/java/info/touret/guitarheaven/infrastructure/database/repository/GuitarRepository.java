package info.touret.guitarheaven.infrastructure.database.repository;

import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class GuitarRepository implements PanacheRepository<GuitarEntity> {

    public List<GuitarEntity> findInIds(List<Long> ids) {
        return this.list("id in ?1", ids);
    }

    public Set<GuitarEntity> findGuitarsByUUIDs(List<UUID> uuids) {
        return this.find("guitarId in ?1", uuids).stream().collect(Collectors.toSet());
    }

    public Optional<GuitarEntity> findByUUID(UUID uuid) {
        return this.find("guitarId = ?1", uuid).stream().findFirst();
    }
}

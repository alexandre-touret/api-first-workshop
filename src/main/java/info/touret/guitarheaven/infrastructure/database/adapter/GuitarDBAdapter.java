package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.exception.EntityNotFoundException;
import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Page;
import info.touret.guitarheaven.domain.port.GuitarPort;
import info.touret.guitarheaven.infrastructure.database.mapper.GuitarEntityMapper;
import info.touret.guitarheaven.infrastructure.database.repository.GuitarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class GuitarDBAdapter implements GuitarPort {

    private final GuitarRepository guitarRepository;
    private final GuitarEntityMapper guitarEntityMapper;

    @Inject
    public GuitarDBAdapter(GuitarRepository guitarRepository, GuitarEntityMapper guitarEntityMapper) {
        this.guitarRepository = guitarRepository;
        this.guitarEntityMapper = guitarEntityMapper;
    }


    @Override
    public List<Guitar> listAll() {
        return guitarEntityMapper.toGuitars(guitarRepository.listAll());
    }

    @Transactional
    @Override
    public void save(Guitar guitar) {
        guitarRepository.persist(guitarEntityMapper.toGuitarEntity(guitar));
    }

    @Transactional
    @Override
    public Guitar update(Guitar guitar) {
        var mapperGuitarEntity = guitarEntityMapper.toGuitarEntity(guitar);
        var guitarEntity = guitarRepository.findByUUID(mapperGuitarEntity.getGuitarId()).orElseThrow(() -> new EntityNotFoundException("Guitar not found :" + guitar.guitarId()));
        guitarEntity.setType(mapperGuitarEntity.getType());
        guitarEntity.setStock(mapperGuitarEntity.getStock());
        guitarEntity.setPriceInUSD(mapperGuitarEntity.getPriceInUSD());
        guitarEntity.setOrders(mapperGuitarEntity.getOrders());
        guitarEntity.setName(mapperGuitarEntity.getName());
        return guitarEntityMapper.toGuitar(guitarRepository.getEntityManager().merge(guitarEntity));
    }

    @Transactional
    @Override
    public boolean deleteByUUID(UUID guitarId) {
        boolean status = false;
        var optionalGuitarEntity = guitarRepository.findByUUID(guitarId);
        if (optionalGuitarEntity.isPresent()) {
            status = guitarRepository.deleteById(optionalGuitarEntity.get().getId());
        }
        return status;
    }

    @Override
    public List<Guitar> findGuitarsByIds(List<Long> ids) {
        return guitarEntityMapper.toGuitars(guitarRepository.findInIds(ids));
    }

    @Override
    public List<Guitar> findGuitarsByGuitarIds(List<UUID> guitarIds) {
        return guitarEntityMapper.toGuitars(List.copyOf(guitarRepository.findGuitarsByUUIDs(guitarIds)));
    }

    @Override
    public Page<Guitar> findAllGuitarByPage(int pageNumber, int pageSize) {
        var guitarEntityPanacheQuery = guitarRepository.findAll().page(io.quarkus.panache.common.Page.of(pageNumber, pageSize));
        var pageCount = guitarEntityPanacheQuery.pageCount();
        var entities = guitarEntityPanacheQuery.list();
        var hasNextPage = guitarEntityPanacheQuery.hasNextPage();
        var hasPreviousPage = guitarEntityPanacheQuery.hasPreviousPage();
        return new Page<>(pageCount, guitarEntityMapper.toGuitars(entities), pageNumber, hasNextPage, hasPreviousPage);
    }
}

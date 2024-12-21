package info.touret.guitarheaven.infrastructure.database.adapter;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.port.GuitarPort;
import info.touret.guitarheaven.infrastructure.database.repository.GuitarRepository;
import info.touret.guitarheaven.infrastructure.database.mapper.GuitarEntityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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
        return guitarEntityMapper.toGuitar(guitarRepository.getEntityManager().merge(guitarEntityMapper.toGuitarEntity(guitar)));
    }

    @Transactional
    @Override
    public boolean delete(Long guitarId) {
        return guitarRepository.deleteById(guitarId);
    }

    @Override
    public List<Guitar> findGuitarsByIds(List<Long> ids) {
        return guitarEntityMapper.toGuitars(guitarRepository.findInIds(ids));
    }
}

package info.touret.guitarheaven.infrastructure.persistence;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.service.GuitarPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DatabaseAdapter implements GuitarPort {

    private final GuitarRepository guitarRepository;
    private final GuitarEntityMapper guitarEntityMapper;

    @Inject
    public DatabaseAdapter(GuitarRepository guitarRepository, GuitarEntityMapper guitarEntityMapper) {
        this.guitarRepository = guitarRepository;
        this.guitarEntityMapper = guitarEntityMapper;
    }

    @Override
    public List<Guitar> listAll() {
        return guitarEntityMapper.toGuitars(guitarRepository.listAll());
    }

    @Override
    public void save(Guitar guitar) {
        guitarRepository.persist(guitarEntityMapper.toGuitarEntity(guitar));
    }

    @Override
    public Guitar update(Guitar guitar) {
        return guitarEntityMapper.toGuitar(guitarRepository.getEntityManager().merge(guitarEntityMapper.toGuitarEntity(guitar)));
    }

    @Override
    public boolean delete(Long guitarId) {
        return guitarRepository.deleteById(guitarId);
    }
}

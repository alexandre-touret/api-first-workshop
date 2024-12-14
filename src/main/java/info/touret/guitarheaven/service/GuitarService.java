package info.touret.guitarheaven.service;

import info.touret.guitarheaven.model.Guitar;
import info.touret.guitarheaven.repository.GuitarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class GuitarService {

    private final GuitarRepository guitarRepository;

    @Inject
    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }
    public List<Guitar> findAllGuitars() {
        return guitarRepository.listAll();
    }

    @Transactional
    public void createGuitar(Guitar guitar) {
        guitarRepository.persist(guitar);
    }

    @Transactional
    public Guitar updateGuitar(Guitar guitar) {
        return guitarRepository.getEntityManager().merge(guitar);
    }

    @Transactional
    public boolean deleteGuitar(Long guitarId) {
        return guitarRepository.deleteById(guitarId);
    }
}

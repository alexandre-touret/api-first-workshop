package info.touret.guitarheaven;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GuitarService {

    public List<Guitar> findAllGuitars() {
        return Guitar.listAll();
    }

    public void createGuitar(Guitar guitar) {
        Guitar.persist(guitar);
    }

    public Guitar updateGuitar(Guitar guitar) {
        return Guitar.getEntityManager().merge(guitar);
    }

    public boolean deleteGuitar(Guitar guitar) {
        return Guitar.deleteById(guitar.id);
    }
}

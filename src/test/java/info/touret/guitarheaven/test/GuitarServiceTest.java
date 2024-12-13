package info.touret.guitarheaven.test;

import info.touret.guitarheaven.model.Guitar;
import info.touret.guitarheaven.repository.GuitarRepository;
import info.touret.guitarheaven.service.GuitarService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GuitarServiceTest {

    private GuitarService guitarService;
    private GuitarRepository guitarRepository;

    @BeforeEach
    void setUp() {
        guitarRepository = mock(GuitarRepository.class);
        guitarService = new GuitarService(guitarRepository);
    }

    @Test
    void should_return_list_of_one_element() {
        when(guitarRepository.listAll()).thenReturn(List.of(new Guitar()));
        assertEquals(1, guitarService.findAllGuitars().size());
    }

    @Test
    void should_create_guitar() throws Exception {
        guitarService.createGuitar(new Guitar());
    }

    @Test
    void should_update_guitar() {
        var mock = mock(EntityManager.class);
        when(guitarRepository.getEntityManager()).thenReturn(mock);
        var guitar = new Guitar();
        guitar.setId(2L);
        when(mock.merge(any(Guitar.class))).thenReturn(guitar);
        assertEquals(guitar.getId(), guitarService.updateGuitar(new Guitar()).getId());
    }

    @Test
    void should_delete_guitar() {
        when(guitarRepository.deleteById(1L)).thenReturn(true);
        assertTrue(guitarService.deleteGuitar(1L));
    }
}

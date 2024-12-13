package info.touret.guitarheaven;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class GuitarServiceTest {

    private GuitarService guitarService;

    @BeforeEach
    void setUp() {
        PanacheMock.mock(Guitar.class);
        guitarService = new GuitarService();
    }

    @Test
    void should_return_list_of_one_element() {
        when(Guitar.listAll()).thenReturn(List.of(new Guitar()));
        assertEquals(1, guitarService.findAllGuitars().size());
    }

    @Test
    void should_update_guitar() {
        var mock = Mockito.mock(EntityManager.class);
        when(Guitar.getEntityManager()).thenReturn(mock);
        var guitar = new Guitar();
        guitar.id = 2L;
        when(mock.merge(any(Guitar.class))).thenReturn(guitar);
        assertEquals(guitar, guitarService.updateGuitar(new Guitar()));
    }
}

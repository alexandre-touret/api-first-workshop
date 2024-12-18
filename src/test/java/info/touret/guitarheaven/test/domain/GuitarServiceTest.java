package info.touret.guitarheaven.test.domain;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.service.GuitarPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static info.touret.guitarheaven.domain.model.Guitar.TYPE.ELECTRIC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GuitarServiceTest {

    private GuitarService guitarService;
    private GuitarPort guitarPort;

    @BeforeEach
    void setUp() {
        guitarPort = mock(GuitarPort.class);
        guitarService = new GuitarService(guitarPort);
    }

    @Test
    void should_return_list_of_one_element() {
        when(guitarPort.listAll()).thenReturn(List.of(new Guitar(1L, "ES 135", ELECTRIC, 1000.0, 10)));
        assertEquals(1, guitarService.findAllGuitars().size());
    }

    @Test
    void should_create_guitar() throws Exception {
        guitarService.createGuitar(new Guitar(null, "ES 135", ELECTRIC, 1000.0, 10));
    }

    @Test
    void should_update_guitar() {
        var guitar = new Guitar(2L, "ES 135", ELECTRIC, 1000.0, 10);
        when(guitarPort.update(any(Guitar.class))).thenReturn(guitar);
        assertEquals(guitar.id(), guitarService.updateGuitar(new Guitar(1L, "ES 135", ELECTRIC, 1000.0, 10)).id());
    }

    @Test
    void should_delete_guitar() {
        when(guitarPort.delete(1L)).thenReturn(true);
        assertTrue(guitarService.deleteGuitar(1L));
    }
}

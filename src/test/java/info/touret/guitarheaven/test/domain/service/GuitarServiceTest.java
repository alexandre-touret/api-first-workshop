package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.model.Page;
import info.touret.guitarheaven.domain.port.GuitarPort;
import info.touret.guitarheaven.domain.service.GuitarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static info.touret.guitarheaven.domain.model.Guitar.TYPE.ELECTRIC;
import static org.junit.jupiter.api.Assertions.*;
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
        var uuid = UUID.randomUUID();
        when(guitarPort.listAll()).thenReturn(List.of(new Guitar(1L, uuid, "Gibson ES 335", ELECTRIC, 1000.0, 10)));
        assertEquals(1, guitarService.findAllGuitars().size());
    }

    @Test
    void should_create_guitar() throws Exception {
        guitarService.createGuitar(new Guitar(null, any(UUID.class), "Gibson ES 335", ELECTRIC, 1000.0, 10));
    }

    @Test
    void should_update_guitar() {
        var guitarId = UUID.randomUUID();
        var guitar = new Guitar(2L, guitarId, "Gibson ES 335", ELECTRIC, 1000.0, 10);
        when(guitarPort.update(any(Guitar.class))).thenReturn(guitar);
        assertEquals(guitar.id(), guitarService.updateGuitar(new Guitar(1L, guitarId, "Gibson ES 335", ELECTRIC, 1000.0, 10)).id());
    }

    @Test
    void should_delete_guitar() {
        when(guitarPort.deleteByUUID(any(UUID.class))).thenReturn(true);
        assertTrue(guitarService.deleteGuitarByUUID(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585")));
    }


    @Test
    void should_find_one_page_successfully() {
        var uuid = UUID.randomUUID();
        final int PAGE_NUMBER = 0;
        final int PAGE_SIZE = 10;
        when(guitarPort.findAllGuitarByPage(PAGE_NUMBER, PAGE_SIZE)).thenReturn(new Page<>(1, List.of(new Guitar(1L, uuid, "Gibson ES 335", ELECTRIC, 1000.0, 10)), 0, false, false));
        var allGuitarsByPage = guitarService.findAllGuitarsByPage(PAGE_NUMBER, PAGE_SIZE);
        assertEquals(1, allGuitarsByPage.getPageCount());
        assertEquals(0, allGuitarsByPage.getPageNumber());
        assertFalse(allGuitarsByPage.hasNext());
        assertFalse(allGuitarsByPage.hasPrevious());
    }

    @Test
    void should_find_one_blank_page_successfully() {
        var uuid = UUID.randomUUID();
        final int PAGE_NUMBER = 1;
        final int PAGE_SIZE = 10;
        when(guitarPort.findAllGuitarByPage(PAGE_NUMBER, PAGE_SIZE)).thenReturn(new Page<>(1, List.of(new Guitar(1L, uuid, "Gibson ES 335", ELECTRIC, 1000.0, 10)), 0, false, false));
        var allGuitarsByPage = guitarService.findAllGuitarsByPage(PAGE_NUMBER, PAGE_SIZE);
        assertEquals(1, allGuitarsByPage.getPageCount());
        assertEquals(0, allGuitarsByPage.getPageNumber());
        assertFalse(allGuitarsByPage.hasNext());
        assertFalse(allGuitarsByPage.hasPrevious());
    }
}

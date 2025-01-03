package info.touret.guitarheaven.test.infrastructure.database;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.infrastructure.database.adapter.GuitarDBAdapter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.UUID;

import static info.touret.guitarheaven.domain.model.Guitar.TYPE.GIPSY;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class GuitarDBAdapterTest {

    @Inject
    GuitarDBAdapter guitarDBAdapter;

    @Test
    @Order(1)
    void should_find_guitars() {
        assertFalse(guitarDBAdapter.listAll().isEmpty());
    }

    @Order(2)
    @Test
    void should_save_successfully() {
        Guitar guitarToSave = new Guitar(null, UUID.randomUUID(), "Dupont Nomade", GIPSY, 1200D, 10);
        assertDoesNotThrow(() -> guitarDBAdapter.save(guitarToSave));
        var guitars = guitarDBAdapter.listAll().stream().filter(guitar -> guitar.name().equals("Dupont Nomade")).toList();
        assertEquals(1, guitars.size());
    }

    @Order(3)
    @Test
    void should_update_successfully() {
        var dupontNomade = guitarDBAdapter.listAll().stream().filter(guitar -> guitar.name().equals("Dupont Nomade")).toList().getFirst();
        dupontNomade = new Guitar(dupontNomade.id(), dupontNomade.guitarId(), dupontNomade.name(), GIPSY, 1200D, 8);
        var guitar = guitarDBAdapter.update(dupontNomade);
        assertEquals(8, guitar.stock());
    }

    @Order(4)
    @Test
    void should_delete_ByUUID_successfully() {
        var guitarToDelete = guitarDBAdapter.listAll().stream().filter(guitar -> guitar.name().equals("Dupont Nomade")).toList().getFirst();
        assertTrue(guitarDBAdapter.deleteByUUID(guitarToDelete.guitarId()));
    }


    @Order(6)
    @Test
    void should_find_page_successfully() {
        var allGuitarByPage = guitarDBAdapter.findAllGuitarByPage(0, 10);
        assertEquals(0, allGuitarByPage.pageNumber());
        assertEquals(10, allGuitarByPage.entities().size());
        assertFalse(allGuitarByPage.hasNext());
        assertFalse(allGuitarByPage.hasPrevious());
    }


    @Order(7)
    @Test
    void should_not_find_page_successfully() {
        var allGuitarByPage = guitarDBAdapter.findAllGuitarByPage(1, 10);
        assertEquals(1, allGuitarByPage.pageNumber());
        assertEquals(0, allGuitarByPage.entities().size());
        assertFalse(allGuitarByPage.hasNext());
        assertTrue(allGuitarByPage.hasPrevious());
    }

    @Test
    void should_find_guitars_IDs_successfully() {
        var ids = List.of(1002L, 1003L);
        var guitarsByIds = guitarDBAdapter.findGuitarsByIds(ids);
        assertEquals(2, guitarsByIds.size());
        assertTrue(guitarsByIds.stream().allMatch(guitar -> ids.contains(guitar.id())));
    }

    @Test
    void should_find_no_guitars_IDs_successfully() {
        var ids = List.of(1104L, 1105L);
        var guitarsByIds = guitarDBAdapter.findGuitarsByIds(ids);
        assertEquals(0, guitarsByIds.size());
    }
}

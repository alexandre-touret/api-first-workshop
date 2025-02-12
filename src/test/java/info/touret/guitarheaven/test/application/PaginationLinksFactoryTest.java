package info.touret.guitarheaven.test.application;

import info.touret.guitarheaven.application.PaginationLinksFactory;
import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.domain.model.Page;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static info.touret.guitarheaven.application.dto.GuitarDto.TYPE.ELECTRIC;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaginationLinksFactoryTest {

    private PaginationLinksFactory paginationLinksFactory;

    @Mock
    private UriInfo uriInfo;
    private Page<GuitarDto> page;

    @BeforeEach
    void setUp() {
        paginationLinksFactory = new PaginationLinksFactory();
        page = new Page<GuitarDto>(1, List.of(new GuitarDto(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), "Gibson ES 335", ELECTRIC, 2500.0, 9)), 0, false, false);

    }

    @Test
    void should_return_a_list_successfully() throws MalformedURLException, URISyntaxException {
        when(uriInfo.getAbsolutePath()).thenReturn(URI.create("http://serverhost/test"));
        paginationLinksFactory.createLinksDto(uriInfo, page, 10);
        assertFalse(page.hasNext());
        assertFalse(page.hasPrevious());
        assertEquals(1, page.pageCount());
        assertEquals(UUID.fromString("628766d4-fee3-46dd-8bcb-426cffb4d585"), page.entities().getFirst().guitarId());
    }

    @Test
    void should_throw_MUE() {
        when(uriInfo.getAbsolutePath()).thenReturn(URI.create("blob://serverhost/test"));
        assertThrows(MalformedURLException.class, ()->paginationLinksFactory.createLinksDto(uriInfo, page, 10));
    }
}

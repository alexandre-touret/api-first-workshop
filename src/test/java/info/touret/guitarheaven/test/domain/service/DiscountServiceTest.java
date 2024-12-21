package info.touret.guitarheaven.test.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.domain.service.DiscountService;
import info.touret.guitarheaven.domain.service.SupplierCatalogPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {
    private DiscountService discountService;
    @Mock
    private SupplierCatalogPort supplierCatalogPort;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService(supplierCatalogPort);
    }

    @Test
    void should_return_0() {
        when(supplierCatalogPort.getAverageGuitarPrice("Fender")).thenReturn(OptionalDouble.of(2000));
        assertEquals(0, discountService.getDiscount(new Guitar(1L, "Fender", Guitar.TYPE.ELECTRIC, 1000d, 10)));
    }

    @Test
    void should_return_0_when_empty() {
        when(supplierCatalogPort.getAverageGuitarPrice("Fender")).thenReturn(OptionalDouble.empty());
        assertEquals(0, discountService.getDiscount(new Guitar(1L, "Fender", Guitar.TYPE.ELECTRIC, 1000d, 10)));
    }

    @Test
    void should_return_half_of_the_difference() {
        when(supplierCatalogPort.getAverageGuitarPrice("Fender")).thenReturn(OptionalDouble.of(900d));
        assertEquals(50, discountService.getDiscount(new Guitar(1L, "Fender", Guitar.TYPE.ELECTRIC, 1000d, 10)));
    }
}

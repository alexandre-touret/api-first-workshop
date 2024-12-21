package info.touret.guitarheaven.domain.service;

import java.util.Optional;
import java.util.OptionalDouble;

public interface SupplierCatalogPort {

    OptionalDouble getAverageGuitarPrice(String guitarName);
}

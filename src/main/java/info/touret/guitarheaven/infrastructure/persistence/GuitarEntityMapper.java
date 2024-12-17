package info.touret.guitarheaven.infrastructure.persistence;

import info.touret.guitarheaven.domain.model.Guitar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GuitarEntityMapper {

    Guitar toGuitar(GuitarEntity guitar);

    List<Guitar> toGuitars(List<GuitarEntity> guitars);

    GuitarEntity toGuitarEntity(Guitar guitar);
}

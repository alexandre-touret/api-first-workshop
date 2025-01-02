package info.touret.guitarheaven.infrastructure.database.mapper;

import info.touret.guitarheaven.domain.model.Guitar;
import info.touret.guitarheaven.infrastructure.database.entity.GuitarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface GuitarEntityMapper {

    Guitar toGuitar(GuitarEntity guitar);

    List<Guitar> toGuitars(List<GuitarEntity> guitars);

    @Mapping(target = "orders", ignore = true)
    GuitarEntity toGuitarEntity(Guitar guitar);


}

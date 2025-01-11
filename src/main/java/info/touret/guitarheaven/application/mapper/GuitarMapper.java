package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.generated.model.*;
import info.touret.guitarheaven.domain.model.Guitar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface GuitarMapper {

    GuitarDto toGuitarDto(Guitar guitar);

    List<GuitarDto> toGuitarsDto(List<Guitar> guitars);

    @Mapping(target = "id", ignore = true)
    Guitar toGuitar(GuitarDto guitarDto);
}

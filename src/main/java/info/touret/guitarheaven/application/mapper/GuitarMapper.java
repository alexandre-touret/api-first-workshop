package info.touret.guitarheaven.application.mapper;

import info.touret.guitarheaven.application.dto.GuitarDto;
import info.touret.guitarheaven.domain.model.Guitar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GuitarMapper {

    GuitarDto toGuitarDto(Guitar guitar);

    List<GuitarDto> toGuitarsDto(List<Guitar> guitars);

    Guitar toGuitar(GuitarDto guitarDto);
}

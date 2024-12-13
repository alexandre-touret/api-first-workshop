package info.touret.guitarheaven.mapper;

import info.touret.guitarheaven.dto.GuitarDto;
import info.touret.guitarheaven.dto.GuitarsDto;
import info.touret.guitarheaven.model.Guitar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GuitarMapper {

    GuitarDto toGuitarDto(Guitar guitar);

    default GuitarsDto toGuitarsDto(List<Guitar> guitars) {
        return new GuitarsDto(guitars.stream().map(this::toGuitarDto).toList());
    }

    Guitar toGuitar(GuitarDto guitarDto);
}

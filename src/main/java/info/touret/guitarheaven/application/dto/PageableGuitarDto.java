package info.touret.guitarheaven.application.dto;

import java.util.List;

public record PageableGuitarDto(List<GuitarDto> guitars, LinksDto links) {

}

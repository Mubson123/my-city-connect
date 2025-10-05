package com.mc.citizen.mapper;

import com.mc.citizen.dto.CitizenRequestDto;
import com.mc.citizen.dto.CitizenResponseDto;
import com.mc.citizen.model.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitizenMapper {

    CitizenResponseDto toResponseDto(Citizen citizen);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Citizen toEntity(CitizenRequestDto citizenRequestDto);
    List<CitizenResponseDto> toResponseDtos(List<Citizen> citizens);
}

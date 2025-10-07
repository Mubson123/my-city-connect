package com.mc.citizen.mapper;

import com.mc.citizen.dto.CitizenRequestDto;
import com.mc.citizen.dto.CitizenResponseDto;
import com.mc.citizen.model.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface CitizenMapper {

    CitizenResponseDto toResponseDto(Citizen citizen);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Citizen toEntity(CitizenRequestDto citizenRequestDto);
    List<CitizenResponseDto> toResponseDtos(List<Citizen> citizens);
}

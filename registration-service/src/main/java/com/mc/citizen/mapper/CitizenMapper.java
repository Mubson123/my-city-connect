package com.mc.citizen.mapper;

import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import com.mc.citizen.model.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CitizenMapper {

    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Citizen toCitizen(ApiCitizenRequest apiCitizenRequest);

    ApiCitizenResponse toApiResponse(Citizen citizen);

    List<ApiCitizenResponse> toApiResponses(List<Citizen> citizens);
}
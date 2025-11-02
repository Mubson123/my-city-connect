package com.mc.visa.mapper;

import com.mc.visa.model.ApiVisaRequest;
import com.mc.visa.model.ApiVisaResponse;
import com.mc.visa.model.Visa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VisaMapper {
    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Visa toVisa(ApiVisaRequest apiVisaRequest);

    ApiVisaResponse toApiResponse(Visa visa);

    List<ApiVisaResponse> toApiResponses(List<Visa> visas);
}

package com.mc.extend.mapper;

import com.mc.extend.model.ApiResidencePermitRequest;
import com.mc.extend.model.ApiResidencePermitResponse;
import com.mc.extend.model.ResidencePermit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResidencePermitMapper {
    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visa", ignore = true)
    @Mapping(target = "iDCart", ignore = true)
    @Mapping(target = "passport", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ResidencePermit toResidencePermit(ApiResidencePermitRequest apiResidencePermitRequest);

    @Mapping(target = "visaId", source = "visa.id")
    @Mapping(target = "iDCartId", source = "IDCart.id")
    @Mapping(target = "passportId", source = "passport.id")
    ApiResidencePermitResponse toApiResponse(ResidencePermit residencePermit);

    List<ApiResidencePermitResponse> toApiResponses(List<ResidencePermit> residencePermits);
}

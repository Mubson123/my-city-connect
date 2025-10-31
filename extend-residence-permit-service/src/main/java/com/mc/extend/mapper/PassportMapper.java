package com.mc.extend.mapper;

import com.mc.extend.model.ApiPassportRequest;
import com.mc.extend.model.ApiPassportResponse;
import com.mc.extend.model.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PassportMapper {

    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Passport toPassport(ApiPassportRequest apiPassportRequest);

    ApiPassportResponse toApiResponse(Passport passport);

    List<ApiPassportResponse> toApiResponses(List<Passport> passports);
}

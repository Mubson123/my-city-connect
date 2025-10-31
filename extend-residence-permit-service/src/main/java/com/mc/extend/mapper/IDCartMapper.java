package com.mc.extend.mapper;

import com.mc.extend.model.ApiIDCartRequest;
import com.mc.extend.model.ApiIDCartResponse;
import com.mc.extend.model.IdentificationCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IDCartMapper {

    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    IdentificationCart toDICart(ApiIDCartRequest apiIDCartRequest);

    @Mapping(target = "iDCartNumber", source = "IDCartNumber")
    ApiIDCartResponse toApiResponse(IdentificationCart identificationCart);

    List<ApiIDCartResponse> toApiResponses(List<IdentificationCart> identificationCarts);
}

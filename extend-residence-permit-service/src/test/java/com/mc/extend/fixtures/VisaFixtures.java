package com.mc.extend.fixtures;

import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import com.mc.extend.model.ApiVisaType;
import com.mc.extend.model.Visa;
import com.mc.extend.model.utils.VisaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class VisaFixtures {

    public static UUID visaId1 = UUID.randomUUID();
    public static UUID visaId2 = UUID.randomUUID();

    public static Visa visa1 = Visa.builder()
            .id(visaId1)
            .visaNumber("FR123A456T890")
            .visaType(VisaType.MISSION)
            .countryOfIssue("France")
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(LocalDateTime.of(2024, 5, 12, 9, 45))
            .issuedAt(LocalDate.of(2024, 1, 10))
            .expiresAt(LocalDate.of(2034, 5, 12))
            .build();

    public static Visa visa2 = Visa.builder()
            .id(visaId2)
            .visaNumber("DE715Z487Y27")
            .visaType(VisaType.STUDY)
            .countryOfIssue("Germany")
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20))
            .updatedAt(LocalDateTime.of(2024, 6, 7, 16, 30))
            .issuedAt(LocalDate.of(2024, 2, 5))
            .expiresAt(LocalDate.of(2034, 6, 7))
            .build();

    public static List<Visa> visaList = List.of(visa1, visa2);

    public static ApiVisaRequest request1 = new ApiVisaRequest()
            .visaNumber("FR123A456T890")
            .visaType(ApiVisaType.MISSION)
            .countryOfIssue("France")
            .issuedAt(LocalDate.of(2024, 1, 10))
            .expiresAt(LocalDate.of(2034, 5, 12));

    public static ApiVisaRequest request2 = new ApiVisaRequest()
            .visaNumber("DE715Z487Y27")
            .visaType(ApiVisaType.STUDY)
            .countryOfIssue("Germany")
            .issuedAt(LocalDate.of(2024, 2, 5))
            .expiresAt(LocalDate.of(2034, 6, 7));

    public static ApiVisaRequest request2Updated = new ApiVisaRequest()
            .visaNumber("DE8594H804")
            .visaType(ApiVisaType.STUDY)
            .countryOfIssue("Germany")
            .issuedAt(LocalDate.of(2025, 10, 11))
            .expiresAt(LocalDate.of(2035, 10, 10));

    public static ApiVisaResponse response1 = new ApiVisaResponse()
            .id(visaId1)
            .visaNumber("FR123A456T890")
            .visaType(ApiVisaType.MISSION)
            .countryOfIssue("France")
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .updatedAt(LocalDateTime.of(2024, 5, 12, 9, 45).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .issuedAt(LocalDate.of(2024, 1, 10))
            .expiresAt(LocalDate.of(2034, 5, 12));

    public static ApiVisaResponse response2 = new ApiVisaResponse()
            .id(visaId2)
            .visaNumber("DE715Z487Y27")
            .visaType(ApiVisaType.STUDY)
            .countryOfIssue("Germany")
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .updatedAt(LocalDateTime.of(2024, 6, 7, 16, 30).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .issuedAt(LocalDate.of(2024, 2, 5))
            .expiresAt(LocalDate.of(2034, 6, 7));

    public static ApiVisaResponse response2Updated = new ApiVisaResponse()
            .id(visaId2)
            .visaNumber("DE8594H804")
            .visaType(ApiVisaType.STUDY)
            .countryOfIssue("Germany")
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .updatedAt(LocalDateTime.of(2025, 10, 11, 16, 30).atZone(java.time.ZoneId.systemDefault()).toOffsetDateTime())
            .issuedAt(LocalDate.of(2025, 10, 11))
            .expiresAt(LocalDate.of(2035, 10, 10));

    public static List<ApiVisaResponse> responseList = List.of(response1, response2);

    public static Visa invalidVisa = Visa.builder()
            .id(visaId1)
            .visaNumber("")
            .visaType(VisaType.MISSION)
            .countryOfIssue("")
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(null)
            .issuedAt(LocalDate.of(2024, 1, 10))
            .expiresAt(LocalDate.of(2034, 5, 12))
            .build();

    public static ApiVisaRequest badRequest = new ApiVisaRequest()
            .visaNumber("")
            .visaType(ApiVisaType.STUDY)
            .countryOfIssue("Germany")
            .issuedAt(LocalDate.of(2024, 2, 5))
            .expiresAt(LocalDate.of(2034, 6, 7));
}

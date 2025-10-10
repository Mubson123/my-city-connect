package com.mc.citizen.fixtures;

import com.mc.citizen.model.*;
import com.mc.citizen.model.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CitizenFixtures {

    public static UUID citizenId1 = UUID.randomUUID();
    public static UUID citizenId2 = UUID.randomUUID();
    public static Citizen citizen1 = Citizen.builder()
            .id(citizenId1)
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(LocalDateTime.of(2024, 5, 12, 9, 45))
            .titles(Set.of(Title.DR))
            .gender(Gender.MALE)
            .firstName("Jean")
            .lastName("Dupont")
            .birthDate(LocalDate.of(1985, 3, 14))
            .birthPlace("Paris")
            .email("jean.dupont@example.com")
            .phones(Set.of(new Phone("0176123456", PhoneType.MOBILE)))
            .maritalStatus(MaritalStatus.MARRIED)
            .addresses(Set.of(new Address(AddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris")))
            .build();

    public static Citizen citizen2 = Citizen.builder()
            .id(citizenId2)
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20))
            .updatedAt(LocalDateTime.of(2024, 6, 7, 16, 30))
            .titles(Set.of(Title.PROF, Title.ING))
            .gender(Gender.FEMALE)
            .firstName("Maria")
            .lastName("Schneider")
            .birthDate(LocalDate.of(1990, 8, 25))
            .birthPlace("Nürnberg")
            .email("maria.schneider@example.de")
            .phones(Set.of(new Phone("0911234567", PhoneType.WORK)))
            .maritalStatus(MaritalStatus.SINGLE)
            .addresses(Set.of(
                    new Address(AddressTyp.MAIN, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.SECONDARY, "Blumenweg 5", "91301", "Forchheim")
            ))
            .build();

    public static List<Citizen> citizenList = List.of(citizen1, citizen2);

    public static Citizen updatedCitizen1 = Citizen.builder()
            .id(citizenId1)
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(LocalDateTime.now())
            .titles(Set.of(Title.DR))
            .gender(Gender.MALE)
            .firstName("Jean")
            .lastName("Dupont")
            .birthDate(LocalDate.of(1985, 3, 14))
            .birthPlace("Paris")
            .email("dupont123jean@gmail.com")
            .phones(Set.of(new Phone("0176586224", PhoneType.MOBILE)))
            .maritalStatus(MaritalStatus.MARRIED)
            .addresses(Set.of(new Address(AddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris")))
            .build();

    public static ApiCitizenRequest request1 = new ApiCitizenRequest(
            List.of(ApiTitle.DR),
            ApiGender.MALE,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "jean.dupont@example.com",
            List.of(new ApiPhone("0176123456", ApiPhoneType.MOBILE)),
            ApiMaritalStatus.MARRIED,
            List.of(new ApiAddress(ApiAddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static ApiCitizenRequest request2 = new ApiCitizenRequest(
            List.of(ApiTitle.PROF, ApiTitle.ING),
            ApiGender.FEMALE,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "Nürnberg",
            "maria.schneider@example.de",
            List.of(new ApiPhone("0911234567", ApiPhoneType.WORK)),
            ApiMaritalStatus.SINGLE,
            List.of(
                    new ApiAddress(ApiAddressTyp.MAIN, "Königstrasse 22", "90402", "Nürnberg"),
                    new ApiAddress(ApiAddressTyp.SECONDARY, "Blumenweg 5", "91301", "Forchheim")
            )
    );

    public static List<ApiCitizenRequest> requestList = List.of(request1, request2);

    public static ApiCitizenRequest updatedRequest1 = new ApiCitizenRequest(
            List.of(ApiTitle.DR),
            ApiGender.MALE,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "dupont123jean@gmail.com",
            List.of(new ApiPhone("0176586224", ApiPhoneType.MOBILE)),
            ApiMaritalStatus.MARRIED,
            List.of(new ApiAddress(ApiAddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static ApiCitizenResponse response1 = new ApiCitizenResponse(
            citizenId1,
            LocalDateTime.of(2024, 1, 10, 14, 30).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            LocalDateTime.of(2024, 5, 12, 9, 45).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            List.of(ApiTitle.DR),
            ApiGender.MALE,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "jean.dupont@example.com",
            List.of(new ApiPhone("0176123456", ApiPhoneType.MOBILE)),
            ApiMaritalStatus.MARRIED,
            List.of(new ApiAddress(ApiAddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static ApiCitizenResponse response2 = new ApiCitizenResponse(
            citizenId2,
            LocalDateTime.of(2024, 2, 5, 10, 20).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            LocalDateTime.of(2024, 6, 7, 16, 30).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            List.of(ApiTitle.PROF, ApiTitle.ING),
            ApiGender.FEMALE,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "Nürnberg",
            "maria.schneider@example.de",
            List.of(new ApiPhone("0911234567", ApiPhoneType.WORK)),
            ApiMaritalStatus.SINGLE,
            List.of(
                    new ApiAddress(ApiAddressTyp.MAIN, "Königstrasse 22", "90402", "Nürnberg"),
                    new ApiAddress(ApiAddressTyp.SECONDARY, "Blumenweg 5", "91301", "Forchheim")
            )
    );

    public static List<ApiCitizenResponse> responseList = List.of(response1, response2);

    public static ApiCitizenResponse updateResponse1 = new ApiCitizenResponse(
            citizenId1,
            LocalDateTime.of(2024, 1, 10, 14, 30).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            LocalDateTime.of(2024, 5, 12, 9, 45).atZone(ZoneId.systemDefault()).toOffsetDateTime(),
            List.of(ApiTitle.DR),
            ApiGender.MALE,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "dupont123jean@gmail.com",
            List.of(new ApiPhone("0176586224", ApiPhoneType.MOBILE)),
            ApiMaritalStatus.MARRIED,
            List.of(new ApiAddress(ApiAddressTyp.MAIN, "12 Rue Victor Hugo", "75001", "Paris"))
    );
}

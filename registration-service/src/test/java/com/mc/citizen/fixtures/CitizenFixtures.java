package com.mc.citizen.fixtures;

import com.mc.citizen.model.dto.CitizenRequestDto;
import com.mc.citizen.model.dto.CitizenResponseDto;
import com.mc.citizen.model.*;
import com.mc.citizen.model.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            .titles(Set.of(Title.Dr))
            .gender(Gender.Male)
            .firstName("Jean")
            .lastName("Dupont")
            .birthDate(LocalDate.of(1985, 3, 14))
            .birthPlace("Paris")
            .email("jean.dupont@example.com")
            .phoneNumbers(Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)))
            .maritalStatus(MaritalStatus.MARRIED)
            .addresses(Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris")))
            .build();

    public static Citizen citizen2 = Citizen.builder()
            .id(citizenId2)
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20))
            .updatedAt(LocalDateTime.of(2024, 6, 7, 16, 30))
            .titles(Set.of(Title.Prof, Title.Ing))
            .gender(Gender.Female)
            .firstName("Maria")
            .lastName("Schneider")
            .birthDate(LocalDate.of(1990, 8, 25))
            .birthPlace("Nürnberg")
            .email("maria.schneider@example.de")
            .phoneNumbers(Set.of(new PhoneNumber("0911234567", PhoneType.Work)))
            .maritalStatus(MaritalStatus.SINGLE)
            .addresses(Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            ))
            .build();

    public static List<Citizen> citizenList = List.of(citizen1, citizen2);

    public static Citizen updatedCitizen1 = Citizen.builder()
            .id(citizenId1)
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(LocalDateTime.now())
            .titles(Set.of(Title.Dr))
            .gender(Gender.Male)
            .firstName("Jean")
            .lastName("Dupont")
            .birthDate(LocalDate.of(1985, 3, 14))
            .birthPlace("Paris")
            .email("dupont123jean@gmail.com")
            .phoneNumbers(Set.of(new PhoneNumber("0176586224", PhoneType.Mobile)))
            .maritalStatus(MaritalStatus.MARRIED)
            .addresses(Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris")))
            .build();

    public static CitizenRequestDto request1 = new CitizenRequestDto(
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "jean.dupont@example.com",
            Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static CitizenRequestDto request2 = new CitizenRequestDto(
            Set.of(Title.Prof, Title.Ing),
            Gender.Female,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "Nürnberg",
            "maria.schneider@example.de",
            Set.of(new PhoneNumber("0911234567", PhoneType.Work)),
            MaritalStatus.SINGLE,
            Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            )
    );

    public static List<CitizenRequestDto> requestList = List.of(request1, request2);

    public static CitizenRequestDto updatedRequest1 = new CitizenRequestDto(
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "dupont123jean@gmail.com",
            Set.of(new PhoneNumber("0176586224", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static CitizenResponseDto response1 = new CitizenResponseDto(
            citizenId1,
            LocalDateTime.of(2024, 1, 10, 14, 30),
            LocalDateTime.of(2024, 5, 12, 9, 45),
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "jean.dupont@example.com",
            Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris"))
    );

    public static CitizenResponseDto response2 = new CitizenResponseDto(
            citizenId2,
            LocalDateTime.of(2024, 2, 5, 10, 20),
            LocalDateTime.of(2024, 6, 7, 16, 30),
            Set.of(Title.Prof, Title.Ing),
            Gender.Female,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "Nürnberg",
            "maria.schneider@example.de",
            Set.of(new PhoneNumber("0911234567", PhoneType.Work)),
            MaritalStatus.SINGLE,
            Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            )
    );

    public static List<CitizenResponseDto> responseList = List.of(response1, response2);

    public static CitizenResponseDto updateResponse1 = new CitizenResponseDto(
            citizenId1,
            LocalDateTime.of(2024, 1, 10, 14, 30),
            LocalDateTime.of(2024, 5, 12, 9, 45),
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "Paris",
            "dupont123jean@gmail.com",
            Set.of(new PhoneNumber("0176586224", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris"))
    );
}

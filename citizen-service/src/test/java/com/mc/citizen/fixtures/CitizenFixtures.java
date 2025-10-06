package com.mc.citizen.fixtures;

import com.mc.citizen.dto.CitizenRequestDto;
import com.mc.citizen.dto.CitizenResponseDto;
import com.mc.citizen.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CitizenFixtures {
    public static Citizen citizen1 = Citizen.builder()
            .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
            .createdAt(LocalDateTime.of(2024, 1, 10, 14, 30))
            .updatedAt(LocalDateTime.of(2024, 5, 12, 9, 45))
            .titles(Set.of(Title.Dr))
            .gender(Gender.Male)
            .firstName("Jean")
            .lastName("Dupont")
            .birthDate(LocalDate.of(1985, 3, 14))
            .email("jean.dupont@example.com")
            .phoneNumbers(Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)))
            .maritalStatus(MaritalStatus.MARRIED)
            .addresses(Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris")))
            .identyDocuments(Set.of(new Identification(IDType.Card, "FR1234567", LocalDate.of(2020, 1, 1), LocalDate.of(2030, 1, 1))))
            .build();

    public static Citizen citizen2 = Citizen.builder()
            .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
            .createdAt(LocalDateTime.of(2024, 2, 5, 10, 20))
            .updatedAt(LocalDateTime.of(2024, 6, 7, 16, 30))
            .titles(Set.of(Title.Prof, Title.Ing))
            .gender(Gender.Female)
            .firstName("Maria")
            .lastName("Schneider")
            .birthDate(LocalDate.of(1990, 8, 25))
            .email("maria.schneider@example.de")
            .phoneNumbers(Set.of(new PhoneNumber("0911234567", PhoneType.Work)))
            .maritalStatus(MaritalStatus.SINGLE)
            .addresses(Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            ))
            .identyDocuments(Set.of(new Identification(IDType.Passport, "D9876543", LocalDate.of(2021, 5, 10), LocalDate.of(2031, 5, 10))))
            .build();

    public static List<Citizen> citizenList = List.of(citizen1, citizen2);

    public static CitizenRequestDto request1 = new CitizenRequestDto(
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "jean.dupont@example.com",
            Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris")),
            Set.of(new Identification(IDType.Card, "FR1234567", LocalDate.of(2020, 1, 1), LocalDate.of(2030, 1, 1))),
            "FR-85-03-14-1234",
            "FR-TAX-98765"
    );

    public static CitizenRequestDto request2 = new CitizenRequestDto(
            Set.of(Title.Prof, Title.Ing),
            Gender.Female,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "maria.schneider@example.de",
            Set.of(new PhoneNumber("0911234567", PhoneType.Work)),
            MaritalStatus.SINGLE,
            Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            ),
            Set.of(new Identification(IDType.Passport, "D9876543", LocalDate.of(2021, 5, 10), LocalDate.of(2031, 5, 10))),
            "DE-90-08-25-5678",
            "DE-TAX-45678"
    );

    public static List<CitizenRequestDto> requestList = List.of(request1, request2);

    public static CitizenResponseDto response1 = new CitizenResponseDto(
            UUID.fromString("11111111-1111-1111-1111-111111111111"),
            LocalDateTime.of(2024, 1, 10, 14, 30),
            LocalDateTime.of(2024, 5, 12, 9, 45),
            Set.of(Title.Dr),
            Gender.Male,
            "Jean",
            "Dupont",
            LocalDate.of(1985, 3, 14),
            "jean.dupont@example.com",
            Set.of(new PhoneNumber("0176123456", PhoneType.Mobile)),
            MaritalStatus.MARRIED,
            Set.of(new Address(AddressTyp.Main, "12 Rue Victor Hugo", "75001", "Paris")),
            Set.of(new Identification(IDType.Card, "FR1234567", LocalDate.of(2020, 1, 1), LocalDate.of(2030, 1, 1))),
            "FR-85-03-14-1234",
            "FR-TAX-98765"
    );

    public static CitizenResponseDto response2 = new CitizenResponseDto(
            UUID.fromString("22222222-2222-2222-2222-222222222222"),
            LocalDateTime.of(2024, 2, 5, 10, 20),
            LocalDateTime.of(2024, 6, 7, 16, 30),
            Set.of(Title.Prof, Title.Ing),
            Gender.Female,
            "Maria",
            "Schneider",
            LocalDate.of(1990, 8, 25),
            "maria.schneider@example.de",
            Set.of(new PhoneNumber("0911234567", PhoneType.Work)),
            MaritalStatus.SINGLE,
            Set.of(
                    new Address(AddressTyp.Main, "Königstrasse 22", "90402", "Nürnberg"),
                    new Address(AddressTyp.Secondary, "Blumenweg 5", "91301", "Forchheim")
            ),
            Set.of(new Identification(IDType.Passport, "D9876543", LocalDate.of(2021, 5, 10), LocalDate.of(2031, 5, 10))),
            "DE-90-08-25-5678",
            "DE-TAX-45678"
    );

    public static List<CitizenResponseDto> responseList = List.of(response1, response2);
}

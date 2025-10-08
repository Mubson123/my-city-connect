package com.mc.citizen.service;

import com.mc.citizen.exception.CitizenAlreadyExistsByEmailException;
import com.mc.citizen.model.dto.CitizenRequestDto;
import com.mc.citizen.model.dto.CitizenResponseDto;
import com.mc.citizen.exception.CitizenNotFoundException;
import com.mc.citizen.fixtures.CitizenFixtures;
import com.mc.citizen.mapper.CitizenMapperImpl;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.repository.CitizenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {
    @Mock
    private CitizenRepository citizenRepository;
    @Mock
    private CitizenMapperImpl citizenMapper;
    @InjectMocks
    private CitizenServiceImpl citizenService;


    @Test
    void shouldReturnAllGivenCitizensSuccessfully() {
        int from = 0;
        int until = 2;
        List<Citizen> citizens = CitizenFixtures.citizenList;
        List<CitizenResponseDto> expected = CitizenFixtures.responseList;
        Page<Citizen> page = new PageImpl<>(citizens);
        when(citizenRepository.findAll(PageRequest.of(from, until))).thenReturn(page);
        when(citizenMapper.toResponseDtos(citizens)).thenReturn(expected);

        List<CitizenResponseDto> actual = citizenService.getCitizens(from, until);
        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals("Jean", actual.get(0).firstName());
        assertEquals("Maria", actual.get(1).firstName());

        verify(citizenRepository, times(1)).findAll(PageRequest.of(0, 2));
        verify(citizenMapper, times(1)).toResponseDtos(citizens);
    }

    @Test
    void shouldThrowExceptionWhenFromNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenService.getCitizens(-1, 10));
        assertEquals("Illegal Argument 'from' or 'until'", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUntilIsZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenService.getCitizens(0, 0));
        assertEquals("'from' must be smaller than 'until'", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenFromGreaterThanUntil() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenService.getCitizens(6, 4));
        assertEquals("'from' must be smaller than 'until'", ex.getMessage());
    }

    @Test
    void shouldReturnAllCitizenByIdSuccessfully() {
        Citizen citizen = CitizenFixtures.citizen1;
        CitizenResponseDto expect = CitizenFixtures.response1;
        when(citizenRepository.findById(citizen.getId())).thenReturn(Optional.of(citizen));
        when(citizenMapper.toResponseDto(citizen)).thenReturn(expect);

        CitizenResponseDto actual = citizenService.getCitizenById(citizen.getId());
        assertNotNull(actual);
        assertEquals(expect, actual);
        verify(citizenRepository, times(1)).findById(citizen.getId());
        verify(citizenMapper, times(1)).toResponseDto(citizen);
    }

    @Test
    void shouldThrowExceptionWhenCitizenNotFound() {
        UUID id = UUID.randomUUID();
        CitizenNotFoundException ex = assertThrows(CitizenNotFoundException.class,
                () -> citizenService.getCitizenById(id));
        assertEquals(String.format("Citizen with ID '%s' not found", id), ex.getMessage());
    }

    @Test
    void shouldCreateCitizenSuccessfully() {
        CitizenRequestDto requestDto = CitizenFixtures.request2;
        Citizen citizen = CitizenFixtures.citizen2;
        CitizenResponseDto expected = CitizenFixtures.response2;
        when(citizenRepository.existsByEmail(citizen.getEmail())).thenReturn(false);
        when(citizenMapper.toEntity(requestDto)).thenReturn(citizen);
        when(citizenRepository.save(citizen)).thenReturn(citizen);
        when(citizenMapper.toResponseDto(citizen)).thenReturn(expected);

        CitizenResponseDto actual = citizenService.createCitizen(requestDto);
        assertNotNull(actual);
        assertEquals(expected, actual);
        verify(citizenRepository, times(1)).existsByEmail(citizen.getEmail());
        verify(citizenMapper, times(1)).toEntity(requestDto);
        verify(citizenMapper, times(1)).toResponseDto(citizen);
        verify(citizenRepository, times(1)).save(citizen);
    }

    @Test
    void shouldCreateCitizenThrowExceptionWhenEmailAlreadyExists() {
        CitizenRequestDto citizenRequestDto = CitizenFixtures.request2;
        when(citizenRepository.existsByEmail(citizenRequestDto.email())).thenReturn(true);

        CitizenAlreadyExistsByEmailException ex = assertThrows(CitizenAlreadyExistsByEmailException.class,
                () -> citizenService.createCitizen(citizenRequestDto));
        assertEquals(String.format("Citizen with email '%s' already exists", citizenRequestDto.email()), ex.getMessage());
    }

    @Test
    void shouldUpdateCitizenSuccessfully() {
        UUID citizenId = CitizenFixtures.citizenId1;
        Citizen citizen = CitizenFixtures.citizen1;
        Citizen updatedCitizen = CitizenFixtures.updatedCitizen1;
        CitizenRequestDto citizenRequestDto = CitizenFixtures.updatedRequest1;
        CitizenResponseDto expected = CitizenFixtures.response1;

        when(citizenRepository.findById(citizenId)).thenReturn(Optional.of(citizen));
        when(citizenMapper.toEntity(citizenRequestDto)).thenReturn(updatedCitizen);
        when(citizenRepository.save(updatedCitizen)).thenReturn(updatedCitizen);
        when(citizenMapper.toResponseDto(updatedCitizen)).thenReturn(expected);

        CitizenResponseDto actual = citizenService.updateCitizen(citizenId, citizenRequestDto);
        assertNotNull(actual);
        assertEquals(expected.email(), actual.email());
        assertEquals(expected.phones(), actual.phones());
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteCitizenSuccessfully() {
        UUID citizenId = CitizenFixtures.citizenId2;
        Citizen citizen = CitizenFixtures.citizen2;
        when(citizenRepository.findById(citizenId)).thenReturn(Optional.of(citizen));
        doNothing().when(citizenRepository).delete(citizen);

        citizenService.deleteCitizen(citizenId);
        verify(citizenRepository, times(1)).delete(citizen);
    }
}

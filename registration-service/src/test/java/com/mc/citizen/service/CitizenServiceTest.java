package com.mc.citizen.service;

import com.mc.citizen.exception.CitizenAlreadyExistsByEmailException;
import com.mc.citizen.exception.CitizenNotFoundException;
import com.mc.citizen.fixtures.CitizenFixtures;
import com.mc.citizen.kafka.KafkaProducerService;
import com.mc.citizen.mapper.CitizenMapperImpl;
import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.repository.CitizenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Mock
    private KafkaProducerService kafkaProducerService;
    @InjectMocks
    private CitizenServiceImpl citizenService;


    @Test
    void shouldReturnAllCitizensSuccessfully() {
        List<Citizen> citizens = CitizenFixtures.citizenList;
        List<ApiCitizenResponse> expected = CitizenFixtures.responseList;
        when(citizenRepository.findAll()).thenReturn(citizens);
        when(citizenMapper.toApiResponses(citizens)).thenReturn(expected);

        List<ApiCitizenResponse> actual = citizenService.getCitizens();
        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals("Jean", actual.get(0).getFirstName());
        assertEquals("Maria", actual.get(1).getFirstName());

        verify(citizenRepository, times(1)).findAll();
        verify(citizenMapper, times(1)).toApiResponses(citizens);
    }

    @Test
    void shouldReturnAllCitizenByIdSuccessfully() {
        Citizen citizen = CitizenFixtures.citizen1;
        ApiCitizenResponse expect = CitizenFixtures.response1;
        when(citizenRepository.findById(citizen.getId())).thenReturn(Optional.of(citizen));
        when(citizenMapper.toApiResponse(citizen)).thenReturn(expect);

        ApiCitizenResponse actual = citizenService.getCitizenById(citizen.getId());
        assertNotNull(actual);
        assertEquals(expect, actual);
        verify(citizenRepository, times(1)).findById(citizen.getId());
        verify(citizenMapper, times(1)).toApiResponse(citizen);
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
        ApiCitizenRequest citizenRequest = CitizenFixtures.request2;
        Citizen citizen = CitizenFixtures.citizen2;
        ApiCitizenResponse expected = CitizenFixtures.response2;
        when(citizenRepository.existsByEmail(citizen.getEmail())).thenReturn(false);
        when(citizenMapper.toCitizen(citizenRequest)).thenReturn(citizen);
        when(citizenRepository.save(citizen)).thenReturn(citizen);
        doNothing().when(kafkaProducerService).sendEvent(citizen, "CITIZEN_CREATED");
        when(citizenMapper.toApiResponse(citizen)).thenReturn(expected);

        ApiCitizenResponse actual = citizenService.createCitizen(citizenRequest);
        assertNotNull(actual);
        assertEquals(expected, actual);
        verify(citizenRepository, times(1)).existsByEmail(citizen.getEmail());
        verify(citizenMapper, times(1)).toCitizen(citizenRequest);
        verify(citizenMapper, times(1)).toApiResponse(citizen);
        verify(citizenRepository, times(1)).save(citizen);
    }

    @Test
    void shouldCreateCitizenThrowExceptionWhenEmailAlreadyExists() {
        ApiCitizenRequest citizenRequest = CitizenFixtures.request2;
        String email = citizenRequest.getEmail();
        when(citizenRepository.existsByEmail(email)).thenReturn(true);

        CitizenAlreadyExistsByEmailException ex = assertThrows(CitizenAlreadyExistsByEmailException.class,
                () -> citizenService.createCitizen(citizenRequest));
        assertEquals(String.format("Citizen with email '%s' already exists", email), ex.getMessage());
    }

    @Test
    void shouldUpdateCitizenSuccessfully() {
        UUID citizenId = CitizenFixtures.citizenId1;
        Citizen citizen = CitizenFixtures.citizen1;
        Citizen updatedCitizen = CitizenFixtures.updatedCitizen1;
        ApiCitizenRequest citizenRequest = CitizenFixtures.updatedRequest1;
        ApiCitizenResponse expected = CitizenFixtures.response1;

        when(citizenRepository.findById(citizenId)).thenReturn(Optional.of(citizen));
        when(citizenMapper.toCitizen(citizenRequest)).thenReturn(updatedCitizen);
        when(citizenRepository.save(updatedCitizen)).thenReturn(updatedCitizen);
        doNothing().when(kafkaProducerService).sendEvent(updatedCitizen, "CITIZEN_UPDATED");
        when(citizenMapper.toApiResponse(updatedCitizen)).thenReturn(expected);

        ApiCitizenResponse actual = citizenService.updateCitizen(citizenId, citizenRequest);
        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhones(), actual.getPhones());
        assertEquals(expected, actual);
        verify(citizenRepository, times(1)).findById(citizenId);
        verify(citizenMapper, times(1)).toCitizen(citizenRequest);
        verify(citizenRepository, times(1)).save(updatedCitizen);
        verify(citizenMapper, times(1)).toApiResponse(updatedCitizen);
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

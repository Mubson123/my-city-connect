package com.mc.extend.service;

import com.mc.extend.exception.VisaNotFoundException;
import com.mc.extend.fixtures.VisaFixtures;
import com.mc.extend.mapper.VisaMapper;
import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import com.mc.extend.model.Visa;
import com.mc.extend.repository.VisaRepository;
import jakarta.validation.ConstraintViolationException;
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
class VisaServiceTest {
    @Mock
    private VisaRepository visaRepository;
    @Mock
    private VisaMapper visaMapper;
    @InjectMocks
    private VisaServiceImpl visaService;

    @Test
    void shouldReturnAllGivenVisasSuccessfully() {
        List<Visa> visas = VisaFixtures.visaList;
        List<ApiVisaResponse> expected = VisaFixtures.responseList;
        when(visaRepository.findAll()).thenReturn(visas);
        when(visaMapper.toApiResponses(visas)).thenReturn(expected);

        List<ApiVisaResponse> actual = visaService.getVisas();
        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals(expected, actual);

        verify(visaRepository, times(1)).findAll();
        verify(visaMapper, times(1)).toApiResponses(visas);
    }

    @Test
    void shouldReturnVisaByIdSuccessfully() {
        Visa visa = VisaFixtures.visa1;
        ApiVisaResponse expected = VisaFixtures.response1;
        when(visaRepository.findById(visa.getId())).thenReturn(Optional.of(visa));
        when(visaMapper.toApiResponse(visa)).thenReturn(expected);

        ApiVisaResponse actual = visaService.getVisaById(visa.getId());
        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(visaRepository, times(1)).findById(visa.getId());
        verify(visaMapper, times(1)).toApiResponse(visa);
    }

    @Test
    void shouldThrowExceptionWhenVisaNotFound() {
        UUID randomId = UUID.randomUUID();
        VisaNotFoundException ex = assertThrows(VisaNotFoundException.class,
                () -> visaService.getVisaById(randomId));
        assertEquals(String.format("Visa with ID '%s' not found", randomId), ex.getMessage());
    }

    @Test
    void shouldGrantVisaSuccessfully() {
        ApiVisaRequest visaRequest = VisaFixtures.request1;
        Visa visa = VisaFixtures.visa1;
        ApiVisaResponse expected = VisaFixtures.response1;
        when(visaMapper.toVisa(visaRequest)).thenReturn(visa);
        when(visaRepository.save(visa)).thenReturn(visa);
        when(visaMapper.toApiResponse(visa)).thenReturn(expected);

        ApiVisaResponse actual = visaService.createVisa(visaRequest);
        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(visaMapper, times(1)).toVisa(visaRequest);
        verify(visaRepository, times(1)).save(visa);
        verify(visaMapper, times(1)).toApiResponse(visa);
    }

    @Test
    void shouldGrantVisaUnsuccessfully() {
        ApiVisaRequest badRequest = VisaFixtures.badRequest;
        when(visaMapper.toVisa(badRequest)).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class,
                () -> visaService.createVisa(badRequest));
        verify(visaMapper, times(1)).toVisa(badRequest);
        verifyNoInteractions(visaRepository);
    }

    @Test
    void shouldUpdateVisaSuccessfully() {
        UUID visaId = VisaFixtures.visaId2;
        Visa visa = VisaFixtures.visa2;
        ApiVisaRequest visaRequest = VisaFixtures.request2Updated;
        ApiVisaResponse expected = VisaFixtures.response2Updated;
        when(visaRepository.findById(visaId)).thenReturn(Optional.of(visa));
        when(visaMapper.toVisa(visaRequest)).thenReturn(visa);
        when(visaRepository.save(visa)).thenReturn(visa);
        when(visaMapper.toApiResponse(visa)).thenReturn(expected);

        ApiVisaResponse actual = visaService.updateVisa(visaId, visaRequest);
        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(visaRepository, times(1)).findById(visaId);
        verify(visaMapper, times(1)).toVisa(visaRequest);
        verify(visaRepository, times(1)).save(visa);
        verify(visaMapper, times(1)).toApiResponse(visa);
    }

    @Test
    void shouldDeleteVisaSuccessfully() {
        UUID visaId = VisaFixtures.visaId2;
        Visa visa = VisaFixtures.visa2;
        when(visaRepository.findById(visaId)).thenReturn(Optional.of(visa));
        doNothing().when(visaRepository).delete(visa);
        visaService.deleteVisa(visaId);
        verify(visaRepository, times(1)).delete(visa);
    }
}

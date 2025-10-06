package com.mc.citizen.service;

import com.mc.citizen.dto.CitizenResponseDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {
    @Mock
    private CitizenRepository citizenRepository;
    @Mock
    private CitizenMapperImpl  citizenMapper;
    @InjectMocks
    private CitizenServiceImpl citizenService;


    @Test
    void shouldReturnAllGivenCitizens() {
        int from = 0;
        int until = 2;
        List<Citizen>  citizens = CitizenFixtures.citizenList;
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
}

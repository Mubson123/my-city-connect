package com.mc.citizen.service;

import com.mc.citizen.dto.CitizenResponseDto;
import com.mc.citizen.mapper.CitizenMapper;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @Override
    public List<CitizenResponseDto> getCitizens(int from, int until) {
        if (from < 0 || until == 0) {
            throw new IllegalArgumentException("Illegal Argument 'from' or 'until'");
        }
        else if (from >= until) {
            throw new IllegalArgumentException("'from' must be smaller than 'until'");
        }
        List<Citizen> citizens =  citizenRepository.findAll(PageRequest.of(from, until)).getContent();
        return citizenMapper.toResponseDtos(citizens);
    }
}

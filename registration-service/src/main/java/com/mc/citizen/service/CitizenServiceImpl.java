package com.mc.citizen.service;

import com.mc.citizen.exception.CitizenAlreadyExistsByEmailException;
import com.mc.citizen.model.dto.CitizenRequestDto;
import com.mc.citizen.model.dto.CitizenResponseDto;
import com.mc.citizen.exception.CitizenNotFoundException;
import com.mc.citizen.mapper.CitizenMapper;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @Override
    public List<CitizenResponseDto> getCitizens(int from, int until) {
        if (from < 0) {
            throw new IllegalArgumentException("Illegal Argument 'from' or 'until'");
        } else if (from >= until) {
            throw new IllegalArgumentException("'from' must be smaller than 'until'");
        }
        List<Citizen> citizens = citizenRepository.findAll(PageRequest.of(from, until)).getContent();
        return citizenMapper.toResponseDtos(citizens);
    }

    @Override
    public CitizenResponseDto getCitizenById(UUID citizenId) {
        Citizen citizen = citizenRepository
                        .findById(citizenId)
                        .orElseThrow(() -> new CitizenNotFoundException(
                                                String.format("Citizen with ID '%s' not found", citizenId)));
        return citizenMapper.toResponseDto(citizen);
    }

    @Override
    public CitizenResponseDto createCitizen(CitizenRequestDto citizenRequestDto) {
        boolean citizenExistsById = citizenRepository.existsByEmail(citizenRequestDto.email());
        if (citizenExistsById) {
            throw new CitizenAlreadyExistsByEmailException("Citizen with email '" + citizenRequestDto.email() + "' already exists");
        }
        Citizen citizen = citizenMapper.toEntity(citizenRequestDto);
        citizen = citizenRepository.save(citizen);
        return citizenMapper.toResponseDto(citizen);
    }

    @Override
    public CitizenResponseDto updateCitizen(UUID citizenId, CitizenRequestDto citizenRequestDto) {
        Citizen citizen = citizenRepository
                        .findById(citizenId)
                        .orElseThrow(() -> new CitizenNotFoundException(
                                                String.format("Citizen with ID '%s' not found", citizenId)));
        Citizen updatedCitizen = citizenMapper.toEntity(citizenRequestDto);
        updatedCitizen.setId(citizenId);
        updatedCitizen.setCreatedAt(citizen.getCreatedAt());
        updatedCitizen = citizenRepository.save(updatedCitizen);
        return citizenMapper.toResponseDto(updatedCitizen);
    }

    @Override
    public void deleteCitizen(UUID citizenId) {
        Citizen citizen = citizenRepository
                .findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(
                        String.format("Citizen with ID '%s' not found", citizenId)));
        citizenRepository.delete(citizen);
    }
}

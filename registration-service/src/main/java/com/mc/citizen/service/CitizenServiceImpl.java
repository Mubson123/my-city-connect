package com.mc.citizen.service;

import com.mc.citizen.exception.CitizenAlreadyExistsByEmailException;
import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import com.mc.citizen.exception.CitizenNotFoundException;
import com.mc.citizen.mapper.CitizenMapper;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
    private static final String NOT_FOUND = "Citizen with ID '%s' not found";
    public static final String EMAIL_ALREADY_EXISTS = "Citizen with email '%s' already exists";

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @Override
    public List<ApiCitizenResponse> getCitizens(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit should be greater than 0");
        }
        List<Citizen> citizens = citizenRepository.findAll(Pageable.ofSize(limit)).getContent();
        return citizenMapper.toApiResponses(citizens);
    }

    @Override
    public ApiCitizenResponse getCitizenById(UUID citizenId) {
        Citizen citizen = citizenRepository
                .findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(
                        String.format(NOT_FOUND, citizenId)));
        return citizenMapper.toApiResponse(citizen);
    }

    @Override
    public ApiCitizenResponse createCitizen(ApiCitizenRequest citizenRequest) {
        final String email = citizenRequest.getEmail();
        boolean existsByEmail = citizenRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new CitizenAlreadyExistsByEmailException(String.format(EMAIL_ALREADY_EXISTS, email));
        }
        Citizen citizen = citizenMapper.toCitizen(citizenRequest);
        citizen = citizenRepository.save(citizen);
        return citizenMapper.toApiResponse(citizen);
    }

    @Override
    public ApiCitizenResponse updateCitizen(UUID citizenId, ApiCitizenRequest citizenRequest) {
        Citizen citizen = citizenRepository
                .findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(
                        String.format(NOT_FOUND, citizenId)));
        Citizen updatedCitizen = citizenMapper.toCitizen(citizenRequest);
        updatedCitizen.setId(citizenId);
        updatedCitizen.setCreatedAt(citizen.getCreatedAt());
        updatedCitizen = citizenRepository.save(updatedCitizen);
        return citizenMapper.toApiResponse(updatedCitizen);
    }

    @Override
    public void deleteCitizen(UUID citizenId) {
        Citizen citizen = citizenRepository
                .findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(
                        String.format(NOT_FOUND, citizenId)));
        citizenRepository.delete(citizen);
    }
}

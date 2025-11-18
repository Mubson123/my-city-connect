package com.mc.citizen.service;

import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;

import java.util.List;
import java.util.UUID;

public interface CitizenService {
    List<ApiCitizenResponse> getCitizens();
    ApiCitizenResponse getCitizenById(UUID citizenId);
    ApiCitizenResponse createCitizen(ApiCitizenRequest apiCitizenRequest);
    ApiCitizenResponse updateCitizen(UUID citizenId, ApiCitizenRequest apiCitizenRequestDto);
    void deleteCitizen(UUID citizenId);
}

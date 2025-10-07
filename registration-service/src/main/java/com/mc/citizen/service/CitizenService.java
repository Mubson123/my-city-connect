package com.mc.citizen.service;

import com.mc.citizen.model.dto.CitizenRequestDto;
import com.mc.citizen.model.dto.CitizenResponseDto;

import java.util.List;
import java.util.UUID;

public interface CitizenService {
    List<CitizenResponseDto> getCitizens(int from, int until);
    CitizenResponseDto getCitizenById(UUID citizenId);
    CitizenResponseDto createCitizen(CitizenRequestDto citizenRequestDto);
}

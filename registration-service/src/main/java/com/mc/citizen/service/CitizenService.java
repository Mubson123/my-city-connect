package com.mc.citizen.service;

import com.mc.citizen.dto.CitizenResponseDto;

import java.util.List;
import java.util.UUID;

public interface CitizenService {
    List<CitizenResponseDto> getCitizens(int from, int until);
    CitizenResponseDto getCitizenById(UUID citizenId);
}

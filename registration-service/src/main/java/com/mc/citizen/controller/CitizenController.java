package com.mc.citizen.controller;

import com.mc.citizen.RegistrationControllerApi;
import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import com.mc.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/registration-service")
public class CitizenController implements RegistrationControllerApi {

    private final CitizenService citizenService;

    @Override
    public ResponseEntity<List<ApiCitizenResponse>> getAllCitizens(Integer limit) {
        return ResponseEntity.ok(citizenService.getCitizens(limit));
    }

    @Override
    public ResponseEntity<ApiCitizenResponse> getCitizenById(UUID citizenId) {
        return ResponseEntity.ok(citizenService.getCitizenById(citizenId));
    }

    @Override
    public ResponseEntity<ApiCitizenResponse> createCitizen(ApiCitizenRequest apiCitizenRequest) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("http:localhost:9091/api/v2/registration-service")
                .build().toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(citizenService.createCitizen(apiCitizenRequest));
    }

    @Override
    public ResponseEntity<ApiCitizenResponse> updateCitizen(UUID citizenId, ApiCitizenRequest apiCitizenRequest) {
        return ResponseEntity.ok(citizenService.updateCitizen(citizenId, apiCitizenRequest));
    }

    @Override
    public ResponseEntity<Void> deleteCitizen(UUID citizenId) {
        citizenService.deleteCitizen(citizenId);
        return ResponseEntity.noContent().build();
    }
}

package com.mc.extend.controller;

import com.mc.extend.VisaControllerApi;
import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import com.mc.extend.service.VisaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/residence-permit")
public class VisaController implements VisaControllerApi {

    private final VisaService visaService;

    @Override
    public ResponseEntity<List<ApiVisaResponse>> getAllVisas() {
        return ResponseEntity.ok(visaService.getVisas());
    }

    @Override
    public ResponseEntity<ApiVisaResponse> getVisaById(UUID visaId) {
        return ResponseEntity.ok(visaService.getVisaById(visaId));
    }

    @Override
    public ResponseEntity<ApiVisaResponse> createVisa(ApiVisaRequest apiVisaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visaService.createVisa(apiVisaRequest));
    }

    @Override
    public ResponseEntity<ApiVisaResponse> updateVisa(UUID visaId, ApiVisaRequest apiVisaRequest) {
        return ResponseEntity.ok(visaService.updateVisa(visaId, apiVisaRequest));
    }

    @Override
    public ResponseEntity<Void> deleteVisa(UUID visaId) {
        visaService.deleteVisa(visaId);
        return ResponseEntity.noContent().build();
    }
}

package com.mc.visa.service;

import com.mc.visa.model.ApiVisaRequest;
import com.mc.visa.model.ApiVisaResponse;

import java.util.List;
import java.util.UUID;

public interface VisaService {
    List<ApiVisaResponse> getVisas();
    ApiVisaResponse getVisaById(UUID visaId);
    ApiVisaResponse createVisa(ApiVisaRequest apiVisaRequest);
    ApiVisaResponse updateVisa(UUID visaId, ApiVisaRequest apiVisaRequest);
    void deleteVisa(UUID visaId);
}

package com.mc.extend.service;

import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;

import java.util.List;
import java.util.UUID;

public interface VisaService {
    List<ApiVisaResponse> getVisas();
    ApiVisaResponse getVisaById(UUID visaId);
    ApiVisaResponse createVisa(ApiVisaRequest apiVisaRequest);
    ApiVisaResponse updateVisa(UUID visaId, ApiVisaRequest apiVisaRequest);
    void deleteVisa(UUID visaId);
}

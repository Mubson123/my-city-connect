package com.mc.extend.service;

import com.mc.extend.exception.VisaNotFoundException;
import com.mc.extend.kafka.KafkaProducerServiceVisa;
import com.mc.extend.mapper.VisaMapper;
import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import com.mc.extend.model.Visa;
import com.mc.extend.repository.VisaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisaServiceImpl implements VisaService {
    private static final String NOT_FOUND = "Visa with ID '%s' not found";

    private final KafkaProducerServiceVisa kafkaProducerService;
    private final VisaRepository visaRepository;
    private final VisaMapper visaMapper;

    @Override
    public List<ApiVisaResponse> getVisas() {
        List<Visa> visas = visaRepository.findAll();
        return visaMapper.toApiResponses(visas);
    }

    @Override
    public ApiVisaResponse getVisaById(UUID visaId) {
        Visa visa = visaRepository
                .findById(visaId)
                .orElseThrow(() -> new VisaNotFoundException(
                        String.format(NOT_FOUND, visaId)));
        return visaMapper.toApiResponse(visa);
    }

    @Transactional
    @Override
    public ApiVisaResponse createVisa(ApiVisaRequest apiVisaRequest) {
        Visa visa = visaMapper.toVisa(apiVisaRequest);
        visa = visaRepository.save(visa);
        kafkaProducerService.sendEvent(visa, "VISA_CREATED");
        return visaMapper.toApiResponse(visa);
    }

    @Transactional
    @Override
    public ApiVisaResponse updateVisa(UUID visaId, ApiVisaRequest apiVisaRequest) {
        Visa visa = visaRepository
                .findById(visaId)
                .orElseThrow(() -> new VisaNotFoundException(
                        String.format(NOT_FOUND, visaId)));
        Visa updatedVisa = visaMapper.toVisa(apiVisaRequest);
        updatedVisa.setId(visaId);
        updatedVisa.setCreatedAt(visa.getCreatedAt());
        updatedVisa.setUpdatedAt(visa.getUpdatedAt());
        updatedVisa = visaRepository.save(updatedVisa);
        kafkaProducerService.sendEvent(updatedVisa, "VISA_UPDATED");
        return visaMapper.toApiResponse(updatedVisa);
    }

    @Transactional
    @Override
    public void deleteVisa(UUID visaId) {
        Visa visa = visaRepository
                .findById(visaId)
                .orElseThrow(() -> new VisaNotFoundException(
                        String.format(NOT_FOUND, visaId)));
        kafkaProducerService.sendEvent(visa, "VISA_DELETED");
        visaRepository.delete(visa);
    }
}

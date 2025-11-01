package com.mc.extend.kafka;

import com.mc.extend.model.Visa;
import extend.event.VisaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final String topic;

    public KafkaProducerService(KafkaTemplate<String, byte[]> kafkaTemplate,
                                @Value("${kafka.topic.extend-residence-permit-events}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendVisaEvents(Visa visa, String eventType) {
        VisaEvent visaEvent =
                VisaEvent.newBuilder()
                        .setVisaId(visa.getId().toString())
                        .setVisaNumber(visa.getVisaNumber())
                        .setIssuedAt(visa.getIssuedAt().toString())
                        .setExpiresAt(visa.getExpiresAt().toString())
                        .setVisaType(visa.getVisaType().name())
                        .setEventType(eventType)
                        .build();

        try {
            String key = String.valueOf(visa.getId());
            kafkaTemplate.send(topic, key, visaEvent.toByteArray());
            log.info("Visa Event sent: {}", visaEvent);
        } catch (Exception e) {
            log.error("Error sending Visa Event: {}", visaEvent);
        }
    }
}

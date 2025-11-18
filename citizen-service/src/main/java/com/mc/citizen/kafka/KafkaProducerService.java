package com.mc.citizen.kafka;

import com.mc.citizen.model.Citizen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import registration.event.CitizenEvent;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final String topic;

    public KafkaProducerService(KafkaTemplate<String, byte[]> kafkaTemplate,
                                @Value("${kafka.topic.citizen-events}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendEvent(Citizen citizen, String eventType) {
        CitizenEvent citizenEvent =
                CitizenEvent.newBuilder()
                        .setRegistrationId(citizen.getId().toString())
                        .setFirstname(citizen.getFirstName())
                        .setLastname(citizen.getLastName())
                        .setEmail(citizen.getEmail())
                        .setEventType(eventType)
                        .build();
        try {
            String key = String.valueOf(citizen.getId());
            kafkaTemplate.send(topic, key, citizenEvent.toByteArray());
            log.info("Citizen Event sent: {}", citizenEvent);
        } catch (Exception e) {
            log.error("Error sending Citizen Event: {}", citizenEvent);
        }
    }
}

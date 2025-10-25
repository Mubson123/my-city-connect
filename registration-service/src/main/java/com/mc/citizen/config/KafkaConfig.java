package com.mc.citizen.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value( "${kafka.topic.citizen-events}")
    private String citizenTopic;

    @Bean
    public NewTopic citizenEventsTopic() {
        return TopicBuilder.name(citizenTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

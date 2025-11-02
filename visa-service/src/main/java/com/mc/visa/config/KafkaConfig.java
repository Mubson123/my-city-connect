package com.mc.visa.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value( "${kafka.topic.visa-events}")
    private String visaTopic;


    @Bean
    public NewTopic visaEventsTopic() {
        return TopicBuilder.name(visaTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

package com.mc.extend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value( "${kafka.topic.extend-residence-permit-events}")
    private String extendResidencePermitTopic;


    @Bean
    public NewTopic visaEventsTopic() {
        return TopicBuilder.name(extendResidencePermitTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

package com.mc.extend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value( "${kafka.topic.visa-events}")
    private String visaTopic;

    @Value( "${kafka.topic.visa-events}")
    private String passportTopic;

    @Value( "${kafka.topic.residence-permit-events}")
    private String residencePermitTopic;

    @Value( "${kafka.topic.residence-permit-events}")
    private String identificationCartTopic;


    @Bean
    public NewTopic visaEventsTopic() {
        return TopicBuilder.name(visaTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic passportEventsTopic() {
        return TopicBuilder.name(passportTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic residencePermitEventsTopic() {
        return TopicBuilder.name(residencePermitTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic identificationCartEventsTopic() {
        return TopicBuilder.name(identificationCartTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

package org.incidence.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * The Consumer class is a configuration class used for creating Kafka consumer-related beans.
 * It enables the creation of a Kafka consumer factory bean and a Kafka listener container factory bean.
 */
@Configuration
public class Consumer {
    final KafkaProperties kafkaProperties;

    public Consumer(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    ConsumerFactory<String, Object> kafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(new DefaultSslBundleRegistry()));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(kafkaConsumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }
}

package com.example.membermanagement.adaptor.impl;

import com.example.membermanagement.adaptor.MemberKafkaProducer;
import com.example.membermanagement.config.KafkaProperties;
import com.example.membermanagement.domain.Member;
import com.example.membermanagement.domain.dto.MemberChanged;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberKafkaProducerImpl implements MemberKafkaProducer {
    private final String TOPIC_MEMBER = "topic_member";

    private final KafkaProperties kafkaProperties;
    private KafkaProducer<String, String> producer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MemberKafkaProducerImpl(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @PostConstruct
    public void initialize() {
        log.info("Kafka producer initializing...");
        this.producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        log.info("Kafka producer initialized...");
    }

    @Override
    public void createUser(Member member) throws JsonProcessingException {
        MemberChanged memberChanged = new MemberChanged(member);
        String message = objectMapper.writeValueAsString(memberChanged);
        producer.send(new ProducerRecord<>(TOPIC_MEMBER, message));
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutdown kafka producer");
        producer.close();
    }
}

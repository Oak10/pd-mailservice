package com.ex.mailservice.services;

import com.ex.mailservice.entities.EmailDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private
    EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "kafka_topic", groupId = "group_id", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailDetails emailDetails = objectMapper.readValue(message, EmailDetails.class);

        emailService.sendSimpleMail(emailDetails);

        // Print statement
        LOGGER.info("kafka message:  <Body:{} , Recipient:{}> ", emailDetails.getMsgBody(), emailDetails.getRecipient());
    }
}
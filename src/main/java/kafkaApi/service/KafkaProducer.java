package kafkaApi.service;

import kafkaApi.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "topic_kafka";

    @Autowired
    private KafkaTemplate<String, MessageDTO> kafkaTemplate;

    public void sendMessage(MessageDTO messageDTO) {
        kafkaTemplate.send(TOPIC, messageDTO);
    }
}

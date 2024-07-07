package kafkaApi.service;

import kafkaApi.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = "topic_kafka", groupId = "group_id")
    public void consume(MessageDTO messageDTO) {
        logger.info("Consumed message: {}", messageDTO);

        switch (messageDTO.getFeature()) {
            case "SAVE":
                messageService.saveMessage(messageDTO);
                logger.info("Saved message with ID: {}", messageDTO.getId());
                break;
            case "DELETE":
                messageService.deleteMessage(messageDTO.getId());
                logger.info("Deleted message with ID: {}", messageDTO.getId());
                break;
            default:
                logger.warn("Unknown feature: {}", messageDTO.getFeature());
        }
    }
}

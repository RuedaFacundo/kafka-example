package kafkaApi.service;

import kafkaApi.dto.MessageDTO;
import kafkaApi.model.Message;
import kafkaApi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(MessageDTO messageDTO) {
        Message message = new Message(messageDTO.getId(), messageDTO.getFeature(), messageDTO.getMessage());
        messageRepository.save(message);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }
}

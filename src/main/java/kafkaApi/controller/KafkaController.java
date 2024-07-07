package kafkaApi.controller;

import kafkaApi.dto.MessageDTO;
import kafkaApi.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public String publishMessage(@Valid @RequestBody MessageDTO messageDTO) {
        kafkaProducer.sendMessage(messageDTO);
        return "Message published successfully!";
    }
}

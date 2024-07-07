package kafkaApi.dto;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class MessageDTO {

    private String id;
    @NotEmpty(message = "Feature is required")
    private String feature;
    @NotEmpty(message = "Message is required")
    private String message;

    public MessageDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


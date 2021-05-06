package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "discussions")
public class OfferDiscussion {

    @Id
    private String id;
    private List<Message> messages;

    public OfferDiscussion(){
        messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        if (messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
    }
}

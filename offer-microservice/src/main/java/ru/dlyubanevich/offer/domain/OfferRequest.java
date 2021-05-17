package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.dlyubanevich.offer.model.offer.OfferRequestModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "requests")
public class OfferRequest {

    @Id
    private String id;
    private User user;
    private List<Nomenclature> items;
    private Location location;
    private String description;
    private String type;
    private OfferOption option;
    private List<Requirement> requirements;
    private String status;
    private String discussionId;

    public OfferRequest(OfferRequestModel model){
        this.description = model.getDescription();
        this.location = model.getLocation();
        this.option = model.getOption();
        this.user = model.getUser();
        this.type = model.getType();
        this.requirements = model.getRequirements();
        this.status = Status.NEW;
    }
}

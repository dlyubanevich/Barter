package ru.dlyubanevich.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.dlyubanevich.offer.model.offer.OfferResponseModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "responses")
public class OfferResponse {

    @Id
    private String id;
    private User user;
    private String offerRequestId;
    private List<Nomenclature> items;
    private Location location;
    private OfferOption option;
    private String description;
    private String discussionId;

    public OfferResponse(OfferResponseModel model){
        this.description = model.getDescription();
        this.location = model.getLocation();
        this.option = model.getOption();
        this.user = model.getUser();
        this.offerRequestId = model.getOfferRequestId();
    }
}

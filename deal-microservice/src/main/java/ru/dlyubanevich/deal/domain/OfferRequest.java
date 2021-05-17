package ru.dlyubanevich.deal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OfferRequest {

    private final String id;
    private final String description;
    private final List<Nomenclature> items;

}

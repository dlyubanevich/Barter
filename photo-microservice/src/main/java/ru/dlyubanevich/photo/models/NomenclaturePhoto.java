package ru.dlyubanevich.photo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class NomenclaturePhoto {

    private final String id;
    private final List<String> photos;

}

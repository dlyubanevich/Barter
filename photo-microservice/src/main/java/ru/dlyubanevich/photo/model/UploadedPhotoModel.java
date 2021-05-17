package ru.dlyubanevich.photo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UploadedPhotoModel {

    private final String ownerId;
    private final List<String> items;

}

package ru.dlyubanevich.nomenclature.service;

import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.model.NomenclatureModel;

public interface MessageService {

    void sendUserNomenclatureMessage(Nomenclature nomenclature, NomenclatureModel nomenclatureModel);
    void sendPhotoMessage(String itemId, NomenclatureModel nomenclatureModel);

}

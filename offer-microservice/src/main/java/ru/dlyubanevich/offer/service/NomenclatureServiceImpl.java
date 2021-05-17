package ru.dlyubanevich.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offer.domain.Nomenclature;
import ru.dlyubanevich.offer.feign.NomenclatureServiceFeignClient;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureServiceFeignClient nomenclatureServiceFeignClient;

    //TODO Добавить в сервис номенклатуры создание сразу списка номенклатур

    @Override
    public List<Nomenclature> addItems(List<NomenclatureModel> items) {
        return items.stream()
                .map(nomenclatureServiceFeignClient::addNomenclature)
                .collect(Collectors.toList());
    }
}

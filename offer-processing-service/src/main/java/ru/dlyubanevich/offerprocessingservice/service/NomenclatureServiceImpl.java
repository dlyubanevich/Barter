package ru.dlyubanevich.offerprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.feign.NomenclatureServiceFeignClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureServiceFeignClient nomenclatureServiceFeignClient;

    @Override
    public List<Nomenclature> addItems(List<Nomenclature> items) {
        List<Nomenclature> nomenclatures = new ArrayList<>();
        for (Nomenclature nomenclature : items) {
            Nomenclature newItem;
            if (nomenclature.isAddableToUserNomenclature()) {
                newItem = nomenclatureServiceFeignClient.addUserNomenclature(nomenclature);
            } else {
                newItem = nomenclatureServiceFeignClient.addNomenclature(nomenclature);
            }
            nomenclatures.add(newItem);
        }
        return nomenclatures;
    }
}

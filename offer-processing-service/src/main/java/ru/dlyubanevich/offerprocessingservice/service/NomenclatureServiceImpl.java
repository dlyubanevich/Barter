package ru.dlyubanevich.offerprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;
import ru.dlyubanevich.offerprocessingservice.feign.NomenclatureServiceFeignClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureServiceFeignClient nomenclatureServiceFeignClient;

    @Override
    public List<Nomenclature> addItems(List<Nomenclature> items) {
        return items.stream()
                .map(nomenclatureServiceFeignClient::addNomenclature)
                .collect(Collectors.toList());
    }
}

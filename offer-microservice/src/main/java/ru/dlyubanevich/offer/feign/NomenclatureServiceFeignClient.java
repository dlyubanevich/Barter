package ru.dlyubanevich.offer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dlyubanevich.offer.domain.Nomenclature;
import ru.dlyubanevich.offer.model.nomenclature.NomenclatureModel;

@FeignClient(name = "nomenclatures")
public interface NomenclatureServiceFeignClient {

    @PostMapping("/api/v1/nomenclature")
    Nomenclature addNomenclature(@RequestBody NomenclatureModel nomenclature);

}

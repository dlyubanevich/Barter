package ru.dlyubanevich.offerprocessingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dlyubanevich.offerprocessingservice.domain.nomenclature.Nomenclature;

@FeignClient(name = "nomenclatures")
public interface NomenclatureServiceFeignClient {

    @PostMapping("/api/v1/nomenclature")
    Nomenclature addNomenclature(@RequestBody Nomenclature nomenclature);

    @PostMapping("/api/v1/nomenclature/user")
    Nomenclature addUserNomenclature(@RequestBody Nomenclature nomenclature);

}

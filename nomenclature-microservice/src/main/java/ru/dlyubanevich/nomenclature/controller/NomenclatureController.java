package ru.dlyubanevich.nomenclature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.domain.OptionProperty;
import ru.dlyubanevich.nomenclature.service.TotalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NomenclatureController {

    private final TotalService totalService;

    @PostMapping("/api/v1/nomenclature")
    public Nomenclature addNomenclature(@RequestBody Nomenclature nomenclature){
        return totalService.addNomenclature(nomenclature, false);
    }

    @PostMapping("/api/v1/nomenclature/user")
    public Nomenclature addUserNomenclature(@RequestBody Nomenclature nomenclature){
        return totalService.addNomenclature(nomenclature, true);
    }

    @GetMapping("/api/v1/nomenclature/user")
    List<Nomenclature> getAllUserNomenclature(String userId){
        return totalService.getAllUserNomenclature(userId);
    }

    @PostMapping("/api/v1/nomenclature/option")
    public NomenclatureOption addNomenclatureOption(@RequestBody NomenclatureOption option){
        return totalService.addNomenclatureOption(option);
    }

    @PostMapping("/api/v1/nomenclature/option/property")
    public OptionProperty addOptionProperty(@RequestBody OptionProperty property){
        return totalService.addOptionProperty(property);
    }

    @GetMapping("/api/v1/nomenclature/option")
    List<NomenclatureOption> getAllNomenclatureOptions(){
        return totalService.getAllNomenclatureOptions();
    }

    @GetMapping("/api/v1/nomenclature/option/property/{optionId}")
    List<OptionProperty> getAllNomenclatureOptions(@PathVariable String optionId){
        return totalService.getOptionProperties(optionId);
    }

    @GetMapping("/api/v1/nomenclature/type")
    List<String> getAllNomenclatureTypes(){
        return totalService.getNomenclatureTypes();
    }

}

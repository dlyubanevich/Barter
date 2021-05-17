package ru.dlyubanevich.nomenclature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.nomenclature.domain.Nomenclature;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.domain.OptionProperty;
import ru.dlyubanevich.nomenclature.model.NomenclatureModel;
import ru.dlyubanevich.nomenclature.service.ProcessingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NomenclatureController {

    private final ProcessingService processingService;

    @PostMapping("/api/v1/nomenclature")
    public Nomenclature addNomenclature(@RequestBody NomenclatureModel nomenclatureModel){
        return processingService.addNomenclature(nomenclatureModel);
    }

    @GetMapping("/api/v1/nomenclature/{id}")
    public Nomenclature getNomenclatureById(@PathVariable String id){
        return processingService.getNomenclatureById(id);
    }

    @PostMapping("/api/v1/nomenclature/option")
    public NomenclatureOption addNomenclatureOption(@RequestBody NomenclatureOption option){
        return processingService.addNomenclatureOption(option);
    }

    @GetMapping("/api/v1/nomenclature/option")
    List<NomenclatureOption> getAllNomenclatureOptions(){
        return processingService.getAllNomenclatureOptions();
    }

    @PostMapping("/api/v1/nomenclature/option/property")
    public OptionProperty addOptionProperty(@RequestBody OptionProperty property){
        return processingService.addOptionProperty(property);
    }

    @GetMapping("/api/v1/nomenclature/option/property/{optionId}")
    List<OptionProperty> getAllNomenclatureOptionProperties(@PathVariable String optionId){
        return processingService.getOptionProperties(optionId);
    }

    @GetMapping("/api/v1/nomenclature/type")
    List<String> getAllNomenclatureTypes(){
        return processingService.getNomenclatureTypes();
    }

}

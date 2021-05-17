package ru.dlyubanevich.nomenclature.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclature.domain.*;
import ru.dlyubanevich.nomenclature.model.NomenclatureModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final NomenclatureOptionService nomenclatureOptionService;
    private final NomenclatureService nomenclatureService;
    private final OptionPropertyService optionPropertyService;
    private final MessageService messageService;

    private final static List<String> TYPES = Type.getAll();

    @Transactional
    @Override
    public Nomenclature addNomenclature(NomenclatureModel nomenclatureModel) {
        Nomenclature savedItem = nomenclatureService.save(new Nomenclature(nomenclatureModel));
        messageService.sendUserNomenclatureMessage(savedItem, nomenclatureModel);
        messageService.sendPhotoMessage(savedItem.getId(), nomenclatureModel);
        return savedItem;
    }

    @Transactional(readOnly = true)
    @Override
    public Nomenclature getNomenclatureById(String id) {
        return nomenclatureService.getById(id);
    }

    @Transactional
    @Override
    public NomenclatureOption addNomenclatureOption(NomenclatureOption option) {
        return nomenclatureOptionService.save(option);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NomenclatureOption> getAllNomenclatureOptions() {
        return nomenclatureOptionService.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OptionProperty> getOptionProperties(String optionId) {
        return optionPropertyService.getAllByOptionId(optionId);
    }

    @Transactional()
    @Override
    public OptionProperty addOptionProperty(OptionProperty optionProperty) {
        return optionPropertyService.save(optionProperty);
    }

    @Override
    public List<String> getNomenclatureTypes() {
        return TYPES;
    }

}

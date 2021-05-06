package ru.dlyubanevich.nomenclature.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclature.domain.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalServiceImpl implements TotalService {

    private final NomenclatureOptionService nomenclatureOptionService;
    private final NomenclatureService nomenclatureService;
    private final OptionPropertyService optionPropertyService;
    private final UserNomenclatureService userNomenclatureService;

    private static final List<String> TYPES = Type.getAll();

    @Transactional
    @Override
    public Nomenclature addNomenclature(Nomenclature nomenclature, boolean isAddableToUserNomenclature) {
        Nomenclature item = nomenclatureService.save(nomenclature);
        if (isAddableToUserNomenclature){
            UserNomenclature userNomenclature = new UserNomenclature(null, item.getOwner().getId(), item.getId());
            userNomenclatureService.save(userNomenclature);
        }
        return item;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Nomenclature> getAllUserNomenclature(String userId) {
        List<String> listId = userNomenclatureService.getAllNomenclatureIdByUserId(userId);
        return nomenclatureService.getAll(listId);
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

    @Override
    public List<String> getNomenclatureTypes() {
        return TYPES;
    }

    @Override
    public OptionProperty addOptionProperty(OptionProperty optionProperty) {
        return optionPropertyService.save(optionProperty);
    }
}

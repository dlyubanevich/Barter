package ru.dlyubanevich.nomenclature.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclature.domain.NomenclatureOption;
import ru.dlyubanevich.nomenclature.repository.NomenclatureOptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NomenclatureOptionServiceImpl implements NomenclatureOptionService {

    private final NomenclatureOptionRepository repository;

    @Transactional
    @Override
    public NomenclatureOption save(NomenclatureOption option) {
        return repository.save(option);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NomenclatureOption> getAll() {
        return repository.findAll();
    }
}

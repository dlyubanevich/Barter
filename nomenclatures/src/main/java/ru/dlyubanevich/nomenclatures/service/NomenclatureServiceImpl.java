package ru.dlyubanevich.nomenclatures.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclatures.domain.Nomenclature;
import ru.dlyubanevich.nomenclatures.domain.User;
import ru.dlyubanevich.nomenclatures.repository.NomenclatureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureRepository repository;

    @Transactional
    @Override
    public Nomenclature save(Nomenclature nomenclature) {
        return repository.save(nomenclature);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Nomenclature> getAllByOwner(User user) {
        return repository.findAllByOwnerId(user.getId());
    }

    @Override
    public List<Nomenclature> getAll(List<String> ids) {
        return repository.findAllByIdIn(ids);
    }
}

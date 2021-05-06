package ru.dlyubanevich.nomenclature.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclature.domain.UserNomenclature;
import ru.dlyubanevich.nomenclature.repository.UserNomenclatureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserNomenclatureServiceImpl implements UserNomenclatureService {

    private final UserNomenclatureRepository repository;

    @Transactional
    @Override
    public UserNomenclature save(UserNomenclature userNomenclature) {
        return repository.save(userNomenclature);
    }

    @Transactional
    @Override
    public void saveAll(List<UserNomenclature> list) {
        repository.saveAll(list);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllNomenclatureIdByUserId(String userId) {
        return repository.findAllByUserId(userId).stream()
                .map(UserNomenclature::getNomenclatureId)
                .collect(Collectors.toList());
    }
}

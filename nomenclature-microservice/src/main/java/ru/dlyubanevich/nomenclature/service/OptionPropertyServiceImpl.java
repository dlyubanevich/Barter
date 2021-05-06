package ru.dlyubanevich.nomenclature.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.nomenclature.domain.OptionProperty;
import ru.dlyubanevich.nomenclature.repository.OptionPropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionPropertyServiceImpl implements OptionPropertyService {

    private final OptionPropertyRepository repository;

    @Transactional
    @Override
    public OptionProperty save(OptionProperty optionProperty) {
        return repository.save(optionProperty);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OptionProperty> getAllByOptionId(String optionId) {
        return repository.findAllByOptionId(optionId);
    }
}

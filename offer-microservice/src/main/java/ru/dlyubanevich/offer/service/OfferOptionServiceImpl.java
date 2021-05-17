package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.OfferOption;
import ru.dlyubanevich.offer.repository.OfferOptionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferOptionServiceImpl implements OfferOptionService {

    private final OfferOptionRepository repository;

    @Transactional
    @Override
    public OfferOption save(OfferOption offerOption) {
        return repository.save(offerOption);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferOption> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

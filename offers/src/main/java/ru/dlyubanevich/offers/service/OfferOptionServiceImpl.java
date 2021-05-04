package ru.dlyubanevich.offers.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offers.domain.OfferOption;
import ru.dlyubanevich.offers.repository.OfferOptionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferOptionServiceImpl implements OfferOptionService {

    private final OfferOptionRepository offerOptionRepository;

    @Transactional
    @Override
    public OfferOption save(OfferOption offerOption) {
        return offerOptionRepository.save(offerOption);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferOption> getAll() {
        return offerOptionRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        offerOptionRepository.deleteById(id);
    }
}

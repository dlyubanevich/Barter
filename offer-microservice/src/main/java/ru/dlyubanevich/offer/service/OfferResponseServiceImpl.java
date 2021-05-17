package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.OfferResponse;
import ru.dlyubanevich.offer.repository.OfferResponseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferResponseServiceImpl implements OfferResponseService {

    private final OfferResponseRepository repository;

    @Transactional
    @Override
    public OfferResponse save(OfferResponse offerResponse) {
        return repository.save(offerResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferResponse> getAllByOfferRequestId(String offerRequestId) {
        return repository.findAllByOfferRequestId(offerRequestId);
    }

    @Override
    public OfferResponse findById(String id) {
        return repository.findById(id).orElseThrow();
    }
}

package ru.dlyubanevich.offers.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offers.domain.OfferRequest;
import ru.dlyubanevich.offers.domain.OfferResponse;
import ru.dlyubanevich.offers.repository.OfferResponseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferResponseServiceImpl implements OfferResponseService {

    private final OfferResponseRepository offerResponseRepository;

    @Transactional
    @Override
    public OfferResponse save(OfferResponse offerResponse) {
        return offerResponseRepository.save(offerResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferResponse> getAllByOfferRequestId(String offerRequestId) {
        return offerResponseRepository.findAllByOfferRequestId(offerRequestId);
    }
}

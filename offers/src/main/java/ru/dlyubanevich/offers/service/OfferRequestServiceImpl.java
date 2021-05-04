package ru.dlyubanevich.offers.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offers.domain.OfferRequest;
import ru.dlyubanevich.offers.domain.Status;
import ru.dlyubanevich.offers.domain.User;
import ru.dlyubanevich.offers.repository.OfferRequestRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferRequestServiceImpl implements OfferRequestService{

    private final OfferRequestRepository offerRequestRepository;

    @Transactional
    @Override
    public OfferRequest save(OfferRequest offerRequest) {
        return offerRequestRepository.save(offerRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferRequest> getAllByUser(User user) {
        return offerRequestRepository.findAllByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferRequest> findAllActiveOffers() {
        return offerRequestRepository.findAllByStatusIn(Status.getAllActiveStatuses());
        //return offerRequestRepository.findAll();
    }
}

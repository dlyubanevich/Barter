package ru.dlyubanevich.offer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.offer.domain.OfferRequest;
import ru.dlyubanevich.offer.domain.Status;
import ru.dlyubanevich.offer.domain.User;
import ru.dlyubanevich.offer.repository.OfferRequestRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferRequestServiceImpl implements OfferRequestService{

    private final OfferRequestRepository repository;

    @Transactional
    @Override
    public OfferRequest save(OfferRequest offerRequest) {
        return repository.save(offerRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferRequest> getAllByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferRequest> findAllActiveOffers() {
        return repository.findAllByStatusIn(Status.getAllActiveStatuses());
    }

    @Transactional(readOnly = true)
    @Override
    public OfferRequest findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public User getOwnerRequest(String id) {
        return repository.findById(id).orElseThrow().getUser();
    }
}

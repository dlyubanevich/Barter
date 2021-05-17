package ru.dlyubanevich.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlyubanevich.deal.domain.Deal;
import ru.dlyubanevich.deal.domain.User;
import ru.dlyubanevich.deal.repository.DealRepository;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository repository;

    @Transactional
    @Override
    public Deal addDeal(Deal deal) {
        return repository.save(deal);
    }

    @Transactional(readOnly = true)
    @Override
    public int getNextDealNumber(User initiator) {
        int countOfDeals = repository.countDealsByInitiatorId(initiator.getId());
        return countOfDeals + 1;
    }

    @Transactional(readOnly = true)
    @Override
    public Deal getDeal(String id) {
        return repository.findById(id).orElseThrow();
    }
}

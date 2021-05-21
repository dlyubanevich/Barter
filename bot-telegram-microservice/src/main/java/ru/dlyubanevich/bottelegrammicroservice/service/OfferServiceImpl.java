package ru.dlyubanevich.bottelegrammicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    @Override
    public List<String> getOfferTypes() {
        return new ArrayList<>();
    }
}

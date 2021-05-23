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
        List<String> types = new ArrayList<>();
        types.add("Спрос");
        types.add("Предложение");
        return types;
    }
}

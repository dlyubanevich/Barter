package ru.dlyubanevich.offers.domain;

import java.util.ArrayList;
import java.util.List;

public class OfferType {

    private final static String DEMAND = "Спрос";
    private final static String PROPOSAL = "Предложение";

    public static List<String> getAll(){
        List<String> list = new ArrayList<>();
        list.add(DEMAND);
        list.add(PROPOSAL);
        return list;
    }
}

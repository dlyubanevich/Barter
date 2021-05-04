package ru.dlyubanevich.offers.domain;

import java.util.ArrayList;
import java.util.List;

public class Status {

    private final static String NEW = "Новый";
    private final static String AGREED = "Согласован";
    private final static String CANCELED = "Отменен";
    private final static String CLOSED = "Закрыт";

    public static List<String> getAllActiveStatuses(){
        ArrayList<String> list = new ArrayList<>();
        list.add(AGREED);
        list.add(NEW);
        return list;
    }
}

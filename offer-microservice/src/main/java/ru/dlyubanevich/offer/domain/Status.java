package ru.dlyubanevich.offer.domain;

import java.util.ArrayList;
import java.util.List;

public class Status {

    public final static String NEW = "Новый";
    public final static String AGREED = "Согласован";
    public final static String CANCELED = "Отменен";
    public final static String CLOSED = "Закрыт";

    public static List<String> getAllActiveStatuses(){
        ArrayList<String> list = new ArrayList<>();
        list.add(AGREED);
        list.add(NEW);
        return list;
    }
}

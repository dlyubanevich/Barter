package ru.dlyubanevich.nomenclature.domain;

import java.util.ArrayList;
import java.util.List;

public class Type {

    public final static String SERVICE = "Услуга";
    public final static String PRODUCT = "Товар";

    public static List<String> getAll(){
        List<String> list = new ArrayList<>();
        list.add(SERVICE);
        list.add(PRODUCT);
        return list;
    }

}

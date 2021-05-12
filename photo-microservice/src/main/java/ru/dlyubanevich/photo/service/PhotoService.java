package ru.dlyubanevich.photo.service;

public interface PhotoService {

    byte[] convert(String photo);
    String load(byte[] bytes);

}

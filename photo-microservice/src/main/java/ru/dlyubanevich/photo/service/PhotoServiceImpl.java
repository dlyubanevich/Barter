package ru.dlyubanevich.photo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final RestTemplate restTemplate = new RestTemplate();

    private String token = "AQAAAAAF2d5UAAccbcencDF2MUq7sQBNjg07W4U";
    private String path = "https://cloud-api.yandex.net/v1/disk/resources/upload";

    @Override
    public byte[] convert(String photo) {
        return Base64.getEncoder().encode(photo.getBytes());
    }

    @Override
    public String load(byte[] bytes) {

        //String uploadPath = path + "?path=barter&overwrite=true";
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(uploadPath)

        //TODO загрузить фотографию на яндекс.диск
        return "https://disk.yandex.ru/i/EF3-MuC91x79BB";
    }


}

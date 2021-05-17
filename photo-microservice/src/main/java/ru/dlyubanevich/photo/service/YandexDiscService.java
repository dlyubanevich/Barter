package ru.dlyubanevich.photo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dlyubanevich.photo.model.FileModel;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class YandexDiscService implements PhotoService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${authorization}")
    private String authorization;
    @Value("${address}")
    private String address;

    private HttpEntity<String> authorizationEntity;

    @Override
    public String uploadFile(FileModel file) {

        initAuthorizationEntity();
        upload(file);
        publish(file);

        return getUrl(file);

    }

    private void initAuthorizationEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        authorizationEntity = new HttpEntity<>(null, headers);
    }

    private void upload(FileModel file) {

        String uploadPath = getUploadPath(file.getName());
        byte[] dataFile = decode(file.getCodedBytes());

        HttpEntity<byte[]> body = new HttpEntity<>(dataFile);

        restTemplate.exchange(
                uploadPath,
                HttpMethod.PUT,
                body,
                String.class
        );

    }

    @SneakyThrows
    private String getUploadPath(String filename) {

        String url = address + "/upload?path=" + filename + "&overwrite=true";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                authorizationEntity,
                String.class
        );

        String uploadPath = null;
        if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
            JsonNode value = objectMapper.readValue(response.getBody(), JsonNode.class);
            uploadPath = value.get("href").textValue();
        }

        return uploadPath;

    }

    public byte[] decode(String photo) {
        return Base64.getMimeDecoder().decode(photo.getBytes());
    }

    private void publish(FileModel file) {

        String url = address + "/publish?path=" + file.getName();

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                authorizationEntity,
                String.class
        );

    }

    @SneakyThrows
    private String getUrl(FileModel file) {

        String url = address + "?path=" + file.getName();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                authorizationEntity,
                String.class
        );

        JsonNode value = objectMapper.readValue(response.getBody(), JsonNode.class);
        return value.get("public_url").textValue();

    }

}

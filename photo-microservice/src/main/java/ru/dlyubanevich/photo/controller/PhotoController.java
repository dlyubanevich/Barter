package ru.dlyubanevich.photo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dlyubanevich.photo.service.PhotoService;

@RequiredArgsConstructor
@RestController
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/api/v1/photo")
    public String upload(@RequestBody String photo){
        byte[] bytes = photoService.convert(photo);
        return photoService.load(bytes);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}

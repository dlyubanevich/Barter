package ru.dlyubanevich.bottelegrammicroservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;

@FeignClient(name = "user-microservice")
public interface UserServiceFeignClient {

    @PostMapping("/api/v1/user")
    String addUser(@RequestBody UserModel userModel);

}

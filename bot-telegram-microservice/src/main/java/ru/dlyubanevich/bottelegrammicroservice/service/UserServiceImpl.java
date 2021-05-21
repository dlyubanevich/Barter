package ru.dlyubanevich.bottelegrammicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlyubanevich.bottelegrammicroservice.model.UserModel;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public void save(UserModel userModel) {

    }
}

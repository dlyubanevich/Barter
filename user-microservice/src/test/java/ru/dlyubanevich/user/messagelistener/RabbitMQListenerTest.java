package ru.dlyubanevich.user.messagelistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dlyubanevich.user.UserApplicationTests;
import ru.dlyubanevich.user.domain.Nomenclature;
import ru.dlyubanevich.user.models.UserAvatarModel;
import ru.dlyubanevich.user.models.UserNomenclatureModel;
import ru.dlyubanevich.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс RabbitMQListener должен")
@SpringBootTest(classes = UserApplicationTests.class)
class RabbitMQListenerTest {

    private static final String USER_ID = "1";
    private static final String URL = "http://";
    private static final String MESSAGE = "{}";
    private static final List<Nomenclature> ITEMS = new ArrayList<>();

    @MockBean
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private Logger logger;

    @Autowired
    private RabbitMQListener rabbitMQListener;

    @BeforeEach
    public void setUp(){
        Mockito.reset(userService);
        Mockito.reset(objectMapper);
        Mockito.reset(logger);
    }

    @SneakyThrows
    @Test
    @DisplayName("записывать аватар пользователя, полученный из photo-microservice")
    void setUserAvatarFromPhotoMicroService() {

        List<String> items = new ArrayList<>();
        items.add(URL);

        UserAvatarModel userAvatar = new UserAvatarModel(USER_ID, items);
        given(objectMapper.readValue(MESSAGE, UserAvatarModel.class)).willReturn(userAvatar);

        rabbitMQListener.setUserAvatar(MESSAGE);

        verify(userService, times(1))
                .addUserAvatar(USER_ID, URL);

    }

    @SneakyThrows
    @Test
    @DisplayName("записывать номенклатуру пользователя, полученную из nomenclature-microservice")
    void setUserItemsFromNomenclatureMicroService() {

        UserNomenclatureModel userNomenclature = new UserNomenclatureModel(USER_ID, ITEMS);
        given(objectMapper.readValue(MESSAGE, UserNomenclatureModel.class)).willReturn(userNomenclature);

        rabbitMQListener.setUserItems(MESSAGE);

        verify(userService, times(1))
                .addUserNomenclature(USER_ID, ITEMS);

    }

}
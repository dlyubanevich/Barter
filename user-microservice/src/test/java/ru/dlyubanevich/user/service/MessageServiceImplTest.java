package ru.dlyubanevich.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dlyubanevich.user.UserApplicationTests;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.models.FileModel;
import ru.dlyubanevich.user.models.PhotoModel;
import ru.dlyubanevich.user.models.UserSubscriptionModel;
import ru.dlyubanevich.user.repository.UserRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс MessageServiceImpl должен")
@SpringBootTest(classes = UserApplicationTests.class)
class MessageServiceImplTest {

    private static final String EXCHANGE_NAME = "barter-exchange";
    private static final String ROUTING_KEY_PHOTO_USER = "photo.user";
    private static final String ROUTING_KEY_NOTIFICATION_USER = "notification.user";
    private static final String USER_ID = "1";
    private static final String PHOTO = "User's avatar";

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUp(){
        Mockito.reset(rabbitTemplate);
        Mockito.reset(objectMapper);
    }

    @SneakyThrows
    @Test
    @DisplayName("отправлять сообщение сервису сохранения фотографий")
    void sendMessageForSavingPhoto() {

        FileModel fileModel = new FileModel(PHOTO, PHOTO);
        PhotoModel photoModel = new PhotoModel(USER_ID, fileModel);
        String userPhotoJson = "{}";

        given(objectMapper.writeValueAsString(photoModel)).willReturn(userPhotoJson);

        messageService.sendPhotoMessage(USER_ID, fileModel);

        verify(rabbitTemplate, times(1))
                .convertAndSend(EXCHANGE_NAME, ROUTING_KEY_PHOTO_USER, userPhotoJson);

    }

    @Test
    @DisplayName("не отправлять сообщение сервису фотографий, если фотография отсутствует")
    void doNotSendMessageIfHaveNoPhoto() {

        String userPhotoJson = "{}";
        messageService.sendPhotoMessage(USER_ID, null);

        verify(rabbitTemplate, times(0))
                .convertAndSend(EXCHANGE_NAME, ROUTING_KEY_PHOTO_USER, userPhotoJson);

    }

    @SneakyThrows
    @Test
    @DisplayName("отправлять сообщение сервису уведомлений")
    void sendMessageToNotificationService() {

        User user = repository.findById(USER_ID).orElseThrow();
        UserSubscriptionModel userSubscription = new UserSubscriptionModel(user);
        String userSubscriptionJson = null;

        given(objectMapper.writeValueAsString(userSubscription)).willReturn(userSubscriptionJson);

        messageService.sendNotificationMessage(user);

        verify(rabbitTemplate, times(1))
                .convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NOTIFICATION_USER, userSubscriptionJson);

    }

}
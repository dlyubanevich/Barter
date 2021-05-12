package ru.dlyubanevich.notification.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Класс SubscriptionRepository должен")
public class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository repository;

    private static final User OWNER = new User("1", "Ivan");
    private static final Subscription SUBSCRIPTION = new Subscription(
            "1",
            OWNER,
            Collections.singletonList("2"),
            Collections.singletonList("001"),
            Collections.singletonList("Спрос")
    );

    @BeforeEach
    public void init(){
        List<Subscription> list = new ArrayList<>();
        list.add(new Subscription("1", OWNER, Collections.singletonList("2"), Collections.singletonList("001"), Collections.singletonList("Спрос")));
        list.add(new Subscription("2", new User("2", "Petr"), Collections.singletonList("1"), Collections.singletonList("001"), Collections.singletonList("Предложение")));
        list.add(new Subscription("3", new User("3", "Misha"), Collections.singletonList("1"), Collections.singletonList("002"), Collections.singletonList("Спрос")));
        repository.saveAll(list);
    }

    @Test
    @DisplayName("находить подписки для владельца")
    void shouldFindSubscriptionByOwner(){

        var subscription = repository.findFirstByOwnerId(OWNER.getId());

        assertThat(subscription)
                .isNotNull();
        assertThat(subscription.getId())
                .isEqualTo(SUBSCRIPTION.getId());

    }

    @Test
    @DisplayName("должен находить подписки по типу предложения")
    void shouldFindSubscriptionByOfferType(){
        var list = repository.findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs("0", Collections.singletonList("0"), "Предложение");
        assertThat(list)
                .hasSize(1);
    }

    @Test
    @DisplayName("должен находить подписки по типу номенклатуры")
    void shouldFindSubscriptionByNomenclatureOptions(){
        List<String> optionsId = new ArrayList<>();
        optionsId.add("001");
        optionsId.add("002");
        var list = repository.findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs("0", optionsId, "0");
        assertThat(list)
                .hasSize(3);
    }

    @Test
    @DisplayName("должен находить подписки по пользователю")
    void shouldFindSubscriptionByUser(){
        var list = repository.findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs("1", Collections.singletonList("003"), "0");
        assertThat(list)
                .hasSize(2);
    }

    @Test
    @DisplayName("должен находить подписки по пользователю и типу предложения")
    void shouldFindSubscriptionByUserAndOfferType(){
        var list = repository.findAllByUsersIdIsOrNomenclatureOptionsIdContainsOrOfferTypesIs("2", Collections.singletonList("003"), "Предложение");
        assertThat(list)
                .hasSize(2);
    }

}

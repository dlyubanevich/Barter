package ru.dlyubanevich.user.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dlyubanevich.user.UserApplicationTests;
import ru.dlyubanevich.user.domain.Nomenclature;
import ru.dlyubanevich.user.domain.NomenclatureOption;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс UserServiceImpl должен")
@SpringBootTest(classes = UserApplicationTests.class)
class UserServiceImplTest {

    private static final String USER_ID = "1";

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @DisplayName("добавлять номенклатуру в каталог пользовательских товаров")
    @Test
    void addUserNomenclature() {

        Nomenclature nomenclature = new Nomenclature("1", "Яблоки", new NomenclatureOption("1", "Фрукты"), "Товар");
        List<Nomenclature> list = new ArrayList<>();
        list.add(nomenclature);

        User user = repository.findById(USER_ID).orElseThrow();
        int itemsBefore = user.getItems().size();

        userService.addUserNomenclature(USER_ID, list);

        user = repository.findById(USER_ID).orElseThrow();

        assertThat(user.getItems())
                .hasSizeGreaterThan(itemsBefore)
                .hasSize(1)
                .allMatch(item -> nomenclature.getId().equals(item.getId()));

    }

}
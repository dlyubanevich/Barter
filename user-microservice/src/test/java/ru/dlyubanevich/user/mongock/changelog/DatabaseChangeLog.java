package ru.dlyubanevich.user.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.dlyubanevich.user.domain.Subscription;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.models.UserDataModel;
import ru.dlyubanevich.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dropDb", author = "dlyubanevich", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertUsers", author = "dlyubanevich", runAlways = true)
    public void insertBooks(UserRepository repository){

        List<User> users = new ArrayList<>();

        Subscription subscription = new Subscription();
        subscription.setNomenclatureOptions(new ArrayList<>());
        subscription.setOfferTypes(new ArrayList<>());
        subscription.setUsers(new ArrayList<>());
        UserDataModel userData = new UserDataModel("1", "Иванов Иван", "911", "", "Мужской", null, subscription);

        users.add(userData.getUser());

        repository.saveAll(users);

    }


}

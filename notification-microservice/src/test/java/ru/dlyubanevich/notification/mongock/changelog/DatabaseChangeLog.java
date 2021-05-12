package ru.dlyubanevich.notification.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.dlyubanevich.notification.domain.Subscription;
import ru.dlyubanevich.notification.domain.User;
import ru.dlyubanevich.notification.repository.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dropDb", author = "dlyubanevich", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertSubscriptions", author = "dlyubanevich", runAlways = true)
    public void insertSubscriptions(SubscriptionRepository repository){

        List<Subscription> list = new ArrayList<>();
        list.add(new Subscription("1", new User("1", "Ivan"), Collections.singletonList("2"), Collections.singletonList("001"), Collections.singletonList("Спрос")));
        list.add(new Subscription("2", new User("2", "Petr"), Collections.singletonList("1"), Collections.singletonList("001"), Collections.singletonList("Предложение")));
        repository.saveAll(list);

    }
}

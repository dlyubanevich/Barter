package ru.dlyubanevich.notification.domain;

import java.util.Collections;
import java.util.List;

public class Status {

    public final static String NEW = "Новое";
    public final static String SENT = "Отправлено";

    private final List<String> activeStatuses = Collections.singletonList(NEW);

    public List<String> getActiveStatuses() {
        return activeStatuses;
    }
}

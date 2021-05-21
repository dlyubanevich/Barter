package ru.dlyubanevich.bottelegrammicroservice.stage;

public interface StateOrder<T> {

    T getFirstState();
    T getNextState(T state);
    T getLastState();
}

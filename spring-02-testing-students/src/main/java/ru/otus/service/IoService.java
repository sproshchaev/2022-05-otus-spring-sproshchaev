package ru.otus.service;

/**
 * Интерфейс IOService
 */
public interface IoService {
    int readInt();
    String readString();
    void writeString(String string);
    void writeLnString(String string);
}

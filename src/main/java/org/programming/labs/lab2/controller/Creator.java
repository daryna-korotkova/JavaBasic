package org.programming.labs.lab2.controller;

public interface Creator<T, H> {
    T create(String name, H head);
}

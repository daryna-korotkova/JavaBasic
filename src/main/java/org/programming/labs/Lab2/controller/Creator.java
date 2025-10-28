package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Human;
import org.programming.labs.Lab2.model.Student;

public interface Creator<T, H extends Human> {
    T create(String name, H head);
}

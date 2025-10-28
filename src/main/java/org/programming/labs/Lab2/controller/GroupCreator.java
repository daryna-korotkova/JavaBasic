package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Group;
import org.programming.labs.Lab2.model.Human;

public class GroupCreator implements Creator<Group, Human> {

    @Override
    public Group create(String name, Human head) {
        return new Group(name, head);
    }
}
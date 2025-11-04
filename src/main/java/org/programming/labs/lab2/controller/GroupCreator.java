package org.programming.labs.lab2.controller;

import org.programming.labs.lab2.model.Group;
import org.programming.labs.lab2.model.Human;
import org.programming.labs.lab2.model.Sex;
import org.programming.labs.lab2.model.Student;

public class GroupCreator implements Creator<Group, Human> {
    private final StudentCreator studentCreator;

    public GroupCreator(StudentCreator studentCreator) {
        this.studentCreator = studentCreator;
    }

    @Override
    public Group create(String name, Human head) {
        return new Group(name, head);
    }

    public Group createTypicalGroupCS(Human groupHead, int groupNumber) {
        Group group = create("CS-" + groupNumber, groupHead);

        Student s1 = studentCreator.createStudent("Dmytro", "Hnatiuk", "Stepanovych", Sex.MALE, 1000 + groupNumber + 1);
        Student s2 = studentCreator.createStudent("Mariia", "Oliynyk", "Olehivna", Sex.FEMALE, 1000 + groupNumber + 2);

        group.addStudent(s1);
        group.addStudent(s2);

        return group;
    }

    public Group createTypicalGroupSE(Human groupHead, int groupNumber) {
        Group group = create("SE-" + groupNumber, groupHead);

        Student s4 = studentCreator.createStudent("Maksym", "Petrenko", "Serhiyovych", Sex.MALE, 2000 + groupNumber + 1);

        group.addStudent(s4);

        return group;
    }
}
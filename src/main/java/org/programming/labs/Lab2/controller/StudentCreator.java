package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Human;
import org.programming.labs.Lab2.model.Sex;
import org.programming.labs.Lab2.model.Student;

public class StudentCreator {

    public Student create(String firstName, String lastName, String patronymic, Sex sex, int studentId) {
        return new Student(firstName, lastName, patronymic, sex, studentId);
    }
}

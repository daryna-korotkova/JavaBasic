package org.programming.labs.lab2.controller;

import org.programming.labs.lab2.model.Human;
import org.programming.labs.lab2.model.Sex;
import org.programming.labs.lab2.model.Student;

public class StudentCreator implements Creator<Student, Human> {

    public Student create(String firstName, String lastName, String patronymic, Sex sex, int studentId) {
        return new Student(firstName, lastName, patronymic, sex);
    }

    @Override
    public Student create(String name, Human head) {
        return new Student(name, head.getLastName(), head.getPatronymic(), head.getSex());
    }

    public Student createStudent(String firstName, String lastName, String patronymic, Sex sex, int studentId) {
        return new Student(firstName, lastName, patronymic, sex);
    }
}

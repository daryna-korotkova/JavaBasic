package org.programming.labs.Lab2.model;

public class Student extends Human {
    private final int studentId;

    public Student(String firstName, String lastName, String patronymic, Sex sex, int studentId) {
        super(firstName, lastName, patronymic, sex);
        this.studentId = studentId;
    }

    public int getStudentId() { return studentId; }

    @Override
    public String toString() {
        return "Student: " + super.toString() + ", ID: " + studentId;
    }
}

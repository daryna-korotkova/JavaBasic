package org.programming.labs.Lab2.model;

public class Human {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Sex sex;

    public Human(String firstName, String lastName, String patronymic, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPatronymic() { return patronymic; }
    public Sex getSex() { return sex; }

    @Override
    public String toString() {
        return lastName + " " + firstName.charAt(0) + ". " + patronymic.charAt(0) + ". (" + sex.name() + ")";
    }
}

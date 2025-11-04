package org.programming.labs.lab2.model;

import java.util.Objects;

public class Human {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Sex sex;

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

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    public void setSex(Sex sex) { this.sex = sex; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(firstName, human.firstName) && Objects.equals(lastName, human.lastName) && Objects.equals(patronymic, human.patronymic) && sex == human.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, sex);
    }


    @Override
    public String toString() {
        return lastName + " " + firstName.charAt(0) + "." + (patronymic.isEmpty() ? "" : patronymic.charAt(0) + ".");
    }
}
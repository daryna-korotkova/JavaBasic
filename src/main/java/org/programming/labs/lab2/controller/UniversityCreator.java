package org.programming.labs.lab2.controller;

import org.programming.labs.lab2.model.Faculty;
import org.programming.labs.lab2.model.Human;
import org.programming.labs.lab2.model.Sex;
import org.programming.labs.lab2.model.University;

public class UniversityCreator implements Creator<University, Human> {
    private final FacultyCreator facultyCreator;

    public UniversityCreator(FacultyCreator facultyCreator) {
        this.facultyCreator = facultyCreator;
    }

    @Override
    public University create(String name, Human head) {
        return new University(name, head);
    }

    public Human createTypicalRector() {
        return new Human("Olexandr", "Melnyk", "Ivanovich", Sex.MALE);
    }

    private Human createTypicalDean(String name, Sex sex) {
        if (sex == Sex.FEMALE) return new Human("Olena", name, "Ivanivna", sex);
        return new Human("Viktor", name, "Oleksiyovich", sex);
    }

    public University createTypicalUniversity(String name) {
        Human rector = createTypicalRector();
        University university = create(name, rector);

        Human dean1 = createTypicalDean("Tkachenko", Sex.FEMALE);
        Human dean2 = createTypicalDean("Lysenko", Sex.MALE);

        Faculty fit = facultyCreator.createTypicalFacultyIT(dean1);
        Faculty econ = facultyCreator.createTypicalFacultyEconomics(dean2);

        university.addFaculty(fit);
        university.addFaculty(econ);

        return university;
    }
}
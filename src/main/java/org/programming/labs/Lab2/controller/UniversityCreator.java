package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Faculty;
import org.programming.labs.Lab2.model.Human;
import org.programming.labs.Lab2.model.University;

public class UniversityCreator implements Creator<University, Human> {
    private final FacultyCreator facultyCreator;

    public UniversityCreator(FacultyCreator facultyCreator) {
        this.facultyCreator = facultyCreator;
    }

    @Override
    public University create(String name, Human head) {
        return new University(name, head);
    }

    public void addFacultyToUniversity(University university, String facName, Human facHead) {
        Faculty faculty = facultyCreator.create(facName, facHead);
        university.addFaculty(faculty);
    }
}

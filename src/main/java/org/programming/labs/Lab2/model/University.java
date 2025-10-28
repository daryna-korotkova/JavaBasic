package org.programming.labs.Lab2.model;

import java.util.ArrayList;
import java.util.List;

public class University extends AbstractUnit {
    private final List<Faculty> faculties = new ArrayList<>();

    public University(String name, Human head) {
        super(name, head);
    }

    public void addFaculty(Faculty faculty) {
        this.faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public String getDetails() {
        return String.format("University: %s, Rector: %s, Faculties: %d",
                getName(), getHead(), faculties.size());
    }
}

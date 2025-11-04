package org.programming.labs.lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class University extends Unit {
    private final List<Faculty> faculties = new ArrayList<>();

    public University(String name, Human head) { super(name, head); }

    public void addFaculty(Faculty faculty) { faculties.add(faculty); }
    public boolean removeFaculty(Faculty faculty) { return faculties.remove(faculty); }

    public List<Faculty> getFaculties() { return faculties; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        University that = (University) o;
        return Objects.equals(faculties, that.faculties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculties);
    }
}

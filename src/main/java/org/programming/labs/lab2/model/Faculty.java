package org.programming.labs.lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Faculty extends Unit {
    private final List<Department> departments = new ArrayList<>();

    public Faculty(String name, Human head) {
        super(name, head);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public boolean removeDepartment(Department department) {
        return departments.remove(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(departments, faculty.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departments);
    }
}

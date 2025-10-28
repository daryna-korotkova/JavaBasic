package org.programming.labs.Lab2.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends AbstractUnit {
    private final List<Department> departments = new ArrayList<>();

    public Faculty(String name, Human head) {
        super(name, head);
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    @Override
    public String getDetails() {
        return String.format("Faculty: %s, Dean: %s, Departments: %d",
                getName(), getHead(), departments.size());
    }
}

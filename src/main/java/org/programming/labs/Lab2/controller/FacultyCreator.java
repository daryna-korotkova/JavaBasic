package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Department;
import org.programming.labs.Lab2.model.Faculty;
import org.programming.labs.Lab2.model.Human;

public class FacultyCreator implements Creator<Faculty, Human> {
    private final DepartmentCreator departmentCreator;

    public FacultyCreator(DepartmentCreator departmentCreator) {
        this.departmentCreator = departmentCreator;
    }

    @Override
    public Faculty create(String name, Human head) {
        return new Faculty(name, head);
    }

    public void addDepartmentToFaculty(Faculty faculty, String depName, Human depHead) {
        Department department = departmentCreator.create(depName, depHead);
        faculty.addDepartment(department);
    }
}

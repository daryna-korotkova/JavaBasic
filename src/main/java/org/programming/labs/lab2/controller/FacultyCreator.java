package org.programming.labs.lab2.controller;

import org.programming.labs.lab2.model.Department;
import org.programming.labs.lab2.model.Faculty;
import org.programming.labs.lab2.model.Human;
import org.programming.labs.lab2.model.Sex;

public class FacultyCreator implements Creator<Faculty, Human> {
    private final DepartmentCreator departmentCreator;

    public FacultyCreator(DepartmentCreator departmentCreator) {
        this.departmentCreator = departmentCreator;
    }

    @Override
    public Faculty create(String name, Human head) {
        return new Faculty(name, head);
    }

    private Human createTypicalDeptHead(String name, Sex sex) {
        if (sex == Sex.MALE) return new Human("Serhiy", name, "Mykolayovych", sex);
        return new Human("Anna", name, "Petrivna", sex);
    }

    public Faculty createTypicalFacultyIT(Human dean) {
        Faculty faculty = create("Faculty of Information Technology", dean);

        Human headOfDep1 = createTypicalDeptHead("Koval", Sex.MALE);
        Human headOfDep2 = createTypicalDeptHead("Moroz", Sex.FEMALE);

        Department csDep = departmentCreator.createTypicalDepartmentCS(headOfDep1);
        Department seDep = departmentCreator.createTypicalDepartmentSE(headOfDep2);

        faculty.addDepartment(csDep);
        faculty.addDepartment(seDep);

        return faculty;
    }

    public Faculty createTypicalFacultyEconomics(Human dean) {
        Faculty faculty = create("Faculty of Economics", dean);
        return faculty;
    }
}

package org.programming.labs.lab2.controller;

import org.programming.labs.lab2.model.Department;
import org.programming.labs.lab2.model.Group;
import org.programming.labs.lab2.model.Human;
import org.programming.labs.lab2.model.Sex;

public class DepartmentCreator implements Creator<Department, Human> {
    private final GroupCreator groupCreator;

    public DepartmentCreator(GroupCreator groupCreator) {
        this.groupCreator = groupCreator;
    }

    @Override
    public Department create(String name, Human head) {
        return new Department(name, head);
    }

    private Human createTypicalGroupHead(String name, Sex sex) {
        if (sex == Sex.FEMALE) return new Human("Iryna", name, "Andriyivna", sex);
        return new Human("Taras", name, "Vasyliovych", sex);
    }

    public Department createTypicalDepartmentCS(Human headOfDep) {
        Department department = create("Department of Computer Science", headOfDep);

        Human groupHead1 = createTypicalGroupHead("Melnyk", Sex.FEMALE);
        Human groupHead2 = createTypicalGroupHead("Shevchenko", Sex.MALE);

        Group groupCS1 = groupCreator.createTypicalGroupCS(groupHead1, 21);
        Group groupCS2 = groupCreator.createTypicalGroupCS(groupHead2, 22);

        department.addGroup(groupCS1);
        department.addGroup(groupCS2);

        return department;
    }

    public Department createTypicalDepartmentSE(Human headOfDep) {
        Department department = create("Department of Software Engineering", headOfDep);

        Human groupHead = createTypicalGroupHead("Shevchenko", Sex.MALE);

        Group groupSE1 = groupCreator.createTypicalGroupSE(groupHead, 21);

        department.addGroup(groupSE1);

        return department;
    }
}
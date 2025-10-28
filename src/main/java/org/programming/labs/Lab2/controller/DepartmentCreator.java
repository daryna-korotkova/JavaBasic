package org.programming.labs.Lab2.controller;

import org.programming.labs.Lab2.model.Department;
import org.programming.labs.Lab2.model.Group;
import org.programming.labs.Lab2.model.Human;

public class DepartmentCreator implements Creator<Department, Human> {
    private final GroupCreator groupCreator;

    public DepartmentCreator(GroupCreator groupCreator) {
        this.groupCreator = groupCreator;
    }

    @Override
    public Department create(String name, Human head) {
        return new Department(name, head);
    }

    public void addGroupToDepartment(Department department, String groupName, Human groupHead) {
        Group group = groupCreator.create(groupName, groupHead);
        department.addGroup(group);
    }
}

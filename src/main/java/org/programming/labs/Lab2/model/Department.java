package org.programming.labs.Lab2.model;

import java.util.ArrayList;
import java.util.List;

public class Department extends AbstractUnit {
    private final List<Group> groups = new ArrayList<>();

    public Department(String name, Human head) {
        super(name, head);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public String getDetails() {
        return String.format("Department: %s, Manager: %s, Groups: %d",
                getName(), getHead(), groups.size());
    }
}

package org.programming.labs.lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department extends Unit {
    private final List<Group> groups = new ArrayList<>();

    public Department(String name, Human head) { super(name, head); }

    public void addGroup(Group group) { groups.add(group); }
    public boolean removeGroup(Group group) { return groups.remove(group); }

    public List<Group> getGroups() { return groups; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Department that = (Department) o;
        return Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), groups);
    }
}

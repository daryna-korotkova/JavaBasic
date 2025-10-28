package org.programming.labs.Lab2.model;

import java.util.ArrayList;
import java.util.List;

public class Group extends AbstractUnit {
    private final List<Student> students = new ArrayList<>();

    public Group(String name, Human head) {
        super(name, head);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String getDetails() {
        return String.format("Group: %s, Headman: %s, Students: %d",
                getName(), getHead(), students.size());
    }
}

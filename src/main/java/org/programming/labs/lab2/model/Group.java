package org.programming.labs.lab2.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group extends Unit {
    private final List<Student> students = new ArrayList<>();

    public Group(String name, Human head) { super(name, head); }

    public void addStudent(Student student) { students.add(student); }
    public boolean removeStudent(Student student) { return students.remove(student); }

    public List<Student> getStudents() { return students; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), students);
    }
}
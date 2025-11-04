package org.programming.labs.lab2.model;

import java.util.Objects;

public abstract class Unit {
    private String name;
    private Human head;

    public Unit(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    public String getName() { return name; }
    public Human getHead() { return head; }

    public void setName(String name) { this.name = name; }
    public void setHead(Human head) { this.head = head; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(name, unit.name) && Objects.equals(head, unit.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, head);
    }

    @Override
    public String toString() {
        return getName() + " (Head: " + getHead() + ")";
    }
}
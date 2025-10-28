package org.programming.labs.Lab2.model;

public abstract class AbstractUnit implements Unit {
    private final String name;
    private final Human head;

    public AbstractUnit(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Human getHead() {
        return head;
    }

    public abstract String getDetails();
}

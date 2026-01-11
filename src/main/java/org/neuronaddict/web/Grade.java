package org.neuronaddict.web;

public enum Grade {

    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    INTERN("Stagiaire");

    Grade(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}

package org.neuronaddict.data;

public enum Grade {

    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    INTERN("Stagiaire");

    Grade(String value) {
        this.value = value;
    }

    private final String value;

    @Override
    public String toString() {
        return value;
    }

}

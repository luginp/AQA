package project.models;

import java.util.List;
import java.util.Objects;

public class Catalog {
    private List<Test> tests;

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "tests=" + tests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Catalog catalog = (Catalog) o;
        return tests.containsAll(catalog.tests);
    }
}

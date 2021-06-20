package com.max.util;

public abstract class DoPrintFileAdaptor implements Visitable {
    private String description;

    protected DoPrintFileAdaptor(String description) {
        this.description = description;
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}

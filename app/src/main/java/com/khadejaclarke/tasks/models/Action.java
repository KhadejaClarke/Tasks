package com.khadejaclarke.tasks.models;

public class Action {
    private String name;
    private Integer icon;

    // Parameterised Constructor
    public Action(String name, Integer icon) {
        this.name = name;
        this.icon = icon;
    }

    // Getters
    public String getName() {return this.name;}

    public Integer getIcon() {return this.icon;}
}
package com.example.demo.owner.model;

public class OwnerProfile {

    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

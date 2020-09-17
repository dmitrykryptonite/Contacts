package com.example.contacts.domain.entities;

public class Contact {
    private int id;
    private String name;
    private String callNumber;

    public Contact(int id, String name, String callNumber) {
        this.id = id;
        this.name = name;
        this.callNumber = callNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}

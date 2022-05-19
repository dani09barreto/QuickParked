package org.devscite.Entities.Model;

import java.util.UUID;

public class Employee extends User{
    private UUID id;
    private String name;
    private Integer document;
    private Integer number;

    public Employee(String username, String passWord ,UUID id, String name, Integer document, Integer number) {
        super(username, passWord);
        this.id = UUID.randomUUID();
        this.name = name;
        this.document = document;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

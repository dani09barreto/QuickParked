package org.devscite.Entities.Model;

import java.math.BigDecimal;
import java.util.UUID;

public class Employee extends UserParking {
    private UUID id;
    private String name;
    private BigDecimal document;
    private BigDecimal number;

    public Employee(String username, String passWord , String name, BigDecimal document, BigDecimal number) {
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

    public BigDecimal getDocument() {
        return document;
    }

    public void setDocument(BigDecimal document) {
        this.document = document;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}

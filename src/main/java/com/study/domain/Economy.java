package com.study.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Economy {
    private int id;
    private String type;

    private Set<Ticket> tickets;

    public Economy() { }

    public Economy(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Economy{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Economy economy = (Economy) o;
        return id == economy.id && Objects.equals(type, economy.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}

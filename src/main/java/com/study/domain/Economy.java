package com.study.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an economy class with an ID, type, and a set of associated tickets.
 * */
public class Economy {
    private int id;
    private String type;
    private Set<Ticket> tickets = new HashSet<>();

    public Economy() { }

    public Economy(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Economy id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Economy type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Sets the set of tickets associated with the economy class.
     * If the economy class already has tickets, it disassociates them before setting the new tickets.
     *
     * @param tickets the set of tickets to associate with the economy class
     * */
    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null){
            for (Ticket ticket : this.tickets){
                ticket.setEconomy(null);
            }
        }
        if (tickets != null){
            for (Ticket ticket : tickets){
                ticket.setEconomy(this);
            }
        }
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        tickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "Economy{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", ticket=" + tickets +
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

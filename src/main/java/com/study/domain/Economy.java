package com.study.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Economy {
    private int id;
    private String type;
    private Set<Ticket> tickets = new HashSet<Ticket>();

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

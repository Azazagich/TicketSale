package com.study.domain;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an age group with an ID and a type.
 * Each age group can have a set of associated tickets.
 * */
public class AgeGroup {
    private int id;
    private String type;
    private Set<Ticket> tickets = new HashSet<>();

    public AgeGroup() { }

    public AgeGroup(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public AgeGroup type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public AgeGroup id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }


    /**
     * Sets the set of tickets associated with the age group.
     * If the age group already has tickets, it disassociates them before setting the new tickets.
     *
     * @param tickets the set of tickets to associate with the age group
     * */
    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null){
            for (Ticket ticket : this.tickets){
                ticket.setAgeGroup(null);
            }
        }
        if (tickets != null){
            for (Ticket ticket : tickets){
                ticket.setAgeGroup(this);
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
    public String toString(){
        return "AgeGroup {" +
                "id" + id +
                "type" + type +
                ", ticket=" + tickets +
                "}";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        AgeGroup ageGroup = (AgeGroup) obj;
        return ageGroup.id == id && ageGroup.type.equals(type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}

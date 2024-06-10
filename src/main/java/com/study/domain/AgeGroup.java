package com.study.domain;
import java.util.HashSet;
import java.util.Set;

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

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

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
                "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        AgeGroup ageGroup = (AgeGroup) obj;
        return ageGroup.id == id && ageGroup.type.equals(type);
    }

    @Override
    public int hashCode(){
       int hash = 17;
       hash *= 31 + Integer.hashCode(id);
       if (type != null) hash *= 31 + type.hashCode();
       return hash;
    }
}

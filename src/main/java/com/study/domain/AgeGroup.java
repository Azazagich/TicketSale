package com.study.domain;
import java.util.Set;


//    public void addTicket(Ticket ticket){
//        tickets.add(ticket);
//    }
//
//    public void removeTicket(Ticket ticket){
//        tickets.remove(ticket);
//    }

public class AgeGroup {
    private int id;
    private String type;
    private Set<Ticket> tickets;

    public AgeGroup() { }

    public AgeGroup(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
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

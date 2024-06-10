package com.study.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a discount in the system.
 * The Discount class contains information about a discount such as its type,
 * percentage, and optional start and end dates. The class provides constructors for
 * creating discounts with mandatory fields and optional methods to set additional fields.
 * */

public class Discount {
    private int id;
    private String type;
    private Double percent;
    private LocalDate startAt; //optional
    private LocalDate endAt; //optional

    private Set<Ticket> tickets = new HashSet<>();

    public Discount(){ }

    public Discount(int id, String type, Double percent) {
        this.id = id;
        this.type = type;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public Discount id(int id) {
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Discount type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPercent() {
        return percent;
    }

    public Discount percent(Double percent) {
        this.percent = percent;
        return this;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public Discount startAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public Discount endAt(LocalDate endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Sets the set of tickets associated with the discount.
     * If the discount already has tickets, it disassociates them before setting the new tickets.
     *
     * @param tickets the set of tickets to associate with the discount
     * */
    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null){
            for (Ticket ticket : this.tickets){
                ticket.setDiscounts(null);
            }
        }
        if (tickets != null){
            for (Ticket ticket : tickets){
                ticket.addDiscount(this);
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
        return "Discount{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", percent=" + percent +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id && Objects.equals(type, discount.type) && Objects.equals(percent, discount.percent)
                && Objects.equals(startAt, discount.startAt) && Objects.equals(endAt, discount.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, percent, startAt, endAt);
    }

}

package com.study.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Discount {
    private int id;
    private String type;
    private Double percent;
    private LocalDate startAt; //optional
    private LocalDate endAt; //optional

    private Set<Ticket> tickets;

    public Discount(){ }

    public Discount(int id, String type, Double percent) {
        this.id = id;
        this.type = type;
        this.percent = percent;
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

    public Double getPercent() {
        return percent;
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

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
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

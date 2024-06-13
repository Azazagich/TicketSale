package com.study.service.dto;

import com.study.domain.Ticket;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.Discount} entity.
 */
public class DiscountDTO implements Serializable {
    private int id;
    private String type;
    private Double percent;
    private LocalDate startAt; //optional
    private LocalDate endAt; //optional

    private Set<TicketDTO> tickets = new HashSet<>();

    public DiscountDTO(){ }

    public DiscountDTO(int id, String type, Double percent) {
        this.id = id;
        this.type = type;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public DiscountDTO id(int id) {
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public DiscountDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPercent() {
        return percent;
    }

    public DiscountDTO percent(Double percent) {
        this.percent = percent;
        return this;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public DiscountDTO startAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public DiscountDTO endAt(LocalDate endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "DiscountDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", percent=" + percent +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountDTO discountDTO = (DiscountDTO) o;
        return id == discountDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

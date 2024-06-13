package com.study.service.dto;

import com.study.domain.AgeGroup;
import com.study.domain.Ticket;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.AgeGroup} entity.
 */
public class AgeGroupDTO implements Serializable {
    private int id;
    private String type;
    private Set<TicketDTO> tickets = new HashSet<>();

    public AgeGroupDTO() { }

    public AgeGroupDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public AgeGroupDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public AgeGroupDTO id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "AgeGroupDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeGroupDTO ageGroupDTO = (AgeGroupDTO) o;
        return id == ageGroupDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

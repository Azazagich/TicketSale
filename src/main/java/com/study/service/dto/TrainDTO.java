package com.study.service.dto;

import com.study.domain.Ticket;
import com.study.domain.Train;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.Train} entity.
 */
public class TrainDTO implements Serializable {

    private int id;
    private int amountOfSeats;
    private String trainModel; //optional
    private Set<Ticket> tickets = new HashSet<>();
    public TrainDTO(){ }

    public TrainDTO(int id, int amountOfSeats) {
        this.id = id;
        this.amountOfSeats = amountOfSeats;
    }

    public int getId() {
        return id;
    }

    public TrainDTO id(int id) {
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(String trainModel) {
        this.trainModel = trainModel;
    }

    public TrainDTO trainModel(String trainModel) {
        this.trainModel = trainModel;
        return this;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public TrainDTO amountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
        return this;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "id=" + id +
                ", amountOfSeats=" + amountOfSeats +
                ", trainModel='" + trainModel + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDTO trainDTO = (TrainDTO) o;
        return id == trainDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

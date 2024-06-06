package com.study.domain;

import java.util.Objects;
import java.util.Set;

public class Train {
    private int id;
    private int amountOfSeats;
    private String trainModel; //optional
    private Set<Ticket> tickets;
    public Train(){ }

    public Train(int id, int amountOfSeats) {
        this.id = id;
        this.amountOfSeats = amountOfSeats;
    }

    public int getId() {
        return id;
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

    public Train trainModel(String trainModel) {
        this.trainModel = trainModel;
        return this;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
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
        return "Train{" +
                "id=" + id +
                ", amountOfSeats=" + amountOfSeats +
                ", trainModel='" + trainModel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return id == train.id &&
                amountOfSeats == train.amountOfSeats &&
                Objects.equals(trainModel, train.trainModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountOfSeats, trainModel);
    }
}

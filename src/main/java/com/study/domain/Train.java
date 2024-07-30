package com.study.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a train with an ID, number of seats, and optional model.
 * It also maintains a set of tickets associated with the train.
 * */
public class Train {
    private int id;
    private int amountOfSeats;
    private String trainModel; //optional
    private Set<Ticket> tickets = new HashSet<>();
    public Train(){ }

    public Train(int id, int amountOfSeats) {
        this.id = id;
        this.amountOfSeats = amountOfSeats;
    }

    public int getId() {
        return id;
    }

    public Train id(int id) {
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

    public Train trainModel(String trainModel) {
        this.trainModel = trainModel;
        return this;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public Train amountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
        return this;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Sets the set of tickets associated with the train.
     * If the train already has tickets, it disassociates them before setting the new tickets.
     *
     * @param tickets the set of tickets to associate with the train
     * */
    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null){
            for (Ticket ticket : this.tickets){
                ticket.setTrain(null);
            }
        }
        if (tickets != null){
            for (Ticket ticket : tickets){
                ticket.setTrain(this);
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

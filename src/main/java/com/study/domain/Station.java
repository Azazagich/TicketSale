package com.study.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a train station with an ID, name, address, and optional phone number.
 * It also maintains a set of tickets associated with the station.
 * */
public class Station {
    private int id;
    private String nameOfStation;
    private String addressLocation;
    private String stationPhone; //optional
    private Set<Ticket> tickets = new HashSet<>();

    public Station() { }

    public Station(int id, String nameOfStation, String addressLocation, String stationPhone) {
        this.id = id;
        this.nameOfStation = nameOfStation;
        this.addressLocation = addressLocation;
    }

    public Station stationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
        return this;
    }

    public void setStationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
    }

    public int getId() {
        return id;
    }

    public Station id(int id) {
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public Station nameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
        return this;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public Station addressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
        return this;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }

    public String getStationPhone() {
        return stationPhone;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Sets the set of tickets associated with the station.
     * If the station already has tickets, it disassociates them before setting the new tickets.
     *
     * @param tickets the set of tickets to associate with the station
     * */
    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null){
            for (Ticket ticket : this.tickets){
                ticket.setStartStation(null);
                ticket.setEndStation(null);
            }
        }
        if (tickets != null){
            for (Ticket ticket : tickets){
                ticket.setStartStation(this);
                ticket.setEndStation(this);
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
        return "Station{" +
                "id=" + id +
                ", nameOfStation='" + nameOfStation + '\'' +
                ", addressLocation='" + addressLocation + '\'' +
                ", stationPhone='" + stationPhone + '\'' +
                ", ticket=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id &&
                Objects.equals(nameOfStation, station.nameOfStation) &&
                Objects.equals(addressLocation, station.addressLocation) &&
                Objects.equals(stationPhone, station.stationPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfStation, addressLocation, stationPhone);
    }
}

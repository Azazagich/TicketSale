package com.study.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Station {
    private int id;
    private String nameOfStation;
    private String addressLocation;
    private String stationPhone; //optional
    private Set<Ticket> tickets;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getAddressLocation() {
        return addressLocation;
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

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", nameOfStation='" + nameOfStation + '\'' +
                ", addressLocation='" + addressLocation + '\'' +
                ", stationPhone='" + stationPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id && Objects.equals(nameOfStation, station.nameOfStation) && Objects.equals(addressLocation, station.addressLocation) && Objects.equals(stationPhone, station.stationPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfStation, addressLocation, stationPhone);
    }
}

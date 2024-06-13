package com.study.service.dto;

import com.study.domain.Station;
import com.study.domain.Ticket;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.Station} entity.
 */
public class StationDTO implements Serializable {
    private int id;
    private String nameOfStation;
    private String addressLocation;
    private String stationPhone; //optional
    private Set<TicketDTO> tickets = new HashSet<>();

    public StationDTO() { }

    public StationDTO(int id, String nameOfStation, String addressLocation, String stationPhone) {
        this.id = id;
        this.nameOfStation = nameOfStation;
        this.addressLocation = addressLocation;
    }

    public StationDTO stationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
        return this;
    }

    public void setStationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
    }

    public int getId() {
        return id;
    }

    public StationDTO id(int id) {
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public StationDTO nameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
        return this;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public StationDTO addressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
        return this;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }

    public String getStationPhone() {
        return stationPhone;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }


    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "StationDTO{" +
                "id=" + id +
                ", nameOfStation='" + nameOfStation + '\'' +
                ", addressLocation='" + addressLocation + '\'' +
                ", stationPhone='" + stationPhone + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDTO stationDTO = (StationDTO) o;
        return id == stationDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

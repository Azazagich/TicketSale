package com.study.service.dto;

import com.study.domain.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.Ticket} entity.
 */
public class TicketDTO implements Serializable {
    private int id;
    private LocalDate departDateBooking;
    private LocalDate returnDateBooking; //optional
    private LocalDate registrationDateTicket;
    private LocalDate returnDateTicket; //optional
    private double price;
    private UserDTO user;
    private StationDTO startStation;
    private StationDTO endStation;
    private TrainDTO train;
    private EconomyDTO economy;
    private AgeGroupDTO ageGroup;
    private Set<DiscountDTO> discounts = new HashSet<>(); //optional

    public TicketDTO(){ }

    public TicketDTO(int id, LocalDate departDateBooking, LocalDate registrationDateTicket, double price,
                  UserDTO userDTO, StationDTO startStationDTO, StationDTO endStationDTO, TrainDTO trainDTO, EconomyDTO economyDTO, AgeGroupDTO ageGroupDTO) {
        this.id = id;
        this.departDateBooking = departDateBooking;
        this.registrationDateTicket = registrationDateTicket;
        this.price = price;
        this.user = userDTO;
        this.startStation = startStationDTO;
        this.endStation = endStationDTO;
        this.train = trainDTO;
        this.economy = economyDTO;
        this.ageGroup = ageGroupDTO;
    }

    public TicketDTO price(double price) {
        this.price = price;
        return this;
    }

    public TicketDTO discount(Set<DiscountDTO> discountsDTO) {
        this.discounts = discountsDTO;
        return this;
    }

    public TicketDTO returnDateBooking(LocalDate returnDateBooking) {
        this.returnDateBooking = returnDateBooking;
        return this;
    }

    public TicketDTO returnDateTicket(LocalDate returnDateTicket) {
        this.returnDateTicket = returnDateTicket;
        return this;
    }

    public void setReturnDateBooking(LocalDate returnDateBooking) {
        this.returnDateBooking = returnDateBooking;
    }

    public void setReturnDateTicket(LocalDate returnDateTicket) {
        this.returnDateTicket = returnDateTicket;
    }

    public void setDiscounts(Set<DiscountDTO> discountsDTO) {
        this.discounts = discountsDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public StationDTO getStartStation() {
        return startStation;
    }

    public void setStartStation(StationDTO startStation) {
        this.startStation = startStation;
    }

    public StationDTO getEndStation() {
        return endStation;
    }

    public void setEndStation(StationDTO endStation) {
        this.endStation = endStation;
    }

    public TrainDTO getTrain() {
        return train;
    }


    public void setTrain(TrainDTO train) {
        this.train = train;
    }

    public EconomyDTO getEconomy() {
        return economy;
    }


    public void setEconomy(EconomyDTO economy) {
        this.economy = economy;
    }

    public AgeGroupDTO getAgeGroup() {
        return ageGroup;
    }


    public void setAgeGroup(AgeGroupDTO ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Set<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public int getId() {
        return id;
    }

    public TicketDTO id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDepartDateBooking() {
        return departDateBooking;
    }

    public TicketDTO departDateBooking(LocalDate departDateBooking) {
        this.departDateBooking = departDateBooking;
        return this;
    }

    public void setDepartDateBooking(LocalDate departDateBooking) {
        this.departDateBooking = departDateBooking;
    }

    public LocalDate getReturnDateBooking() {
        return returnDateBooking;
    }

    public LocalDate getRegistrationDateTicket() {
        return registrationDateTicket;
    }

    public TicketDTO registrationDateTicket(LocalDate registrationDateTicket) {
        this.registrationDateTicket = registrationDateTicket;
        return this;
    }

    public void setRegistrationDateTicket(LocalDate registrationDateTicket) {
        this.registrationDateTicket = registrationDateTicket;
    }

    public LocalDate getReturnDateTicket() {
        return returnDateTicket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", departDateBooking=" + departDateBooking +
                ", returnDateBooking=" + returnDateBooking +
                ", registrationDateTicket=" + registrationDateTicket +
                ", returnDateTicket=" + returnDateTicket +
                ", price=" + price +
                ", user=" + user +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", train=" + train +
                ", economy=" + economy +
                ", ageGroup=" + ageGroup +
                ", discounts=" + discounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof TicketDTO)){
            return false;
        }
        return id == ((TicketDTO)o).id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

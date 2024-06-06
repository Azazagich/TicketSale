package com.study.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Ticket {
    private int id;
    private LocalDate departDateBooking;
    private LocalDate returnDateBooking; //optional
    private LocalDate registrationDateTicket;
    private LocalDate returnDateTicket; //optional
    private double price;
    private User user;
    private Station station;
    private Train train;
    private Economy economy;
    private AgeGroup ageGroup;
    private List<Discount> discount; //optional

    public Ticket(){ }

    public Ticket(int id, LocalDate departDateBooking, LocalDate registrationDateTicket, double price,
                  User user, Station station, Train train, Economy economy, AgeGroup ageGroup) {
        this.id = id;
        this.departDateBooking = departDateBooking;
        this.registrationDateTicket = registrationDateTicket;
        this.price = price;
        this.user = user;
        this.station = station;
        this.train = train;
        this.economy = economy;
        this.ageGroup = ageGroup;
    }

    public Ticket discount(List<Discount> discount) {
        this.discount = discount;
        return this;
    }

    public Ticket returnDateBooking(LocalDate returnDateBooking) {
        this.returnDateBooking = returnDateBooking;
        return this;
    }

    public Ticket returnDateTicket(LocalDate returnDateTicket) {
        this.returnDateTicket = returnDateTicket;
        return this;
    }

    public void setReturnDateBooking(LocalDate returnDateBooking) {
        this.returnDateBooking = returnDateBooking;
    }

    public void setReturnDateTicket(LocalDate returnDateTicket) {
        this.returnDateTicket = returnDateTicket;
    }

    public void setDiscount(List<Discount> discount) {
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Economy getEconomy() {
        return economy;
    }

    public void setEconomy(Economy economy) {
        this.economy = economy;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public List<Discount> getDiscount() {
        return discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDepartDateBooking() {
        return departDateBooking;
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
        return "Ticket{" +
                "id=" + id +
                ", departDateBooking=" + departDateBooking +
                ", returnDateBooking=" + returnDateBooking +
                ", registrationDateTicket=" + registrationDateTicket +
                ", returnDateTicket=" + returnDateTicket +
                ", price=" + price +
                ", user=" + user +
                ", station=" + station +
                ", train=" + train +
                ", economy=" + economy +
                ", ageGroup=" + ageGroup +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Double.compare(price, ticket.price) == 0 &&
                Objects.equals(departDateBooking, ticket.departDateBooking) &&
                Objects.equals(returnDateBooking, ticket.returnDateBooking) &&
                Objects.equals(registrationDateTicket, ticket.registrationDateTicket) &&
                Objects.equals(returnDateTicket, ticket.returnDateTicket) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(station, ticket.station) &&
                Objects.equals(train, ticket.train) &&
                Objects.equals(economy, ticket.economy) &&
                Objects.equals(ageGroup, ticket.ageGroup) &&
                Objects.equals(discount, ticket.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departDateBooking, returnDateBooking, registrationDateTicket,
                returnDateTicket, price, user, station, train, economy, ageGroup, discount);
    }
}

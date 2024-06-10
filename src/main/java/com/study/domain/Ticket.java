package com.study.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Represents a ticket in the system with booking details and associated entities.
 * The Ticket class contains information about a ticket such as booking dates, price,
 * user information, start and end stations, train details, economy class, age group,
 * and optional discounts. The class provides constructors for creating tickets with
 * fields and optional methods to set additional fields.
 * */

public class Ticket {
    private int id;
    private LocalDate departDateBooking;
    private LocalDate returnDateBooking; //optional
    private LocalDate registrationDateTicket;
    private LocalDate returnDateTicket; //optional
    private double price;
    private User user;
    private Station startStation;
    private Station endStation;
    private Train train;
    private Economy economy;
    private AgeGroup ageGroup;
    private Set<Discount> discounts = new HashSet<>(); //optional

    public Ticket(){ }

    public Ticket(int id, LocalDate departDateBooking, LocalDate registrationDateTicket, double price,
                  User user, Station startStation, Station endStation, Train train, Economy economy, AgeGroup ageGroup) {
        this.id = id;
        this.departDateBooking = departDateBooking;
        this.registrationDateTicket = registrationDateTicket;
        this.price = price;
        this.user = user;
        this.startStation = startStation;
        this.endStation = endStation;
        this.train = train;
        this.economy = economy;
        this.ageGroup = ageGroup;
    }

    public Ticket discount(Set<Discount> discount) {
        this.discounts = discount;
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

    public void setDiscounts(Set<Discount> discounts) {
        if (this.discounts != null){
            for (Discount discount : this.discounts) {
                discount.setTickets(null);
            }
        }
        if (discounts != null){
            for (Discount discount : discounts){
                discount.addTicket(this);
            }
        }
        this.discounts = discounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.setTicket(this);
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
        startStation.addTicket(this);
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
        endStation.addTicket(this);
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
        train.addTicket(this);
    }

    public Economy getEconomy() {
        return economy;
    }

    public void setEconomy(Economy economy) {
        this.economy = economy;
        economy.addTicket(this);
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
        ageGroup.addTicket(this);
    }

    public Set<Discount> getDiscounts() {
        return discounts;
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

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public void removeDiscount(Discount discount) {
        discounts.remove(discount);
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
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", train=" + train +
                ", economy=" + economy +
                ", ageGroup=" + ageGroup +
                ", discount=" + discounts +
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
                Objects.equals(startStation, ticket.startStation) &&
                Objects.equals(endStation, ticket.endStation) &&
                Objects.equals(train, ticket.train) &&
                Objects.equals(economy, ticket.economy) &&
                Objects.equals(ageGroup, ticket.ageGroup) &&
                Objects.equals(discounts, ticket.discounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departDateBooking, returnDateBooking, registrationDateTicket,
                returnDateTicket, price, user, startStation, endStation, train, economy, ageGroup, discounts);
    }
}
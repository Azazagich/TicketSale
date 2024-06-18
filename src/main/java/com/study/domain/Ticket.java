package com.study.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Represents a ticket for a train journey. A ticket includes details such as
 * booking dates, registration dates, price, and associated entities like user, station,
 * train, economy class, age group, and discounts.
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

    public Ticket price(double price) {
        this.price = price;
        return this;
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

    /**
     * Sets the set of discounts associated with the ticket.
     * If the ticket already has discounts, it disassociates them before setting the new discounts.
     *
     * @param discounts the set of discounts to associate with the ticket
     * */
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

    /**
     * Sets the user associated with the ticket.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
        user.setTicket(this);
    }

    public Station getStartStation() {
        return startStation;
    }

    /**
     * Sets the starting station of the journey.
     *
     * @param startStation the starting station to set
     */
    public void setStartStation(Station startStation) {
        this.startStation = startStation;
        startStation.addTicket(this);
    }

    public Station getEndStation() {
        return endStation;
    }

    /**
     * Sets the ending station of the journey.
     *
     * @param endStation the ending station to set
     */
    public void setEndStation(Station endStation) {
        this.endStation = endStation;
        endStation.addTicket(this);
    }

    public Train getTrain() {
        return train;
    }

    /**
     * Sets the train associated with the ticket.
     *
     * @param train the train to set
     */
    public void setTrain(Train train) {
        this.train = train;
        train.addTicket(this);
    }

    public Economy getEconomy() {
        return economy;
    }

    /**
     * Sets the economy class associated with the ticket.
     *
     * @param economy the economy class to set
     */
    public void setEconomy(Economy economy) {
        this.economy = economy;
        economy.addTicket(this);
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
     * Sets the age group associated with the ticket.
     *
     * @param ageGroup the age group to set
     */
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

    public Ticket id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDepartDateBooking() {
        return departDateBooking;
    }

    public Ticket departDateBooking(LocalDate departDateBooking) {
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

    public Ticket registrationDateTicket(LocalDate registrationDateTicket) {
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
        if (this == o){
            return true;
        }
        if (!(o instanceof Ticket)){
            return false;
        }
        return id == ((Ticket)o).id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
package com.study.repository;

import com.study.domain.Station;
import com.study.domain.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TicketRepository implements CrudRepository<Ticket>{

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, Ticket> tickets = new HashMap<>();


    @Override
    public Ticket save(Ticket ticket) {
        if (ticket != null) {
            ticket.setId(++id);
            tickets.put(id, ticket);
        }
        return ticket;
    }


    @Override
    public List<Ticket> saveAll(List<Ticket> tickets) {
        for (Ticket ticket : tickets){
            if (ticket != null) {
                ticket.setId(++id);
                this.tickets.put(id, ticket);
            }
        }
        return tickets;
    }


    @Override
    public Optional<Ticket> findById(Integer id){
        return Optional.of(tickets.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && tickets.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, Ticket nwTicket){
        if (nwTicket != null){
            nwTicket.setId(id);
            tickets.put(id, nwTicket);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id){
        if (id != null){
            tickets.remove(id);
        }
    }


    @Override
    public void delete(Ticket ticket){
        if (ticket != null){
            deleteById(ticket.getId());
        }
    }


    @Override
    public void deleteAll(){
        tickets.clear();
    }


    @Override
    public void deleteAll(List<Ticket> tickets) {
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    deleteById(ticket.getId());
                }
            }
        }
    }

}

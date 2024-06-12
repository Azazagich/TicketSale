package com.study.repository;

import com.study.domain.Station;
import com.study.domain.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation for managing Ticket entities.
 * */
public class TicketRepository implements CrudRepository<Ticket>{

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for Ticket entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for Ticket entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, Ticket> tickets = new HashMap<>();

    /**
     * Saves a single Ticket entity.
     * @param ticket The Ticket entity to be saved.
     * @return The saved Ticket entity.
     * */
    @Override
    public Ticket save(Ticket ticket) {
        if (ticket != null) {
            ticket.setId(++id);
            tickets.put(id, ticket);
            LOGGER.debug("Saved Ticket with id {}", id);
        }
        return ticket;
    }

    /**
     * Saves a list of Ticket entities.
     * @param tickets The list of Ticket entities to be saved.
     * @return The list of saved Ticket entities.
     * */
    @Override
    public List<Ticket> saveAll(List<Ticket> tickets) {
        for (Ticket ticket : tickets){
            if (ticket != null) {
                ticket.setId(++id);
                this.tickets.put(id, ticket);
                LOGGER.debug("Saved Ticket with id {}", id);
            }
        }
        return tickets;
    }

    /**
     * Retrieves a Ticket entity by its identifier.
     * @param id The identifier of the Ticket entity to be retrieved.
     * @return An optional containing the retrieved Ticket entity, or empty if not found.
     * */
    @Override
    public Optional<Ticket> findById(Integer id){
        LOGGER.debug("Finding Ticket with id {}", id);
        return Optional.of(tickets.get(id));
    }

    /**
     * Checks if a Ticket entity with the given identifier exists.
     * @param id The identifier of the Ticket entity to check.
     * @return true if the Ticket entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && tickets.containsKey(id);
        LOGGER.debug("Existence check for Ticket with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of a Ticket entity.
     * @param id The current identifier of the Ticket entity.
     * @param nwTicket The Ticket entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, Ticket nwTicket){
        if (nwTicket != null && id != null){
            tickets.remove(id);
            tickets.put(nwTicket.getId(), nwTicket);
            LOGGER.debug("Updated Ticket with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update Ticket with id {}", id);
        return false;
    }

    /**
     * Deletes a Ticket entity by its identifier.
     * @param id The identifier of the Ticket entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            tickets.remove(id);
            LOGGER.debug("Deleted Ticket with id {}", id);
        }
    }

    /**
     * Deletes a single Ticket entity.
     * @param ticket The Ticket entity to be deleted.
     * */
    @Override
    public void delete(Ticket ticket){
        if (ticket != null){
            deleteById(ticket.getId());
            LOGGER.debug("Deleted Ticket: {}", ticket);
        }
    }

    /**
     * Deletes all Ticket entities.
     */
    @Override
    public void deleteAll(){
        tickets.clear();
        LOGGER.debug("Deleted all Tickets");

    }

    /**
     * Deletes a list of Ticket entities.
     * @param tickets The list of Ticket entities to be deleted.
     * */
    @Override
    public void deleteAll(List<Ticket> tickets) {
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    deleteById(ticket.getId());
                    LOGGER.debug("Deleted Ticket with id {}", ticket.getId());
                }
            }
        }
    }

}

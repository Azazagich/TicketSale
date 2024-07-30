package com.study.service;

import com.study.repository.TicketRepository;
import com.study.service.dto.TicketDTO;
import com.study.service.mapper.TicketMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing {@link TicketDTO} entities.
 * Implements {@link CrudService} to provide CRUD operations for TicketDTO objects.
 */
public class TicketService implements CrudService<TicketDTO>{

    /**
     * Repository for performing CRUD operations on Ticket entities.
     */
    private final TicketRepository ticketRepository;

    /**
     * Mapper for converting between Ticket entities and TicketDTOs.
     */
    private final TicketMapper ticketMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public TicketService(){
        this(new TicketRepository(), new TicketMapper());
    }

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    /**
     * Saves a TicketDTO entity.
     *
     * @param ticketDTO The TicketDTO object to save.
     * @return The saved TicketDTO object.
     */
    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        LOGGER.debug("Saving TicketDTO: {}", ticketDTO);
        return ticketMapper.toDTO(ticketRepository.save(ticketMapper.toEntity(ticketDTO)));
    }

    /**
     * Saves a list of TicketDTO entities.
     *
     * @param ticketsDTO The list of TicketDTO objects to save.
     * @return The list of saved TicketDTO objects.
     */
    @Override
    public List<TicketDTO> saveAll(List<TicketDTO> ticketsDTO) {
        LOGGER.debug("Saving all TicketsDTO");
        return ticketMapper.toDTO(ticketRepository.saveAll(ticketMapper.toEntity(ticketsDTO)));
    }

    /**
     * Finds a TicketDTO entity by its ID.
     *
     * @param id The ID of the TicketDTO entity to find.
     * @return An Optional containing the found TicketDTO object, or empty if not found.
     */
    @Override
    public Optional<TicketDTO> findById(Integer id) {
        LOGGER.debug("Find ticketDTO by id {}", id);
        return ticketMapper.toDTO(ticketRepository.findById(id));
    }

    /**
     * Retrieves all TicketDTO entities.
     *
     * @return A list of all TicketDTO objects.
     */
    @Override
    public List<TicketDTO> findAll() {
        LOGGER.debug("Find All TicketsDTO elements");
        return ticketMapper.toDTO(ticketRepository.findAll());
    }

    /**
     * Checks if a TicketDTO entity exists by its ID.
     *
     * @param id The ID of the TicketDTO entity to check.
     * @return true if the TicketDTO exists, false otherwise.
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of Ticket by ID: {}", id);
        return ticketRepository.existById(id);
    }

    /**
     * Updates a TicketDTO entity with a new DTO object.
     *
     * @param id           The ID of the TicketDTO entity to update.
     * @param nwTicketDTO The new TicketDTO object to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateId(Integer id, TicketDTO nwTicketDTO) {
        LOGGER.debug("Updating Ticket with ID: {}", id);
        if (id != null && nwTicketDTO != null){
            return ticketRepository.updateId(id, ticketMapper.toEntity(nwTicketDTO));
        }
        return false;
    }

    /**
     * Deletes a TicketDTO entity by its ID.
     *
     * @param id The ID of the TicketDTO entity to delete.
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting Ticket by ID: {}", id);
        ticketRepository.deleteById(id);
    }

    /**
     * Deletes a TicketDTO entity.
     *
     * @param ticketDTO The TicketDTO object to delete.
     */
    @Override
    public void delete(TicketDTO ticketDTO) {
        LOGGER.debug("DeletingTicket: {}", ticketDTO);
        ticketRepository.delete(ticketMapper.toEntity(ticketDTO));
    }

    /**
     * Deletes all TicketDTO entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Tickets");
        ticketRepository.deleteAll();
    }

    /**
     * Deletes a list of TicketDTO entities.
     *
     * @param ticketsDTO The list of TicketDTO objects to delete.
     */
    @Override
    public void deleteAll(List<TicketDTO> ticketsDTO) {
        LOGGER.debug("Deleting all Tickets list");
        ticketRepository.deleteAll(ticketMapper.toEntity(ticketsDTO));
    }
}

package com.study.service.mapper;

import com.study.domain.Station;
import com.study.domain.Ticket;
import com.study.domain.User;
import com.study.service.dto.StationDTO;
import com.study.service.dto.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TicketMapper implements Mapper<TicketDTO, Ticket>{

    /**
     * Mapper class for converting between Ticket and TicketDTO objects.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts a Ticket object to a TicketDTO object.
     * Logs the conversion if the ticket is not null.
     *
     * @param ticket the Ticket object to be converted.
     * @return the converted TicketDTO object, or a new TicketDTO if the input is null.
     */
    @Override
    public TicketDTO toDTO(Ticket ticket) {
        if (ticket != null) {
             LOGGER.debug("Converted from Ticket to TicketDTO: {}", ticket);
             TicketDTO ticketDTO = new TicketDTO();
             ticketDTO.setId(ticket.getId());
             ticketDTO.setDepartDateBooking(ticket.getDepartDateBooking());
             ticketDTO.setReturnDateBooking(ticket.getReturnDateBooking());
             ticketDTO.setRegistrationDateTicket(ticket.getRegistrationDateTicket());
             ticketDTO.setReturnDateTicket(ticket.getReturnDateTicket());
             ticketDTO.setPrice(ticket.getPrice());
             ticketDTO.setUser(new UserMapper().toDTO(ticket.getUser()));
             ticketDTO.setStartStation(new StationMapper().toDTO(ticket.getStartStation()));
             ticketDTO.setEndStation(new StationMapper().toDTO(ticket.getEndStation()));
             ticketDTO.setTrain(new TrainMapper().toDTO(ticket.getTrain()));
             ticketDTO.setEconomy(new EconomyMapper().toDTO(ticket.getEconomy()));
             ticketDTO.setAgeGroup(new AgeGroupMapper().toDTO(ticket.getAgeGroup()));
             ticketDTO.setDiscounts(new DiscountMapper().toDTO(ticket.getDiscounts()));
             return ticketDTO;
        }
        return new TicketDTO();
    }

    /**
     * Converts an Optional<Ticket> object to an Optional<TicketDTO> object.
     * Logs the conversion if the ticket is present.
     *
     * @param ticket the Optional<Ticket> object to be converted.
     * @return the converted Optional<TicketDTO> object, or Optional.empty() if the input is empty.
     */
    @Override
    public Optional<TicketDTO> toDTO(Optional<Ticket> ticket) {
        if (ticket.isPresent()){
            LOGGER.debug("Converted from Optional<Ticket> to Optional<TicketDTO>: {}", ticket);
            return Optional.of(toDTO(ticket.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of Ticket objects to a list of TicketDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param tickets the list of Ticket objects to be converted.
     * @return the converted list of TicketDTO objects, or an empty list if the input list is empty.
     */
    @Override
    public List<TicketDTO> toDTO(List<Ticket> tickets) {
        if (!tickets.isEmpty()){
            LOGGER.debug("Converting list of Tickets to list of TicketsDTO");
            return tickets.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts a TicketDTO object to a Ticket object.
     * Logs the conversion if the ticketDTO is not null.
     *
     * @param ticketDTO the TicketDTO object to be converted.
     * @return the converted Ticket object, or a new Ticket if the input is null.
     */
    @Override
    public Ticket toEntity(TicketDTO ticketDTO) {
        if (ticketDTO != null) {
            LOGGER.debug("Converted from TicketDTO to Ticket: {}", ticketDTO);
            Ticket ticket = new Ticket();
            ticket.setId(ticketDTO.getId());
            ticket.setDepartDateBooking(ticketDTO.getDepartDateBooking());
            ticket.setReturnDateBooking(ticketDTO.getReturnDateBooking());
            ticket.setRegistrationDateTicket(ticketDTO.getRegistrationDateTicket());
            ticket.setReturnDateTicket(ticketDTO.getReturnDateTicket());
            ticket.setPrice(ticketDTO.getPrice());
            ticket.setUser(new UserMapper().toEntity(ticketDTO.getUser()));
            ticket.setStartStation(new StationMapper().toEntity(ticketDTO.getStartStation()));
            ticket.setEndStation(new StationMapper().toEntity(ticketDTO.getEndStation()));
            ticket.setTrain(new TrainMapper().toEntity(ticketDTO.getTrain()));
            ticket.setEconomy(new EconomyMapper().toEntity(ticketDTO.getEconomy()));
            ticket.setAgeGroup(new AgeGroupMapper().toEntity(ticketDTO.getAgeGroup()));
            ticket.setDiscounts(new DiscountMapper().toEntity(ticketDTO.getDiscounts()));
            return ticket;
        }
        return new Ticket();
    }

    /**
     * Converts a list of TicketDTO objects to a list of Ticket objects.
     * Logs the conversion if the list is not empty.
     *
     * @param ticketsDTO the list of TicketDTO objects to be converted.
     * @return the converted list of Ticket objects, or an empty list if the input list is empty.
     */
    @Override
    public List<Ticket> toEntity(List<TicketDTO> ticketsDTO) {
        if (!ticketsDTO.isEmpty()){
            LOGGER.debug("Converting list of TicketDTOs to list of Tickets");
            return ticketsDTO.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .toList();
        }
        return List.of();
    }
}

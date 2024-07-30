package com.study.service.mapper;

import com.study.domain.*;
import com.study.service.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

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
             ticketDTO.setUser(toUserDTO(ticket.getUser()));
             ticketDTO.setStartStation(toStationDTO(ticket.getStartStation()));
             ticketDTO.setEndStation(toStationDTO(ticket.getEndStation()));
             ticketDTO.setTrain(toTrainDTO(ticket.getTrain()));
             ticketDTO.setEconomy(toEconomyDTO(ticket.getEconomy()));
             ticketDTO.setAgeGroup(toAgeGroupDTO(ticket.getAgeGroup()));
             ticketDTO.setDiscounts(toDiscountsDTO(ticket.getDiscounts()));
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
            ticket.setUser(toUserEntity(ticketDTO.getUser()));
            ticket.setStartStation(toStationEntity(ticketDTO.getStartStation()));
            ticket.setEndStation(toStationEntity(ticketDTO.getEndStation()));
            ticket.setTrain(toTrainEntity(ticketDTO.getTrain()));
            ticket.setEconomy(toEconomyEntity(ticketDTO.getEconomy()));
            ticket.setAgeGroup(toAgeGroupEntity(ticketDTO.getAgeGroup()));
            ticket.setDiscounts(toDiscountEntity((ticketDTO.getDiscounts())));
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

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO, or an empty UserDTO if the input is null
     */
    protected UserDTO toUserDTO(User user) {
        if (user != null) {
            LOGGER.debug("Converted from User to UserDTO: {}", user);
            return new UserDTO()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .dateOfBirth(user.getDateOfBirth());
        }
        return new UserDTO();
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the corresponding User entity, or an empty User if the input is null
     */
    protected User toUserEntity(UserDTO userDTO){
        if (userDTO != null) {
            LOGGER.debug("Converted from UserDTO to User: {}", userDTO);
            return new User()
                    .id(userDTO.getId())
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .dateOfBirth(userDTO.getDateOfBirth());
        }
        return new User();
    }

    /**
     * Converts a Train entity to a TrainDTO.
     *
     * @param train the Train entity to convert
     * @return the corresponding TrainDTO, or an empty TrainDTO if the input is null
     */
    protected TrainDTO toTrainDTO(Train train) {
        if (train != null) {
            LOGGER.debug("Converted from Train to TrainDTO: {}", train);
            return new TrainDTO()
                    .id(train.getId())
                    .trainModel(train.getTrainModel());
        }
        return new TrainDTO();
    }

    /**
     * Converts a TrainDTO to a Train entity.
     *
     * @param trainDTO the TrainDTO to convert
     * @return the corresponding Train entity, or an empty Train if the input is null
     */
    protected Train toTrainEntity(TrainDTO trainDTO) {
        LOGGER.debug("Converted from TrainDTO to Train: {}", trainDTO);
        if (trainDTO != null){
            return new Train()
                    .id(trainDTO.getId())
                    .trainModel(trainDTO.getTrainModel());
        }
        return new Train();
    }

    /**
     * Converts a Station entity to a StationDTO.
     *
     * @param station the Station entity to convert
     * @return the corresponding StationDTO, or an empty StationDTO if the input is null
     */
    protected StationDTO toStationDTO(Station station) {
        if (station != null) {
            LOGGER.debug("Converted from Station to StationDTO: {}", station);
            return new StationDTO()
                    .id(station.getId())
                    .nameOfStation(station.getNameOfStation())
                    .addressLocation(station.getAddressLocation());
        }
        return new StationDTO();
    }

    /**
     * Converts a StationDTO to a Station entity.
     *
     * @param stationDTO the StationDTO to convert
     * @return the corresponding Station entity, or an empty Station if the input is null
     */
    protected Station toStationEntity(StationDTO stationDTO) {
        LOGGER.debug("Converted from StationDTO to Station: {}", stationDTO);
        if (stationDTO != null){
            return new Station()
                    .id(stationDTO.getId())
                    .nameOfStation(stationDTO.getNameOfStation()).
                    addressLocation(stationDTO.getAddressLocation());
        }
        return new Station();
    }

    /**
     * Converts an Economy entity to an EconomyDTO.
     *
     * @param economy the Economy entity to convert
     * @return the corresponding EconomyDTO, or an empty EconomyDTO if the input is null
     */
    protected EconomyDTO toEconomyDTO(Economy economy) {
        if (economy != null) {
            LOGGER.debug("Converted from Economy to EconomyDTO: {}", economy);
            return new EconomyDTO()
                    .id(economy.getId())
                    .type(economy.getType());
        }
        return new EconomyDTO();
    }

    /**
     * Converts an EconomyDTO to an Economy entity.
     *
     * @param economyDTO the EconomyDTO to convert
     * @return the corresponding Economy entity, or an empty Economy if the input is null
     */
    protected Economy toEconomyEntity(EconomyDTO economyDTO) {
        LOGGER.debug("Converted from EconomyDTO to Economy: {}", economyDTO);
        if (economyDTO != null){
            return new Economy()
                    .id(economyDTO.getId())
                    .type(economyDTO.getType());
        }
        return new Economy();
    }

    /**
     * Converts an AgeGroup entity to an AgeGroupDTO.
     *
     * @param ageGroup the AgeGroup entity to convert
     * @return the corresponding AgeGroupDTO, or an empty AgeGroupDTO if the input is null
     */
    protected AgeGroupDTO toAgeGroupDTO(AgeGroup ageGroup) {
        if (ageGroup != null) {
            LOGGER.debug("Converted from ageGroup to AgeGroupDTO: {}", ageGroup);
            return new AgeGroupDTO()
                    .id(ageGroup.getId())
                    .type(ageGroup.getType());
        }
        return new AgeGroupDTO();
    }

    /**
     * Converts an AgeGroupDTO to an AgeGroup entity.
     *
     * @param ageGroupDTO the AgeGroupDTO to convert
     * @return the corresponding AgeGroup entity, or an empty AgeGroup if the input is null
     */
    protected AgeGroup toAgeGroupEntity(AgeGroupDTO ageGroupDTO) {
        LOGGER.debug("Converted from AgeGroupDTO to AgeGroup: {}", ageGroupDTO);
        if (ageGroupDTO != null){
            return new AgeGroup()
                    .id(ageGroupDTO.getId())
                    .type(ageGroupDTO.getType());
        }
        return new AgeGroup();
    }

    /**
     * Converts a Discount entity to a DiscountDTO.
     *
     * @param discount the Discount entity to convert
     * @return the corresponding DiscountDTO, or an empty DiscountDTO if the input is null
     */
    protected DiscountDTO toDiscountDTO(Discount discount) {
        if (discount != null) {
            LOGGER.debug("Converted from Discount to DiscountDTO: {}", discount);
            return new DiscountDTO()
                    .id(discount.getId())
                    .type(discount.getType())
                    .percent(discount.getPercent());
        }
        return new DiscountDTO();
    }

    /**
     * Converts a set of Discount entities to a set of DiscountDTOs.
     *
     * @param discounts the set of Discount entities to convert
     * @return the corresponding set of DiscountDTOs, or an empty set if the input is null or empty
     */
    protected Set<DiscountDTO> toDiscountsDTO(Set<Discount> discounts) {
        if (!discounts.isEmpty()){
            LOGGER.debug("Converting list of Discounts to list of DiscountsDTO");
            return discounts.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDiscountDTO)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    /**
     * Converts a DiscountDTO to a Discount entity.
     *
     * @param discountDTO the DiscountDTO to convert
     * @return the corresponding Discount entity, or an empty Discount if the input is null
     */
    protected Discount toDiscountEntity(DiscountDTO discountDTO) {
        LOGGER.debug("Converted from DiscountDTO to Discount: {}", discountDTO);
        if (discountDTO != null){
            return new Discount()
                    .id(discountDTO.getId())
                    .type(discountDTO.getType())
                    .percent(discountDTO.getPercent());
        }
        return new Discount();
    }

    /**
     * Converts a set of DiscountDTOs to a set of Discount entities.
     *
     * @param discounts the set of DiscountDTOs to convert
     * @return the corresponding set of Discount entities, or an empty set if the input is null or empty
     */
    protected Set<Discount> toDiscountEntity(Set<DiscountDTO> discounts) {
        if (!discounts.isEmpty()){
            LOGGER.debug("Converting set of Discounts to set of DiscountsDTO");
            return discounts.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDiscountEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}

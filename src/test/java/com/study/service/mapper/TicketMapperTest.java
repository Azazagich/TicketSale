package com.study.service.mapper;

import com.study.domain.Economy;
import com.study.domain.Ticket;
import com.study.service.dto.EconomyDTO;
import com.study.service.dto.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link TicketMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class TicketMapperTest {

    private static final double ADULT_TICKET_PRICE = 250.5;
    private static final double CHILD_TICKET_PRICE = 50.0;

    private static final String ECONOMY_CLASS_STANDARD = "Стандарт";
    private static final String ECONOMY_CLASS_ECONOMY = "Економ";

    private static final int ID_3 = 3;

    private TicketMapper ticketMapper;

    private TicketDTO ticketDTO;
    private Ticket ticket;

    private EconomyDTO economyDTO;
    private Economy economy;

    private TicketDTO createDTO(){
        return new TicketDTO();
    }

    private TicketDTO createDTO(double price){
        return new TicketDTO().price(price);
    }

    private TicketDTO createDTO(int id, double price){
        return new TicketDTO().id(id).price(price);
    }

    private Ticket createEntity(){
        return new Ticket();
    }

    private Ticket createEntity(double price){
        return new Ticket().price(price);
    }

    private Ticket createEntity(int id, double price){
        return new Ticket().id(id).price(price);
    }

    private Economy createEconomyEntity(String type){
        return new Economy().type(type);
    }

    private EconomyDTO createEconomyDTO(String type){
        return new EconomyDTO().type(type);
    }

    @BeforeEach
    void setUp() {
        ticketMapper = new TicketMapper();

        economyDTO = new EconomyDTO().type(ECONOMY_CLASS_ECONOMY);
        economy = createEconomyEntity(ECONOMY_CLASS_STANDARD);

        ticketDTO = new TicketDTO().price(ADULT_TICKET_PRICE);
        ticketDTO.setEconomy(economyDTO);

        ticket = new Ticket().price(ADULT_TICKET_PRICE);
        ticket.setEconomy(economy);
    }

    @Test
    void testToDTO() {
        assertEquals(ticketMapper.toDTO(ticket), createDTO(CHILD_TICKET_PRICE));
        assertEquals(ticketMapper.toDTO(createEntity()), createDTO());
    }

    @Test
    void testToDTOs() {
        Ticket ticket2 = createEntity(ID_3, ADULT_TICKET_PRICE);
        List<Ticket> tickets = List.of(ticket, ticket2);
        EconomyDTO economy = createEconomyDTO(ECONOMY_CLASS_ECONOMY);
        TicketDTO ticketDTO1 = createDTO(ID_3, ADULT_TICKET_PRICE);
        ticketDTO1.setEconomy(economy);
        List<TicketDTO> expectedDTOs = List.of(ticketDTO, ticketDTO1);
        assertIterableEquals(ticketMapper.toDTO(tickets), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Ticket> ticket2 = Optional.of(createEntity(ADULT_TICKET_PRICE));
        ticket2.get().setEconomy(economy);
        Optional<TicketDTO> ticketDTO2 = Optional.of(createDTO(ADULT_TICKET_PRICE));
        ticketDTO2.get().setEconomy(createEconomyDTO(ECONOMY_CLASS_STANDARD));
        assertEquals(ticketMapper.toDTO(ticket2),ticketDTO2);
    }

    @Test
    void testToEntity() {
        assertEquals(ticketMapper.toEntity(ticketDTO), createEntity(ADULT_TICKET_PRICE));
    }

    @Test
    void testToEntities() {
        TicketDTO ticket2DTO = createDTO(ID_3, ADULT_TICKET_PRICE);
        List<TicketDTO> ticketsDTO = List.of(ticketDTO, ticket2DTO);
        List<Ticket> expectedEntities = List.of(createEntity(CHILD_TICKET_PRICE),
                createEntity(ID_3, CHILD_TICKET_PRICE));
        assertIterableEquals(ticketMapper.toEntity(ticketsDTO), expectedEntities);
    }
}

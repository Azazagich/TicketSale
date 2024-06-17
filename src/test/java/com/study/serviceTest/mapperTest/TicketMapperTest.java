package com.study.serviceTest.mapperTest;

import com.study.domain.Economy;
import com.study.domain.Ticket;
import com.study.domain.Train;
import com.study.service.dto.EconomyDTO;
import com.study.service.dto.TicketDTO;
import com.study.service.dto.TrainDTO;
import com.study.service.mapper.TicketMapper;
import com.study.service.mapper.TrainMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TicketMapperTest {
    private TicketMapper ticketMapper;

    private TicketDTO ticketDTO;
    private Ticket ticket;

    private EconomyDTO economy1;
    private Economy economy2;
    @BeforeEach
    void setUp() {
        ticketMapper = new TicketMapper();

        economy1 = new EconomyDTO().type("Економ");
        economy2 = new Economy().type("Стандарт");
        ticketDTO = new TicketDTO().price(350);
        ticketDTO.setEconomy(economy1);

        ticket = new Ticket().price(350);
        ticket.setEconomy(economy2);
    }

    @Test
    void testToDTO() {
        assertEquals(ticketMapper.toDTO(ticket), new TicketDTO().price(550));
        assertEquals(ticketMapper.toDTO(new Ticket()), new TicketDTO());
        assertInstanceOf(TicketDTO.class, ticketMapper.toDTO(new Ticket()));
    }

    @Test
    void testToDTOs() {
        Ticket ticket2 = new Ticket().id(3).price(350);
        List<Ticket> tickets = List.of(ticket, ticket2);
        EconomyDTO economy = new EconomyDTO().type("Стандарт");
        TicketDTO ticketDTO1 = new TicketDTO().id(3).price(350);
        ticketDTO1.setEconomy(economy);
        List<TicketDTO> expectedDTOs = List.of(ticketDTO, ticketDTO1);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Ticket> ticket2 = Optional.of(new Ticket().price(350));
        ticket2.get().setEconomy(economy2);
        Optional<TicketDTO> ticketDTO2 = Optional.of(new TicketDTO().price(350));
        ticketDTO2.get().setEconomy(new EconomyDTO().type("Стандарт"));
        assertEquals(ticketMapper.toDTO(ticket2),ticketDTO2);
    }

    @Test
    void testToEntity() {
        assertEquals(ticketMapper.toEntity(ticketDTO), new Ticket().price(350));
        assertInstanceOf(TicketDTO.class, ticketMapper.toEntity(new TicketDTO()));
    }

//    @Test
//    void testToEntities() {
//        TrainDTO train2DTO = new TrainDTO().id(3).amountOfSeats(50);
//        List<TrainDTO> trainsDTO = List.of(trainDTO, train2DTO);
//        List<Train> expectedEntities = List.of(new Train().amountOfSeats(50), new Train().id(3).amountOfSeats(50));
//        assertEquals(trainMapper.toEntity(trainsDTO), expectedEntities);
//    }
}

package com.study.serviceTest;

import com.study.repository.TicketRepository;
import com.study.service.TicketService;
import com.study.service.dto.TicketDTO;
import com.study.service.mapper.TicketMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link TicketService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link TicketDTO} entities using the service layer.
 */
public class TicketServiceTest {

    private final double ADULT_TICKET_PRICE = 250.5;
    private final double CHILD_TICKET_PRICE = 50.0;
    private final double OLD_TICKET_PRICE = 150.0;
    private final double CELEBRATION_TICKET_PRICE = 150.0;
    
    private final int FIRST_ELEMENT_TRAIN = 0;
    private final int SECOND_ELEMENT_TRAIN = 1;
    private final int PRIMARY_LIST_TRAIN_SIZE = 3;

    private final int EXPECTED_SIZE_ADDITION = 1;
    private final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private TicketDTO ticketDTO1;
    private TicketDTO ticketDTO2;
    private TicketDTO ticketDTO3;
    
    private TicketService ticketService;
    private TicketMapper ticketMapper;
    private TicketRepository ticketRepository;

    private TicketDTO createDTO(double price) {
        return new TicketDTO().price(price);
    }

    @BeforeEach
    void setUp() {
        ticketRepository = new TicketRepository();
        ticketMapper = new TicketMapper();

        ticketService = new TicketService(ticketRepository, ticketMapper);

        ticketDTO1 = ticketService.save(createDTO(ADULT_TICKET_PRICE));
        ticketDTO2 = ticketService.save(createDTO(CHILD_TICKET_PRICE));
        ticketDTO3 = ticketService.save(createDTO(OLD_TICKET_PRICE));
    }

    @AfterEach
    void tearDown() {
        ticketService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = ticketService.findAll().size();
        TicketDTO saved = ticketService.save(createDTO(CELEBRATION_TICKET_PRICE));

        assertTrue(ticketService.existById(saved.getId()));

        TicketDTO saved2 = ticketService.findById(saved.getId()).orElseThrow();

        assertEquals(CELEBRATION_TICKET_PRICE, saved.getPrice());
        assertEquals(saved2.getPrice(), saved.getPrice());
        assertEquals( sizeBeforeSave + EXPECTED_SIZE_ADDITION, ticketService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = ticketService.findAll().size();
        List<TicketDTO> ticketsDTO = new ArrayList<>();

        TicketDTO ticketDTO4 = createDTO(CELEBRATION_TICKET_PRICE);
        TicketDTO ticketDTO5 = createDTO(OLD_TICKET_PRICE);

        ticketsDTO.add(ticketDTO4);
        ticketsDTO.add(ticketDTO5);

        List<TicketDTO> ticketDTOS = ticketService.saveAll(ticketsDTO);

        assertTrue(ticketService.existById(ticketDTOS.get(FIRST_ELEMENT_TRAIN).getId()));
        assertTrue(ticketService.existById(ticketDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(ticketDTO4.getPrice(), ticketDTOS.get(FIRST_ELEMENT_TRAIN).getPrice());
        assertEquals(ticketDTO5.getPrice(), ticketDTOS.get(SECOND_ELEMENT_TRAIN).getPrice());
        assertEquals( sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, ticketService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(ticketService.existById(ticketDTO1.getId()));
        TicketDTO saved = ticketService.findById(ticketDTO1.getId()).orElseThrow();
        assertEquals(ticketDTO1.getPrice(), saved.getPrice());
    }

    @Test
    void findAll() {
        assertEquals(PRIMARY_LIST_TRAIN_SIZE, ticketService.findAll().size());
        assertTrue(ticketService.existById(ticketDTO2.getId()));
        assertTrue(ticketService.existById(ticketDTO3.getId()));
        assertNotNull(ticketService.findAll());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = ticketService.findAll().size();
        TicketDTO ticketDTO4 = createDTO(CELEBRATION_TICKET_PRICE);

        assertTrue(ticketService.updateId(ticketDTO2.getId(), ticketDTO4));
        assertTrue(ticketService.existById(ticketDTO4.getId()));
        assertEquals(ticketDTO4.getPrice(), ticketService.findById(ticketDTO4.getId()).get().getPrice());
        assertEquals(sizeBeforeUpdate, ticketService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = ticketService.findAll().size();

        assertTrue(ticketService.existById(ticketDTO1.getId()));
        ticketService.delete(ticketDTO1);
        assertFalse(ticketService.existById(ticketDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, ticketService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = ticketService.findAll().size();
        List<TicketDTO> ticketsDTODelete = List.of(ticketDTO1, ticketDTO2);

        assertTrue(ticketService.existById(ticketDTO1.getId()));
        assertTrue(ticketService.existById(ticketDTO2.getId()));
        assertTrue(ticketService.existById(ticketDTO3.getId()));

        ticketService.deleteAll(ticketsDTODelete);
        assertFalse(ticketService.existById(ticketDTO1.getId()));
        assertFalse(ticketService.existById(ticketDTO2.getId()));
        assertTrue(ticketService.existById(ticketDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, ticketService.findAll().size());
    }
}

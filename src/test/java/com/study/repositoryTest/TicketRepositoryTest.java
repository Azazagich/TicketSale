package com.study.repositoryTest;

import com.study.domain.Ticket;
import com.study.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the {@link TicketRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link Ticket} entities in the repository.
 */
public class TicketRepositoryTest {

    private final double ADULT_TICKET_PRICE = 250.5;
    private final double CHILD_TICKET_PRICE = 50.0;
    private final double OLD_TICKET_PRICE = 150.0;

    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;

    private static TicketRepository ticketRepository;

    private Ticket createEntity(double price) {
        return new Ticket().price(price);
    }

    @BeforeEach
    void setUp() {
        ticketRepository = new TicketRepository();

        // Initialize the repository and the Ticket instances
        ticket1 = createEntity(ADULT_TICKET_PRICE);
        ticket2 = createEntity(CHILD_TICKET_PRICE);
        ticket3 = createEntity(OLD_TICKET_PRICE);

        // Add the Ticket instances to a list and save them in the repository
        List<Ticket> ageGroups = new ArrayList<>();
        ageGroups.add(ticket1);
        ageGroups.add(ticket2);
        ageGroups.add(ticket3);

        ticketRepository.saveAll(ageGroups);
    }

    @AfterEach
    void tearDown() {
        ticketRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedTicket() {
        // Check if the Ticket with ID 1 is correctly found
        assertTrue(ticketRepository.existById(ticket1.getId()));
        assertEquals(ticket1, ticketRepository.findById(ticket1.getId()).get());

        // Check if the Ticket with ID 2 is correctly found and has the correct price
        assertTrue(ticketRepository.existById(ticket2.getId()));
        assertEquals(ticket2.getPrice(), ticketRepository.findById(ticket2.getId()).get().getPrice());

        // Check if the Ticket with ID 2 is the same object as ticket2
        assertEquals(ticket2, ticketRepository.findById(ticket2.getId()).get());

        // Ensure that Ticket with ID 3 is not the same as ticket2
        assertTrue(ticketRepository.existById(ticket3.getId()));
        assertNotEquals(ticket3, ticketRepository.findById(ticket2.getId()).get());

        // Ensure that Ticket with ID 1 is not the same as ticket3
        assertNotEquals(ticket3, ticketRepository.findById(ticket1.getId()).get());
    }

    @Test
    void findAll_thenReturnAllEntities(){
        assertEquals(ticketRepository.findAll(), List.of(ticket1, ticket2, ticket3));
    }

    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult() {
        // Check if existence check with null ID returns false
        assertFalse(ticketRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(ticketRepository.existById(ticket1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(ticketRepository.existById(ticket2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(ticketRepository.existById(ticket3.getId()));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult() {
        // Update the Ticket with ID 2 and check if the update was successful
        ticket1 = new Ticket().price(OLD_TICKET_PRICE).id(ticket2.getId());
        assertTrue(ticketRepository.updateId(ticket2.getId(), ticket1));

        // Ensure that updating with a null Ticket returns false
        assertFalse(ticketRepository.updateId(ticket1.getId(), null));

        // Ensure that updating with null ID and null Ticket returns false
        assertFalse(ticketRepository.updateId(null, null));

        // Ensure that updating with null ID and a valid Ticket returns false
        assertFalse(ticketRepository.updateId(null, ticket1));
    }

    @Test
    public void givenId_thenDeleteElInMap() {
        // Delete the element with ID 2 and verify that it no longer exists
        assertTrue(ticketRepository.existById(ticket2.getId()));
        ticketRepository.deleteById(ticket2.getId());

        // Check that the element with ID 2 no longer exists
        assertFalse(ticketRepository.existById(ticket2.getId()));
    }

    @Test
    public void givenTicket_thenDeleteElInMap() {
        // Delete the ticket3 element from the repository
        assertTrue(ticketRepository.existById(ticket3.getId()));
        ticketRepository.delete(ticket3);

        // Verify that the element with ID 3 no longer exists
        assertFalse(ticketRepository.existById(ticket3.getId()));
    }

    @Test
    public void deleteAllElInMap() {
        // Verify that all elements exist before deletion
        assertTrue(ticketRepository.existById(ticket3.getId()));
        assertTrue(ticketRepository.existById(ticket2.getId()));
        assertTrue(ticketRepository.existById(ticket1.getId()));

        // Delete all elements from the repository
        ticketRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(ticketRepository.existById(ticket3.getId()));
        assertFalse(ticketRepository.existById(ticket2.getId()));
        assertFalse(ticketRepository.existById(ticket1.getId()));
    }

    @Test
    public void givenListTicket_thenDeleteListElInMap() {
        // Create a list of elements to delete
        List<Ticket> ticketDelete = new ArrayList<>();
        ticketDelete.add(ticket1);
        ticketDelete.add(ticket2);

        // Delete the list of elements from the repository
        ticketRepository.deleteAll(ticketDelete);

        // Verify that the element with ID 3 still exists
        assertTrue(ticketRepository.existById(ticket3.getId()));

        // Verify that the elements with IDs 1 and 2 no longer exist
        assertFalse(ticketRepository.existById(ticket2.getId()));
        assertFalse(ticketRepository.existById(ticket1.getId()));
    }

    @Test
    public void givenElTicket_whenSaveElInMap_thenReturnTicket() {
        // Create a new Ticket instance
        Ticket ticket4 = createEntity(OLD_TICKET_PRICE);

        // Save the new ticket in the repository
        ticketRepository.save(ticket4);
        ticketRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(ticketRepository.existById(ticket4.getId()));
    }

    @Test
    public void givenListElTicket_whenSaveListElInMap_thenReturnListElTicket() {
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(ticketRepository.existById(ticket1.getId()));
        assertTrue(ticketRepository.existById(ticket2.getId()));

        // Create a new Ticket instance
        Ticket ticket4 = createEntity(OLD_TICKET_PRICE);

        // Verify that the element with ID 4 does not exist before saving
        assertFalse(ticketRepository.existById(ticket4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(ticketRepository.findById(ticket1.getId()).get(), ticket1);
        assertEquals(ticketRepository.findById(ticket2.getId()).get(), ticket2);
    }
}

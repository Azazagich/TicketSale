package com.study.repositoryTest;

import com.study.domain.Discount;
import com.study.repository.DiscountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class contains unit tests for the {@link DiscountRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link Discount} entities in the repository.
 * */
public class DiscountRepositoryTest {

    private final String DISCOUNT_TYPE_SOCIAL = "Social Discount";
    private final String DISCOUNT_TYPE_CHILDREN = "Children Discount";
    private final String DISCOUNT_TYPE_MILITARY = "Military Discount";
    private final double DISCOUNT_SOCIAL_PERCENT = 0.4;
    private final double DISCOUNT_CHILDREN_PERCENT = 0.3;
    private final double DISCOUNT_MILITARY_PERCENT = 0.5;

    private Discount discount1;
    private Discount discount2;
    private Discount discount3;

    private static DiscountRepository discountRepository;

    private Discount createEntity(String type, double percent) {
        return new Discount().type(type).percent(percent);
    }

    @BeforeEach
    void SetUp() {
        discountRepository = new DiscountRepository();

        // Initialize the repository and the discounts
        discount1 = createEntity(DISCOUNT_TYPE_SOCIAL, DISCOUNT_SOCIAL_PERCENT);
        discount2 = createEntity(DISCOUNT_TYPE_CHILDREN, DISCOUNT_CHILDREN_PERCENT);
        discount3 = createEntity(DISCOUNT_TYPE_MILITARY, DISCOUNT_MILITARY_PERCENT);

        // Add the discounts to a list and save them in the repository
        List<Discount> discounts = new ArrayList<>();
        discounts.add(discount1);
        discounts.add(discount2);
        discounts.add(discount3);

        discountRepository.saveAll(discounts);
    }

    @AfterEach
    void tearDown() {
        discountRepository.deleteAll();
    }

    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedDiscount() {
        // Check if the Discount with ID 1 is correctly found
        assertTrue(discountRepository.existById(discount1.getId()));
        assertEquals(discount1, discountRepository.findById(discount1.getId()).get());

        // Check if the Discount with ID 2 is correctly found and has the correct type and percent
        assertTrue(discountRepository.existById(discount2.getId()));
        assertEquals(discount2.getType(), discountRepository.findById(discount2.getId()).get().getType());
        assertEquals(discount2.getPercent(), discountRepository.findById(discount2.getId()).get().getPercent());

        // Check if the Discount with ID 2 is the same object as discount2
        assertEquals(discount2, discountRepository.findById(discount2.getId()).get());

        // Ensure that Discount with ID 3 is not the same as discount2
        assertTrue(discountRepository.existById(discount3.getId()));
        assertNotEquals(discount3, discountRepository.findById(discount2.getId()).get());

        // Ensure that Discount with ID 1 is not the same as discount3
        assertNotEquals(discount3, discountRepository.findById(discount1.getId()).get());
    }

    @Test
    void findAll_thenReturnAllEntities(){
        assertEquals(discountRepository.findAll(), List.of(discount1, discount2, discount3));
    }
    
    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(discountRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(discountRepository.existById(discount1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(discountRepository.existById(discount2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(discountRepository.existById(discount3.getId()));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){
        Discount discountUpdate = new Discount().type(DISCOUNT_TYPE_MILITARY).percent(DISCOUNT_MILITARY_PERCENT).id(discount2.getId());

        // Check if the update operation for ID 2 returns true
        assertTrue(discountRepository.updateId(discount2.getId(), discountUpdate));

        // Check if the update operation with a null object returns false
        assertFalse(discountRepository.updateId(discount1.getId(), null));

        // Check if the update operation with null IDs returns false
        assertFalse(discountRepository.updateId(null,null));
        assertFalse(discountRepository.updateId(null,discountUpdate));
    }

    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        discountRepository.existById(discount2.getId());
        discountRepository.deleteById(discount2.getId());

        // Check that the element with ID 2 no longer exists
        discountRepository.existById(discount2.getId());
        assertFalse(discountRepository.existById(discount2.getId()));
    }

    @Test
    public void givenDiscount_thenDeleteElInMap(){
        // Delete the discount3 element from the repository
        assertTrue(discountRepository.existById(discount3.getId()));
        discountRepository.delete(discount3);

        // Verify that the element with ID 3 no longer exists
        assertFalse(discountRepository.existById(discount3.getId()));
    }

    @Test
    public void deleteAllElInMap() {
        // Verify that all elements exist before deletion
        assertTrue(discountRepository.existById(discount3.getId()));
        assertTrue(discountRepository.existById(discount2.getId()));
        assertTrue(discountRepository.existById(discount1.getId()));

        // Delete all elements from the repository
        discountRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(discountRepository.existById(discount3.getId()));
        assertFalse(discountRepository.existById(discount2.getId()));
        assertFalse(discountRepository.existById(discount1.getId()));
    }

    @Test
    public void givenListDiscount_thenDeleteListElInMap(){
        // Create a list of elements to delete
        List<Discount> discountsDelete = new ArrayList<>();
        discountsDelete.add(discount1);
        discountsDelete.add(discount2);

        // Delete the list of elements from the repository
        discountRepository.deleteAll(discountsDelete);

        // Verify that the element with ID 3 still exists
        assertTrue(discountRepository.existById(discount3.getId()));

        // Verify that the elements with IDs 1 and 2 no longer exist
        assertFalse(discountRepository.existById(discount2.getId()));
        assertFalse(discountRepository.existById(discount1.getId()));
    }

    @Test
    public void givenElDiscount_whenSaveElInMap_thenReturnDiscount(){
        Discount discount4 = createEntity(DISCOUNT_TYPE_CHILDREN, DISCOUNT_CHILDREN_PERCENT);

        discountRepository.save(discount4);
        discountRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(discountRepository.existById(discount4.getId()));
    }

    @Test
    public void givenListElDiscount_whenSaveListElInMap_thenReturnListElDiscount(){
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(discountRepository.existById(discount1.getId()));
        assertTrue(discountRepository.existById(discount2.getId()));

        Discount discount4 = createEntity(DISCOUNT_TYPE_SOCIAL, DISCOUNT_SOCIAL_PERCENT);

        // Verify that the elements with IDs 4 do not exist
        assertFalse(discountRepository.existById(discount4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(discountRepository.findById(discount1.getId()).get(), discount1);
        assertEquals(discountRepository.findById(discount2.getId()).get(), discount2);
    }
}
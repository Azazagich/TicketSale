package com.study.service;

import com.study.repository.DiscountRepository;
import com.study.service.dto.DiscountDTO;
import com.study.service.mapper.DiscountMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link DiscountService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link DiscountDTO} entities using the service layer.
 */
public class DiscountServiсeTest {

    private final String DISCOUNT_TYPE_SOCIAL = "Social Discount";
    private final String DISCOUNT_TYPE_CHILDREN = "Children Discount";
    private final String DISCOUNT_TYPE_MILITARY = "Military Discount";
    private final String DISCOUNT_TYPE_RETIREE = "Retiree Discount";

    private final int FIRST_ELEMENT_DISCOUNT = 0;
    private final int SECOND_ELEMENT_DISCOUNT = 1;
    private final int PRIMARY_LIST_DISCOUNT_SIZE = 3;

    private final int EXPECTED_SIZE_ADDITION = 1;
    private final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private DiscountDTO discountDTO1;
    private DiscountDTO discountDTO2;
    private DiscountDTO discountDTO3;

    private DiscountService discountService;
    private DiscountMapper discountMapper;
    private DiscountRepository discountRepository;

    private DiscountDTO createDTO(String type){
        return new DiscountDTO().type(type);
    }

    @BeforeEach
    void setUp() {
        discountRepository = new DiscountRepository();
        discountMapper = new DiscountMapper();

        discountService = new DiscountService(discountRepository, discountMapper);

        discountDTO1 = discountService.save(createDTO(DISCOUNT_TYPE_SOCIAL));
        discountDTO2 = discountService.save(createDTO(DISCOUNT_TYPE_CHILDREN));
        discountDTO3 = discountService.save(createDTO(DISCOUNT_TYPE_MILITARY));
    }

    @AfterEach
    void tearDown() {
        discountService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = discountService.findAll().size();
        DiscountDTO saved = discountService.save(createDTO(DISCOUNT_TYPE_RETIREE));

        assertTrue(discountService.existById(saved.getId()));

        DiscountDTO saved2 = discountService.findById(saved.getId()).orElseThrow();

        assertEquals(DISCOUNT_TYPE_RETIREE, saved.getType());
        assertEquals(saved2.getType(), saved.getType());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, discountService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = discountService.findAll().size();
        List<DiscountDTO> discountsDTO = new ArrayList<>();

        DiscountDTO discountDTO4 = createDTO(DISCOUNT_TYPE_RETIREE);
        DiscountDTO discountDTO5 = createDTO(DISCOUNT_TYPE_MILITARY);

        discountsDTO.add(discountDTO4);
        discountsDTO.add(discountDTO5);

        List<DiscountDTO> discountDTOS = discountService.saveAll(discountsDTO);

        assertTrue(discountService.existById(discountDTOS.get(FIRST_ELEMENT_DISCOUNT).getId()));
        assertTrue(discountService.existById(discountDTOS.get(SECOND_ELEMENT_DISCOUNT).getId()));
        assertEquals(discountDTO4.getType(), discountDTOS.get(FIRST_ELEMENT_DISCOUNT).getType());
        assertEquals(discountDTO5.getType(), discountDTOS.get(SECOND_ELEMENT_DISCOUNT).getType());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, discountService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(discountService.existById(discountDTO1.getId()));
        DiscountDTO saved = discountService.findById(discountDTO1.getId()).orElseThrow();
        assertEquals(discountDTO1.getType(), saved.getType());
    }

    @Test
    void findAll() {
        assertEquals(PRIMARY_LIST_DISCOUNT_SIZE, discountService.findAll().size());
        assertTrue(discountService.existById(discountDTO1.getId()));
        assertTrue(discountService.existById(discountDTO2.getId()));
        assertTrue(discountService.existById(discountDTO3.getId()));
        assertNotNull(discountService.findAll());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = discountService.findAll().size();
        DiscountDTO discountDTO4 = createDTO(DISCOUNT_TYPE_RETIREE);

        assertTrue(discountService.updateId(discountDTO2.getId(), discountDTO4));
        assertTrue(discountService.existById(discountDTO4.getId()));
        assertEquals(discountDTO4.getType(), discountService.findById(discountDTO4.getId()).get().getType());
        assertEquals(sizeBeforeUpdate, discountService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = discountService.findAll().size();

        assertTrue(discountService.existById(discountDTO1.getId()));
        discountService.delete(discountDTO1);
        assertFalse(discountService.existById(discountDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, discountService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = discountService.findAll().size();
        List<DiscountDTO> discountsDTODelete = List.of(discountDTO1, discountDTO2);

        assertTrue(discountService.existById(discountDTO1.getId()));
        assertTrue(discountService.existById(discountDTO2.getId()));
        assertTrue(discountService.existById(discountDTO3.getId()));

        discountService.deleteAll(discountsDTODelete);
        assertFalse(discountService.existById(discountDTO1.getId()));
        assertFalse(discountService.existById(discountDTO2.getId()));
        assertTrue(discountService.existById(discountDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, discountService.findAll().size());
    }
}

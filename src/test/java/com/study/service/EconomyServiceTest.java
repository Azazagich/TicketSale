package com.study.service;

import com.study.service.dto.EconomyDTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains unit tests for the {@link EconomyService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link EconomyDTO} entities using the service layer.
 */
public class EconomyServiceTest {

    private static final String ECONOMY_CLASS_COMFORT = "Комфорт";
    private static final String ECONOMY_CLASS_STANDARD = "Стандарт";
    private static final String ECONOMY_CLASS_ECONOMY = "Економ";
    private static final String ECONOMY_CLASS_PREMIUM = "Преміум";


    private static final int SECOND_ELEMENT_ECONOMY = 1;
    private static final int PRIMARY_LIST_ECONOMY_SIZE = 3;

    private static final int EXPECTED_SIZE_ADDITION = 1;
    private static final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private EconomyDTO economyDTO1;
    private EconomyDTO economyDTO2;
    private EconomyDTO economyDTO3;

    private EconomyService economyService;

    private EconomyDTO createDTO(String type){
        return new EconomyDTO().type(type);
    }

    @BeforeEach
    void setUp() {

        economyService = new EconomyService();

        economyDTO1 = economyService.save(createDTO(ECONOMY_CLASS_COMFORT));
        economyDTO2 = economyService.save(createDTO(ECONOMY_CLASS_STANDARD));
        economyDTO3 = economyService.save(createDTO(ECONOMY_CLASS_ECONOMY));
    }

    @AfterEach
    void tearDown() {
        economyService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = economyService.findAll().size();
        EconomyDTO expectedDTO = createDTO(ECONOMY_CLASS_PREMIUM);
        EconomyDTO saved = economyService.save(expectedDTO);

        assertTrue(economyService.existById(saved.getId()));

        assertEquals(expectedDTO.getType(), saved.getType());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, economyService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = economyService.findAll().size();
        List<EconomyDTO> economiesDTO = new ArrayList<>();

        EconomyDTO economyDTO4 = createDTO(ECONOMY_CLASS_PREMIUM);
        EconomyDTO economyDTO5 = createDTO(ECONOMY_CLASS_ECONOMY);

        economiesDTO.add(economyDTO4);
        economiesDTO.add(economyDTO5);

        List<EconomyDTO> economyDTOS = economyService.saveAll(economiesDTO);

        assertTrue(economyService.existById(economyDTOS.getFirst().getId()));
        assertTrue(economyService.existById(economyDTOS.get(SECOND_ELEMENT_ECONOMY).getId()));
        assertEquals(economyDTO4.getType(), economyDTOS.getFirst().getType());
        assertEquals(economyDTO5.getType(), economyDTOS.get(SECOND_ELEMENT_ECONOMY).getType());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, economyService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(economyService.existById(economyDTO1.getId()));
        EconomyDTO saved = economyService.findById(economyDTO1.getId()).orElseThrow();
        assertEquals(economyDTO1.getType(), saved.getType());
    }

    @Test
    void findAll() {
        List<EconomyDTO> economyDTOS = economyService.findAll();
        assertEquals(PRIMARY_LIST_ECONOMY_SIZE, economyService.findAll().size());
        assertTrue(economyService.existById(economyDTOS.getFirst().getId()));
        assertTrue(economyService.existById(economyDTOS.get(SECOND_ELEMENT_ECONOMY).getId()));
        assertEquals(economyDTO1.getType(), economyDTOS.getFirst().getType());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = economyService.findAll().size();
        EconomyDTO economyDTO4 = createDTO(ECONOMY_CLASS_PREMIUM);

        assertTrue(economyService.updateId(economyDTO2.getId(), economyDTO4));
        assertTrue(economyService.existById(economyDTO4.getId()));
        assertEquals(economyDTO4.getType(), economyService.findById(economyDTO4.getId()).get().getType());
        assertEquals(sizeBeforeUpdate, economyService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = economyService.findAll().size();

        economyService.delete(economyDTO1);
        assertFalse(economyService.existById(economyDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, economyService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = economyService.findAll().size();
        List<EconomyDTO> economiesDTODelete = List.of(economyDTO1, economyDTO2);

        economyService.deleteAll(economiesDTODelete);
        assertFalse(economyService.existById(economyDTO1.getId()));
        assertFalse(economyService.existById(economyDTO2.getId()));
        assertTrue(economyService.existById(economyDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, economyService.findAll().size());

    }
}

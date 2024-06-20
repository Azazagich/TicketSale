package com.study.service;

import com.study.repository.EconomyRepository;
import com.study.service.dto.EconomyDTO;
import com.study.service.mapper.EconomyMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link EconomyService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link EconomyDTO} entities using the service layer.
 */
public class EconomyServiceTest {

    private final String ECONOMY_CLASS_COMFORT = "Комфорт";
    private final String ECONOMY_CLASS_STANDARD = "Стандарт";
    private final String ECONOMY_CLASS_ECONOMY = "Економ";
    private final String ECONOMY_CLASS_PREMIUM = "Преміум";

    private final int FIRST_ELEMENT_ECONOMY = 0;
    private final int SECOND_ELEMENT_ECONOMY = 1;
    private final int PRIMARY_LIST_ECONOMY_SIZE = 3;

    private final int EXPECTED_SIZE_ADDITION = 1;
    private final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private EconomyDTO economyDTO1;
    private EconomyDTO economyDTO2;
    private EconomyDTO economyDTO3;

    private EconomyService economyService;
    private EconomyMapper economyMapper;
    private EconomyRepository economyRepository;

    private EconomyDTO createDTO(String type){
        return new EconomyDTO().type(type);
    }

    @BeforeEach
    void setUp() {
        economyRepository = new EconomyRepository();
        economyMapper = new EconomyMapper();

        economyService = new EconomyService(economyRepository, economyMapper);

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
        EconomyDTO saved = economyService.save(createDTO(ECONOMY_CLASS_PREMIUM));

        assertTrue(economyService.existById(saved.getId()));

        EconomyDTO saved2 = economyService.findById(saved.getId()).orElseThrow();

        assertEquals(ECONOMY_CLASS_PREMIUM, saved.getType());
        assertEquals(saved2.getType(), saved.getType());
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

        assertTrue(economyService.existById(economyDTOS.get(FIRST_ELEMENT_ECONOMY).getId()));
        assertTrue(economyService.existById(economyDTOS.get(SECOND_ELEMENT_ECONOMY).getId()));
        assertEquals(economyDTO4.getType(), economyDTOS.get(FIRST_ELEMENT_ECONOMY).getType());
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
        assertEquals(PRIMARY_LIST_ECONOMY_SIZE, economyService.findAll().size());
        assertTrue(economyService.existById(economyDTO1.getId()));
        assertTrue(economyService.existById(economyDTO2.getId()));
        assertTrue(economyService.existById(economyDTO3.getId()));
        assertNotNull(economyService.findAll());
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

        assertTrue(economyService.existById(economyDTO1.getId()));
        economyService.delete(economyDTO1);
        assertFalse(economyService.existById(economyDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, economyService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = economyService.findAll().size();
        List<EconomyDTO> economiesDTODelete = List.of(economyDTO1, economyDTO2);

        assertTrue(economyService.existById(economyDTO1.getId()));
        assertTrue(economyService.existById(economyDTO2.getId()));
        assertTrue(economyService.existById(economyDTO3.getId()));

        economyService.deleteAll(economiesDTODelete);
        assertFalse(economyService.existById(economyDTO1.getId()));
        assertFalse(economyService.existById(economyDTO2.getId()));
        assertTrue(economyService.existById(economyDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, economyService.findAll().size());

    }
}

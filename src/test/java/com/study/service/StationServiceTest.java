package com.study.service;

import com.study.service.dto.StationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link StationService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link StationDTO} entities using the service layer.
 */
public class StationServiceTest {

    private static final String STATION_KYIV = "KYIV Station";
    private static final String STATION_VINNYTSIA = "Vinnytsia Station";
    private static final String STATION_LVIV = "Lviv Station";
    private static final String STATION_MOGPOD = "Mog-Pod Station";

    private static final int SECOND_ELEMENT_STATION = 1;
    private static final int PRIMARY_LIST_STATION_SIZE = 3;

    private static final int EXPECTED_SIZE_ADDITION = 1;
    private static final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private StationDTO stationDTO1;
    private StationDTO stationDTO2;
    private StationDTO stationDTO3;

    private StationService stationService;

    private StationDTO createDTO(String nameOfStation){
        return new StationDTO().nameOfStation(nameOfStation);
    }

    @BeforeEach
    void setUp() {
        stationService = new StationService();

        stationDTO1 = stationService.save(createDTO(STATION_KYIV));
        stationDTO2 = stationService.save(createDTO(STATION_VINNYTSIA));
        stationDTO3 = stationService.save(createDTO(STATION_LVIV));
    }

    @AfterEach
    void tearDown() {
        stationService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = stationService.findAll().size();
        StationDTO expectedDTO = createDTO(STATION_MOGPOD);
        StationDTO saved = stationService.save(expectedDTO);

        assertTrue(stationService.existById(saved.getId()));

        assertEquals(expectedDTO.getNameOfStation(), saved.getNameOfStation());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, stationService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = stationService.findAll().size();
        List<StationDTO> stationsDTO = new ArrayList<>();

        StationDTO stationDTO4 = createDTO(STATION_MOGPOD);
        StationDTO stationDTO5 = createDTO(STATION_LVIV);

        stationsDTO.add(stationDTO4);
        stationsDTO.add(stationDTO5);

        List<StationDTO> stationDTOS = stationService.saveAll(stationsDTO);

        assertTrue(stationService.existById(stationDTOS.getFirst().getId()));
        assertTrue(stationService.existById(stationDTOS.get(SECOND_ELEMENT_STATION).getId()));
        assertEquals(stationDTO4.getNameOfStation(), stationDTOS.getFirst().getNameOfStation());
        assertEquals(stationDTO5.getNameOfStation(), stationDTOS.get(SECOND_ELEMENT_STATION).getNameOfStation());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, stationService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(stationService.existById(stationDTO1.getId()));
        StationDTO saved = stationService.findById(stationDTO1.getId()).orElseThrow();
        assertEquals(stationDTO1.getNameOfStation(), saved.getNameOfStation());
    }

    @Test
    void findAll() {
        List<StationDTO> stationDTOS = stationService.findAll();
        assertEquals(PRIMARY_LIST_STATION_SIZE, stationService.findAll().size());
        assertTrue(stationService.existById(stationDTOS.getFirst().getId()));
        assertTrue(stationService.existById(stationDTOS.get(SECOND_ELEMENT_STATION).getId()));
        assertEquals(stationDTO1.getNameOfStation(), stationDTOS.getFirst().getNameOfStation());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = stationService.findAll().size();
        StationDTO stationDTO4 = createDTO(STATION_MOGPOD);

        assertTrue(stationService.updateId(stationDTO2.getId(), stationDTO4));
        assertTrue(stationService.existById(stationDTO4.getId()));
        assertEquals(stationDTO4.getNameOfStation(), stationService.findById(stationDTO4.getId()).get().getNameOfStation());
        assertEquals(sizeBeforeUpdate, stationService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = stationService.findAll().size();

        stationService.delete(stationDTO1);
        assertFalse(stationService.existById(stationDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, stationService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = stationService.findAll().size();
        List<StationDTO> stationsDTODelete = List.of(stationDTO1, stationDTO2);

        stationService.deleteAll(stationsDTODelete);
        assertFalse(stationService.existById(stationDTO1.getId()));
        assertFalse(stationService.existById(stationDTO2.getId()));
        assertTrue(stationService.existById(stationDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, stationService.findAll().size());
    }
}

package com.study.service;

import com.study.service.dto.TrainDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link TrainService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link TrainDTO} entities using the service layer.
 */
public class TrainServiceTest {

    private static final int MAX_AMOUNT_SEATS_TRAIN = 120;
    private static final int MIN_AMOUNT_SEATS_TRAIN = 40;
    private static final int AVERAGE_AMOUNT_SEATS_TRAIN = 80;
    private static final int AVERAGE_MAX_AMOUNT_SEATS_TRAIN = 60;

    private static final int SECOND_ELEMENT_TRAIN = 1;
    private static final int PRIMARY_LIST_TRAIN_SIZE = 3;

    private static final int EXPECTED_SIZE_ADDITION = 1;
    private static final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private TrainDTO trainDTO1;
    private TrainDTO trainDTO2;
    private TrainDTO trainDTO3;

    private TrainService trainService;

    private TrainDTO createDTO(int amountOfSeats){
        return new TrainDTO().amountOfSeats(amountOfSeats);
    }

    @BeforeEach
    void setUp() {
        trainService = new TrainService();

        trainDTO1 = trainService.save(createDTO(MAX_AMOUNT_SEATS_TRAIN));
        trainDTO2 = trainService.save(createDTO(MIN_AMOUNT_SEATS_TRAIN));
        trainDTO3 = trainService.save(createDTO(AVERAGE_AMOUNT_SEATS_TRAIN));
    }

    @AfterEach
    void tearDown() {
        trainService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = trainService.findAll().size();
        TrainDTO expectedDTO = createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN);
        TrainDTO saved = trainService.save(expectedDTO);

        assertTrue(trainService.existById(saved.getId()));

        assertEquals(expectedDTO.getAmountOfSeats(), saved.getAmountOfSeats());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, trainService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = trainService.findAll().size();
        List<TrainDTO> trainsDTO = new ArrayList<>();

        TrainDTO trainDTO4 = createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN);
        TrainDTO trainDTO5 = createDTO(AVERAGE_AMOUNT_SEATS_TRAIN);

        trainsDTO.add(trainDTO4);
        trainsDTO.add(trainDTO5);

        List<TrainDTO> stationDTOS = trainService.saveAll(trainsDTO);

        assertTrue(trainService.existById(stationDTOS.getFirst().getId()));
        assertTrue(trainService.existById(stationDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(trainDTO4.getAmountOfSeats(), stationDTOS.getFirst().getAmountOfSeats());
        assertEquals(trainDTO5.getAmountOfSeats(), stationDTOS.get(SECOND_ELEMENT_TRAIN).getAmountOfSeats());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, trainService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(trainService.existById(trainDTO1.getId()));
        TrainDTO saved = trainService.findById(trainDTO1.getId()).orElseThrow();
        assertEquals(trainDTO1.getAmountOfSeats(), saved.getAmountOfSeats());
    }

    @Test
    void findAll() {
        List<TrainDTO> trainDTOS = trainService.findAll();
        assertEquals(PRIMARY_LIST_TRAIN_SIZE, trainService.findAll().size());
        assertTrue(trainService.existById(trainDTOS.getFirst().getId()));
        assertTrue(trainService.existById(trainDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(trainDTO1.getAmountOfSeats(), trainDTOS.getFirst().getAmountOfSeats());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = trainService.findAll().size();
        TrainDTO trainDTO4 = createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN);

        assertTrue(trainService.updateId(trainDTO2.getId(), trainDTO4));
        assertTrue(trainService.existById(trainDTO4.getId()));
        assertEquals(trainDTO4.getAmountOfSeats(), trainService.findById(trainDTO4.getId()).get().getAmountOfSeats());
        assertEquals(sizeBeforeUpdate, trainService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = trainService.findAll().size();

        trainService.delete(trainDTO1);
        assertFalse(trainService.existById(trainDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, trainService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDelete = trainService.findAll().size();
        List<TrainDTO> trainsDTODelete = List.of(trainDTO1, trainDTO2);

        trainService.deleteAll(trainsDTODelete);
        assertFalse(trainService.existById(trainDTO1.getId()));
        assertFalse(trainService.existById(trainDTO2.getId()));
        assertTrue(trainService.existById(trainDTO3.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION_LIST, trainService.findAll().size());
    }
}

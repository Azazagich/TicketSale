package com.study.serviceTest;

import com.study.repository.TrainRepository;
import com.study.service.TrainService;
import com.study.service.dto.TrainDTO;
import com.study.service.mapper.TrainMapper;
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

    private final int MAX_AMOUNT_SEATS_TRAIN = 120;
    private final int MIN_AMOUNT_SEATS_TRAIN = 40;
    private final int AVERAGE_AMOUNT_SEATS_TRAIN = 80;
    private final int AVERAGE_MAX_AMOUNT_SEATS_TRAIN = 60;
    
    private final int FIRST_ELEMENT_TRAIN = 0;
    private final int SECOND_ELEMENT_TRAIN = 1;
    private final int PRIMARY_LIST_TRAIN_SIZE = 3;

    private TrainDTO trainDTO1;
    private TrainDTO trainDTO2;
    private TrainDTO trainDTO3;

    private TrainService trainService;
    private TrainMapper trainMapper;
    private TrainRepository trainRepository;

    private TrainDTO createDTO(int amountOfSeats){
        return new TrainDTO().amountOfSeats(amountOfSeats);
    }

    @BeforeEach
    void setUp() {
        trainRepository = new TrainRepository();
        trainMapper = new TrainMapper();

        trainService = new TrainService(trainRepository, trainMapper);

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
        TrainDTO saved = trainService.save(createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN));

        assertTrue(trainService.existById(saved.getId()));

        TrainDTO saved2 = trainService.findById(saved.getId()).orElseThrow();

        assertEquals(AVERAGE_MAX_AMOUNT_SEATS_TRAIN, saved.getAmountOfSeats());
        assertEquals(saved2.getAmountOfSeats(), saved.getAmountOfSeats());
    }

    @Test
    void saveAll() {
        List<TrainDTO> trainsDTO = new ArrayList<>();

        TrainDTO trainDTO4 = createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN);
        TrainDTO trainDTO5 = createDTO(AVERAGE_AMOUNT_SEATS_TRAIN);

        trainsDTO.add(trainDTO4);
        trainsDTO.add(trainDTO5);

        List<TrainDTO> stationDTOS = trainService.saveAll(trainsDTO);

        assertTrue(trainService.existById(stationDTOS.get(FIRST_ELEMENT_TRAIN).getId()));
        assertTrue(trainService.existById(stationDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(trainDTO4.getAmountOfSeats(), stationDTOS.get(FIRST_ELEMENT_TRAIN).getAmountOfSeats());
        assertEquals(trainDTO5.getAmountOfSeats(), stationDTOS.get(SECOND_ELEMENT_TRAIN).getAmountOfSeats());
    }

    @Test
    void findById() {
        assertTrue(trainService.existById(trainDTO1.getId()));
        TrainDTO saved = trainService.findById(trainDTO1.getId()).orElseThrow();
        assertEquals(trainDTO1.getAmountOfSeats(), saved.getAmountOfSeats());
    }

    @Test
    void findAll() {
        assertEquals(PRIMARY_LIST_TRAIN_SIZE, trainService.findAll().size());
        assertTrue(trainService.existById(trainDTO2.getId()));
        assertTrue(trainService.existById(trainDTO3.getId()));
        assertNotNull(trainService.findAll());
    }

    @Test
    void updateId() {
        TrainDTO trainDTO4 = createDTO(AVERAGE_MAX_AMOUNT_SEATS_TRAIN);

        assertTrue(trainService.updateId(trainDTO2.getId(), trainDTO4));
        assertTrue(trainService.existById(trainDTO4.getId()));
        assertEquals(trainDTO4.getAmountOfSeats(), trainService.findById(trainDTO4.getId()).get().getAmountOfSeats());
    }

    @Test
    void delete() {
        assertTrue(trainService.existById(trainDTO1.getId()));
        trainService.delete(trainDTO1);
        assertFalse(trainService.existById(trainDTO1.getId()));
    }

    @Test
    void deleteAll() {
        List<TrainDTO> trainsDTODelete = List.of(trainDTO1, trainDTO2);

        assertTrue(trainService.existById(trainDTO1.getId()));
        assertTrue(trainService.existById(trainDTO2.getId()));
        assertTrue(trainService.existById(trainDTO3.getId()));

        trainService.deleteAll(trainsDTODelete);
        assertFalse(trainService.existById(trainDTO1.getId()));
        assertFalse(trainService.existById(trainDTO2.getId()));
        assertTrue(trainService.existById(trainDTO3.getId()));
    }
}

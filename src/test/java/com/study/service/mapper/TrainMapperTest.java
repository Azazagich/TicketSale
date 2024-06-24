package com.study.service.mapper;

import com.study.domain.Train;
import com.study.service.dto.TrainDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link TrainMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class TrainMapperTest {

    private static final int MAX_AMOUNT_SEATS_TRAIN = 120;
    private static final int AVERAGE_SEATS_TRAIN = 70;
    private static final int MIN_AMOUNT_SEATS_TRAIN = 40;

    private static final int ID_1 = 1;
    private static final int ID_3 = 3;

    private TrainMapper trainMapper;

    private TrainDTO trainDTO;
    private Train train;

    private TrainDTO createDTO(){
        return new TrainDTO();
    }

    private TrainDTO createDTO(int amountOfSeats){
        return new TrainDTO().amountOfSeats(amountOfSeats);
    }

    private TrainDTO createDTO(int id, int amountOfSeats){
        return new TrainDTO().id(id).amountOfSeats(amountOfSeats);
    }

    private Train createEntity(){
        return new Train();
    }

    private Train createEntity(int amountOfSeats){
        return new Train().amountOfSeats(amountOfSeats);
    }

    private Train createEntity(int id, int amountOfSeats){
        return new Train().id(id).amountOfSeats(amountOfSeats);
    }

    @BeforeEach
    void setUp() {
        trainMapper = new TrainMapper();

        trainDTO = createDTO(MAX_AMOUNT_SEATS_TRAIN);
        train = createEntity(MIN_AMOUNT_SEATS_TRAIN);
    }

    @Test
    void testToDTO() {
        assertEquals(trainMapper.toDTO(train), createDTO(MAX_AMOUNT_SEATS_TRAIN));
        assertEquals(trainMapper.toDTO(createEntity()), createDTO());
    }

    @Test
    void testToDTOs() {
        Train train2 = createEntity(ID_3, AVERAGE_SEATS_TRAIN);
        List<Train> trains = List.of(train, train2);
        List<TrainDTO> expectedDTOs = List.of(createDTO(MAX_AMOUNT_SEATS_TRAIN),
                createDTO(ID_3, AVERAGE_SEATS_TRAIN));
        assertIterableEquals(trainMapper.toDTO(trains), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Train> train = Optional.of(createEntity(ID_1, AVERAGE_SEATS_TRAIN));
        assertEquals(trainMapper.toDTO(train), Optional.of(createDTO(ID_1, AVERAGE_SEATS_TRAIN)));
    }

    @Test
    void testToEntity() {
        assertEquals(trainMapper.toEntity(trainDTO), createEntity(MAX_AMOUNT_SEATS_TRAIN));
    }

    @Test
    void testToEntities() {
        TrainDTO train2DTO = createDTO(ID_3, MAX_AMOUNT_SEATS_TRAIN);
        List<TrainDTO> trainsDTO = List.of(trainDTO, train2DTO);
        List<Train> expectedEntities = List.of(createEntity(MAX_AMOUNT_SEATS_TRAIN),
                createEntity(ID_3, MAX_AMOUNT_SEATS_TRAIN));
        assertIterableEquals(trainMapper.toEntity(trainsDTO), expectedEntities);
    }
}

package com.study.serviceTest.mapperTest;

import com.study.domain.Train;
import com.study.service.dto.TrainDTO;
import com.study.service.mapper.TrainMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link TrainMapper}.
 * This class contains unit tests for the {@link TrainMapper} class which converts
 * between {@link Train} and {@link TrainDTO} objects.
 */
public class TrainMapperTest {

    private TrainMapper trainMapper;

    private TrainDTO trainDTO;
    private Train train;

    @BeforeEach
    void setUp() {
        trainMapper = new TrainMapper();

        trainDTO = new TrainDTO().amountOfSeats(50);
        train = new Train().amountOfSeats(35);
    }

    @Test
    void testToDTO() {
        assertEquals(trainMapper.toDTO(train), new TrainDTO().amountOfSeats(50));
        assertEquals(trainMapper.toDTO(new Train()), new TrainDTO());
        assertInstanceOf(TrainDTO.class, trainMapper.toDTO(new Train()));
    }

    @Test
    void testToDTOs() {
        Train train2 = new Train().id(3).amountOfSeats(25);
        List<Train> trains = List.of(train, train2);
        List<TrainDTO> expectedDTOs = List.of(new TrainDTO().amountOfSeats(50), new TrainDTO().id(3).amountOfSeats(25));
        assertEquals(trainMapper.toDTO(trains), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Train> train = Optional.of(new Train().id(1).amountOfSeats(25));
        assertEquals(trainMapper.toDTO(train), Optional.of(new TrainDTO().id(1)));
    }

    @Test
    void testToEntity() {
        assertEquals(trainMapper.toEntity(trainDTO), new Train().amountOfSeats(50));
        assertInstanceOf(Train.class, trainMapper.toEntity(new TrainDTO()));
    }

    @Test
    void testToEntities() {
        TrainDTO train2DTO = new TrainDTO().id(3).amountOfSeats(50);
        List<TrainDTO> trainsDTO = List.of(trainDTO, train2DTO);
        List<Train> expectedEntities = List.of(new Train().amountOfSeats(50), new Train().id(3).amountOfSeats(50));
        assertEquals(trainMapper.toEntity(trainsDTO), expectedEntities);
    }
}

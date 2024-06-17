package com.study.serviceTest.mapperTest;

import com.study.domain.Station;
import com.study.service.dto.StationDTO;
import com.study.service.mapper.StationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link StationMapper}.
 * This class contains unit tests for the {@link StationMapper} class which converts
 * between {@link Station} and {@link StationDTO} objects.
 */
public class StationMapperTest {

    private StationMapper stationMapper;

    private StationDTO stationDTO;
    private Station station;

    @BeforeEach
    void setUp() {
        stationMapper = new StationMapper();

        stationDTO = new StationDTO().nameOfStation("Львівський");
        station = new Station().nameOfStation("Київський");
    }

    @Test
    void testToDTO() {
        assertEquals(stationMapper.toDTO(station), new StationDTO().nameOfStation("Львівський"));
        assertEquals(stationMapper.toDTO(new Station()), new StationDTO());
        assertInstanceOf(StationDTO.class, stationMapper.toDTO(new Station()));
    }

    @Test
    void testToDTOs() {
        Station station2 = new Station().id(3).nameOfStation("Львівський");
        List<Station> stations = List.of(station, station2);
        List<StationDTO> expectedDTOs = List.of(new StationDTO().nameOfStation("Львівський"), new StationDTO().id(3).nameOfStation("Київський"));
        assertEquals(stationMapper.toDTO(stations), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Station> station = Optional.of(new Station().id(1).nameOfStation("Львівський"));
        assertEquals(stationMapper.toDTO(station), Optional.of(new StationDTO().id(1).nameOfStation("Львівський")));
    }

    @Test
    void testToEntity() {
        assertEquals(stationMapper.toEntity(stationDTO), new Station().nameOfStation("Львівський"));
        assertInstanceOf(Station.class, stationMapper.toEntity(new StationDTO()));
    }

    @Test
    void testToEntities() {
        StationDTO station2DTO = new StationDTO().id(3).nameOfStation("Львівський");
        List<StationDTO> stationsDTO = List.of(stationDTO, station2DTO);
        List<Station> expectedEntities = List.of(new Station().nameOfStation("Львівський"), new Station().id(3).nameOfStation("Львівський"));
        assertEquals(stationMapper.toEntity(stationsDTO), expectedEntities);
    }
}

package com.study.service.mapper;

import com.study.domain.Station;
import com.study.service.dto.StationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * This class contains unit tests for the {@link StationMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class StationMapperTest {

    private final String STATION_KYIV = "KYIV Station";
    private final String STATION_VINNYTSIA = "Vinnytsia Station";

    private final int ID_1 = 1;
    private final int ID_3 = 3;

    private StationMapper stationMapper;

    private StationDTO stationDTO;
    private Station station;

    private StationDTO createDTO(){
        return new StationDTO();
    }

    private StationDTO createDTO(String nameOfStation){
        return new StationDTO().nameOfStation(nameOfStation);
    }

    private StationDTO createDTO(int id, String nameOfStation){
        return new StationDTO().id(id).nameOfStation(nameOfStation);
    }

    private Station createEntity(){
        return new Station();
    }

    private Station createEntity(String nameOfStation){
        return new Station().nameOfStation(nameOfStation);
    }

    private Station createEntity(int id, String nameOfStation){
        return new Station().id(id).nameOfStation(nameOfStation);
    }

    @BeforeEach
    void setUp() {
        stationMapper = new StationMapper();

        stationDTO = new StationDTO().nameOfStation(STATION_VINNYTSIA);
        station = new Station().nameOfStation(STATION_KYIV);
    }

    @Test
    void testToDTO() {
        assertEquals(stationMapper.toDTO(station), createDTO(STATION_VINNYTSIA));
        assertEquals(stationMapper.toDTO(createEntity()), createDTO());
        assertInstanceOf(StationDTO.class, stationMapper.toDTO(createEntity()));
    }

    @Test
    void testToDTOs() {
        Station station2 = createEntity(ID_3, STATION_VINNYTSIA);
        List<Station> stations = List.of(station, station2);
        List<StationDTO> expectedDTOs = List.of(createDTO(STATION_VINNYTSIA), createDTO(ID_3, STATION_KYIV));
        assertEquals(stationMapper.toDTO(stations), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Station> station = Optional.of(createEntity(ID_1, STATION_VINNYTSIA));
        assertEquals(stationMapper.toDTO(station), Optional.of(createDTO(ID_1, STATION_VINNYTSIA)));
    }

    @Test
    void testToEntity() {
        assertEquals(stationMapper.toEntity(stationDTO), createEntity(STATION_VINNYTSIA));
        assertInstanceOf(Station.class, stationMapper.toEntity(createDTO()));
    }

    @Test
    void testToEntities() {
        StationDTO station2DTO = createDTO(ID_3,STATION_VINNYTSIA);
        List<StationDTO> stationsDTO = List.of(stationDTO, station2DTO);
        List<Station> expectedEntities = List.of(createEntity(STATION_VINNYTSIA), createEntity(ID_3, STATION_VINNYTSIA));
        assertEquals(stationMapper.toEntity(stationsDTO), expectedEntities);
    }
}

package com.study.service.mapper;

import com.study.domain.Economy;
import com.study.service.dto.EconomyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * This class contains unit tests for the {@link EconomyMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class EconomyMapperTest {

    private final String ECONOMY_CLASS_STANDARD = "Стандарт";
    private final String ECONOMY_CLASS_ECONOMY = "Економ";

    private final int ID_1 = 1;
    private final int ID_3 = 3;

    private EconomyMapper economyMapper;

    private EconomyDTO economyDTO;
    private Economy economy;

    private EconomyDTO createDTO(){
        return new EconomyDTO();
    }

    private EconomyDTO createDTO(String type){
        return new EconomyDTO().type(type);
    }

    private EconomyDTO createDTO(int id, String type){
        return new EconomyDTO().id(id).type(type);
    }

    private Economy createEntity(){
        return new Economy();
    }

    private Economy createEntity(String type){
        return new Economy().type(type);
    }

    private Economy createEntity(int id, String type){
        return new Economy().id(id).type(type);
    }

    @BeforeEach
    void setUp() {
        economyMapper = new EconomyMapper();

        economyDTO = createDTO().type(ECONOMY_CLASS_ECONOMY);
        economy = new Economy().type(ECONOMY_CLASS_STANDARD);
    }

    @Test
    void testToDTO() {
        assertEquals(economyMapper.toDTO(economy), createDTO(ECONOMY_CLASS_STANDARD));
        assertEquals(economyMapper.toDTO(createEntity()), createDTO());
        assertInstanceOf(EconomyDTO.class, economyMapper.toDTO(createEntity()));
    }

    @Test
    void testToDTOs() {
        Economy economy2 = createEntity(ID_3,ECONOMY_CLASS_STANDARD);
        List<Economy> economies = List.of(economy, economy2);
        List<EconomyDTO> expectedDTOs = List.of(createDTO(ECONOMY_CLASS_ECONOMY), createDTO(ID_3,ECONOMY_CLASS_STANDARD));
        assertEquals(economyMapper.toDTO(economies), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Economy> economy = Optional.of(createEntity(ID_1,ECONOMY_CLASS_STANDARD));
        assertEquals(economyMapper.toDTO(economy), Optional.of(createDTO(ID_1, ECONOMY_CLASS_STANDARD)));
    }

    @Test
    void testToEntity() {
        assertEquals(economyMapper.toEntity(economyDTO), createEntity(ECONOMY_CLASS_ECONOMY));
        assertInstanceOf(Economy.class, economyMapper.toEntity(createDTO()));
    }

    @Test
    void testToEntities() {
        EconomyDTO economy2DTO = createDTO(ID_3, ECONOMY_CLASS_STANDARD);
        List<EconomyDTO> economiesDTO = List.of(economyDTO, economy2DTO);
        List<Economy> expectedEntities = List.of(createEntity(ECONOMY_CLASS_ECONOMY), createEntity(ID_3,ECONOMY_CLASS_STANDARD));
        assertEquals(economyMapper.toEntity(economiesDTO), expectedEntities);
    }
}

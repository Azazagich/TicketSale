package com.study.serviceTest.mapperTest;

import com.study.domain.Discount;
import com.study.domain.Economy;
import com.study.service.dto.DiscountDTO;
import com.study.service.dto.EconomyDTO;
import com.study.service.mapper.DiscountMapper;
import com.study.service.mapper.EconomyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link EconomyMapper}.
 * This class contains unit tests for the {@link EconomyMapper} class which converts
 * between {@link Economy} and {@link EconomyDTO} objects.
 */
public class EconomyMapperTest {

    private EconomyMapper economyMapper;

    private EconomyDTO economyDTO;
    private Economy economy;

    @BeforeEach
    void setUp() {
        economyMapper = new EconomyMapper();

        economyDTO = new EconomyDTO().type("Економ");
        economy = new Economy().type("Стандарт");
    }

    @Test
    void testToDTO() {
        assertEquals(economyMapper.toDTO(economy), new EconomyDTO().type("Стандарт"));
        assertEquals(economyMapper.toDTO(new Economy()), new EconomyDTO());
        assertInstanceOf(EconomyDTO.class, economyMapper.toDTO(new Economy()));
    }

    @Test
    void testToDTOs() {
        Economy economy2 = new Economy().id(3).type("Стандарт");
        List<Economy> economies = List.of(economy, economy2);
        List<EconomyDTO> expectedDTOs = List.of(new EconomyDTO().type("Економ"), new EconomyDTO().id(3).type("Стандарт"));
        assertEquals(economyMapper.toDTO(economies), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Economy> economy = Optional.of(new Economy().id(1).type("Стандарт"));
        assertEquals(economyMapper.toDTO(economy), Optional.of(new EconomyDTO().id(1).type("Стандарт")));
    }

    @Test
    void testToEntity() {
        assertEquals(economyMapper.toEntity(economyDTO), new Economy().type("Економ"));
        assertInstanceOf(Economy.class, economyMapper.toEntity(new EconomyDTO()));
    }

    @Test
    void testToEntities() {
        EconomyDTO economy2DTO = new EconomyDTO().id(3).type("Стандарт");
        List<EconomyDTO> economiesDTO = List.of(economyDTO, economy2DTO);
        List<Economy> expectedEntities = List.of(new Economy().type("Економ"), new Economy().id(3).type("Стандарт"));
        assertEquals(economyMapper.toEntity(economiesDTO), expectedEntities);
    }
}

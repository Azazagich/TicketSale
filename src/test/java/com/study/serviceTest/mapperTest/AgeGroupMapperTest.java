package com.study.serviceTest.mapperTest;

import com.study.domain.AgeGroup;
import com.study.service.dto.AgeGroupDTO;
import com.study.service.mapper.AgeGroupMapper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
 * This class contains unit tests for the {@link AgeGroupMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
class AgeGroupMapperTest {

    private AgeGroupMapper ageGroupMapper;

    private AgeGroupDTO ageGroupDTO;
    private AgeGroup ageGroup;

    @BeforeEach
    void setUp() {
        ageGroupMapper = new AgeGroupMapper();

        ageGroupDTO = new AgeGroupDTO().type("Дитина");
        ageGroup = new AgeGroup().type("Пенсіонер");
    }

    @Test
    void testToDTO() {
        assertEquals(ageGroupMapper.toDTO(ageGroup), new AgeGroupDTO().type("Пенсіонер"));
        assertEquals(ageGroupMapper.toDTO(new AgeGroup()), new AgeGroupDTO());
        assertInstanceOf(AgeGroupDTO.class, ageGroupMapper.toDTO(new AgeGroup()));
    }

    @Test
    void testToDTOs() {
        AgeGroup ageGroup2 = new AgeGroup().id(3).type("Дитина");
        List<AgeGroup> ageGroups = List.of(ageGroup, ageGroup2);
        List<AgeGroupDTO> expectedDTOs = List.of(new AgeGroupDTO().type("Пенсіонер"), new AgeGroupDTO().id(3).type("Дитина"));
        assertEquals(ageGroupMapper.toDTO(ageGroups), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<AgeGroup> ageGroup = Optional.of(new AgeGroup().id(1).type("Пенсіонер"));
        assertEquals(ageGroupMapper.toDTO(ageGroup), Optional.of(new AgeGroupDTO().id(1).type("Пенсіонер")));
    }

    @Test
    void testToEntity() {
        assertEquals(ageGroupMapper.toEntity(ageGroupDTO), new AgeGroup().type("Дитина"));
        assertInstanceOf(AgeGroup.class, ageGroupMapper.toEntity(new AgeGroupDTO()));
    }

    @Test
    void testToEntities() {
        AgeGroupDTO ageGroup2DTO = new AgeGroupDTO().id(3).type("Дитина");
        List<AgeGroupDTO> ageGroupsDTO = List.of(ageGroupDTO, ageGroup2DTO);
        List<AgeGroup> expectedEntities = List.of(new AgeGroup().type("Дитина"), new AgeGroup().id(3).type("Дитина"));
        assertEquals(ageGroupMapper.toEntity(ageGroupsDTO), expectedEntities);
    }
}
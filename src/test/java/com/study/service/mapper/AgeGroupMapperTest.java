package com.study.service.mapper;

import com.study.domain.AgeGroup;
import com.study.service.dto.AgeGroupDTO;

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

    private static final String AGE_GROUP_ADULT_TYPE = "Дорослий";
    private static final String AGE_GROUP_CHILD_TYPE = "Дитина";

    private static final int ID_1 = 1;
    private static final int ID_3 = 3;

    private AgeGroupMapper ageGroupMapper;

    private AgeGroupDTO ageGroupDTO;
    private AgeGroup ageGroup;

    private AgeGroupDTO createDTO(){
        return new AgeGroupDTO();
    }

    private AgeGroup createEntity(){
        return new AgeGroup();
    }

    @BeforeEach
    void setUp() {
        ageGroupMapper = new AgeGroupMapper();

        ageGroupDTO = createDTO().type(AGE_GROUP_CHILD_TYPE);
        ageGroup = createEntity().type(AGE_GROUP_ADULT_TYPE);
    }

    @Test
    void testToDTO() {
        assertEquals(ageGroupMapper.toDTO(ageGroup), createDTO().type(AGE_GROUP_ADULT_TYPE));
        assertEquals(ageGroupMapper.toDTO(createEntity()), createDTO());
    }

    @Test
    void testToDTOs() {
        AgeGroup ageGroup2 = createEntity().id(ID_3).type(AGE_GROUP_ADULT_TYPE);
        List<AgeGroup> ageGroups = List.of(ageGroup, ageGroup2);
        List<AgeGroupDTO> expectedDTOs = List.of(createDTO().type(AGE_GROUP_ADULT_TYPE),
                createDTO().id(ID_3).type(AGE_GROUP_CHILD_TYPE));
        assertIterableEquals(ageGroupMapper.toDTO(ageGroups), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<AgeGroup> ageGroup = Optional.of(createEntity().id(ID_1).type(AGE_GROUP_ADULT_TYPE));
        assertEquals(ageGroupMapper.toDTO(ageGroup), Optional.of(createDTO().id(ID_1).type(AGE_GROUP_ADULT_TYPE)));
    }

    @Test
    void testToEntity() {
        assertEquals(ageGroupMapper.toEntity(ageGroupDTO), createEntity().type(AGE_GROUP_CHILD_TYPE));
    }

    @Test
    void testToEntities() {
        AgeGroupDTO ageGroup2DTO = createDTO().id(ID_3).type(AGE_GROUP_CHILD_TYPE);
        List<AgeGroupDTO> ageGroupsDTO = List.of(ageGroupDTO, ageGroup2DTO);
        List<AgeGroup> expectedEntities = List.of(createEntity().type(AGE_GROUP_CHILD_TYPE),
                createEntity().id(ID_3).type(AGE_GROUP_CHILD_TYPE));
        assertIterableEquals(ageGroupMapper.toEntity(ageGroupsDTO), expectedEntities);
    }
}
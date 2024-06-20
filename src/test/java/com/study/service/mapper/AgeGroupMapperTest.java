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

    private final String AGE_GROUP_ADULT_TYPE = "Дорослий";
    private final String AGE_GROUP_CHILD_TYPE = "Дитина";

    private final int ID_1 = 1;
    private final int ID_3 = 3;

    private AgeGroupMapper ageGroupMapper;

    private AgeGroupDTO ageGroupDTO;
    private AgeGroup ageGroup;

    private AgeGroupDTO createDTO(){
        return new AgeGroupDTO();
    }

    private AgeGroupDTO createDTO(String type){
        return new AgeGroupDTO().type(type);
    }

    private AgeGroupDTO createDTO(int id, String type){
        return new AgeGroupDTO().id(id).type(type);
    }

    private AgeGroup createEntity(){
        return new AgeGroup();
    }

    private AgeGroup createEntity(String type){
        return new AgeGroup().type(type);
    }

    private AgeGroup createEntity(int id, String type){
        return new AgeGroup().id(id).type(type);
    }

    @BeforeEach
    void setUp() {
        ageGroupMapper = new AgeGroupMapper();

        ageGroupDTO = createDTO(AGE_GROUP_CHILD_TYPE);
        ageGroup = createEntity(AGE_GROUP_ADULT_TYPE);
    }

    @Test
    void testToDTO() {
        assertEquals(ageGroupMapper.toDTO(ageGroup), createDTO(AGE_GROUP_ADULT_TYPE));
        assertEquals(ageGroupMapper.toDTO(createEntity()), createDTO());
        assertInstanceOf(AgeGroupDTO.class, ageGroupMapper.toDTO(createEntity()));
    }

    @Test
    void testToDTOs() {
        AgeGroup ageGroup2 = createEntity(ID_3, AGE_GROUP_ADULT_TYPE);
        List<AgeGroup> ageGroups = List.of(ageGroup, ageGroup2);
        List<AgeGroupDTO> expectedDTOs = List.of(createDTO(AGE_GROUP_ADULT_TYPE), createDTO(ID_3, AGE_GROUP_CHILD_TYPE));
        assertEquals(ageGroupMapper.toDTO(ageGroups), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<AgeGroup> ageGroup = Optional.of(createEntity(ID_1, AGE_GROUP_ADULT_TYPE));
        assertEquals(ageGroupMapper.toDTO(ageGroup), Optional.of(createDTO(ID_1, AGE_GROUP_ADULT_TYPE)));
    }

    @Test
    void testToEntity() {
        assertEquals(ageGroupMapper.toEntity(ageGroupDTO), createEntity(AGE_GROUP_CHILD_TYPE));
        assertInstanceOf(AgeGroup.class, ageGroupMapper.toEntity(createDTO()));
    }

    @Test
    void testToEntities() {
        AgeGroupDTO ageGroup2DTO = createDTO(ID_3, AGE_GROUP_CHILD_TYPE);
        List<AgeGroupDTO> ageGroupsDTO = List.of(ageGroupDTO, ageGroup2DTO);
        List<AgeGroup> expectedEntities = List.of(createEntity(AGE_GROUP_CHILD_TYPE), createEntity(ID_3, AGE_GROUP_CHILD_TYPE));
        assertEquals(ageGroupMapper.toEntity(ageGroupsDTO), expectedEntities);
    }
}
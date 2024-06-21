package com.study.service;

import com.study.domain.AgeGroup;
import com.study.repository.AgeGroupRepository;
import com.study.service.dto.AgeGroupDTO;
import static org.junit.jupiter.api.Assertions.*;

import com.study.service.mapper.AgeGroupMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains unit tests for the {@link AgeGroupService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link AgeGroupDTO} entities using the service layer.
 */
public class AgeGroupServiceTest {

    private static final String AGE_GROUP_ADULT_TYPE = "Дорослий";
    private static final String AGE_GROUP_NAME_CHILD_TYPE = "Дитина";
    private static final String AGE_GROUP_NAME_BABIES_TYPE = "Малюки";
    private static final String AGE_GROUP_RETIREE_TYPE = "Пенсіонер";

    private static final int SECOND_ELEMENT_AGE_GROUP = 1;
    private static final int PRIMARY_LIST_AGE_GROUP_SIZE = 3;

    private static final int EXPECTED_SIZE_ADDITION = 1;
    private static final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private AgeGroup ageGroup1;
    private AgeGroup ageGroup2;
    private AgeGroup ageGroup3;

    private AgeGroupService ageGroupService;
    private AgeGroupMapper ageGroupMapper;
    private AgeGroupRepository ageGroupRepository;

    private AgeGroup createEntity(String type){
        return new AgeGroup().type(type);
    }

    @BeforeEach
    void setUp() {
        ageGroupRepository = new AgeGroupRepository();
        ageGroupMapper = new AgeGroupMapper();

        ageGroupService = new AgeGroupService(ageGroupRepository, ageGroupMapper);

        ageGroup1 = ageGroupRepository.save(createEntity(AGE_GROUP_ADULT_TYPE));
        ageGroup2 = ageGroupRepository.save(createEntity(AGE_GROUP_RETIREE_TYPE));
        ageGroup3 = ageGroupRepository.save(createEntity(AGE_GROUP_NAME_CHILD_TYPE));
    }

    @AfterEach
    void tearDown() {
        ageGroupRepository.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = ageGroupRepository.findAll().size();
        AgeGroupDTO saved = ageGroupService.save(ageGroupMapper.toDTO(createEntity(AGE_GROUP_NAME_BABIES_TYPE)));

        assertTrue(ageGroupRepository.existById(saved.getId()));

        AgeGroup saved2 = ageGroupRepository.findById(saved.getId()).orElseThrow();

        assertEquals(AGE_GROUP_NAME_BABIES_TYPE, saved.getType());
        assertEquals(saved2.getType(), saved.getType());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, ageGroupRepository.findAll().size());
    }

     @Test
    void saveAll() {
         int sizeBeforeSaveAll = ageGroupRepository.findAll().size();
         List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();

        AgeGroupDTO ageGroupDTO4 = ageGroupMapper.toDTO(createEntity(AGE_GROUP_NAME_BABIES_TYPE));
        AgeGroupDTO ageGroupDTO5 = ageGroupMapper.toDTO(createEntity(AGE_GROUP_RETIREE_TYPE));

        ageGroupsDTO.add(ageGroupDTO4);
        ageGroupsDTO.add(ageGroupDTO5);

        List<AgeGroupDTO> ageGroupDTOS = ageGroupService.saveAll(ageGroupsDTO);

        assertTrue(ageGroupRepository.existById(ageGroupDTOS.getFirst().getId()));
        assertTrue(ageGroupRepository.existById(ageGroupDTOS.get(SECOND_ELEMENT_AGE_GROUP).getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupDTOS.getFirst().getType());
        assertEquals(ageGroupDTO5.getType(), ageGroupDTOS.get(SECOND_ELEMENT_AGE_GROUP).getType());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, ageGroupRepository.findAll().size());
     }

    @Test
    void findById() {
        assertTrue(ageGroupRepository.existById(ageGroup1.getId()));
        AgeGroupDTO saved = ageGroupService.findById(ageGroup1.getId()).orElseThrow();
        assertEquals(ageGroup1.getType(), saved.getType());
    }

    @Test
    void findAll() {
        List<AgeGroupDTO> ageGroupDTOS = ageGroupService.findAll();
        assertEquals(PRIMARY_LIST_AGE_GROUP_SIZE, ageGroupService.findAll().size());
        assertTrue(ageGroupRepository.existById(ageGroupDTOS.getFirst().getId()));
        assertTrue(ageGroupRepository.existById(ageGroupDTOS.get(SECOND_ELEMENT_AGE_GROUP).getId()));
        assertEquals(ageGroup1.getType(), ageGroupDTOS.getFirst().getType());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = ageGroupRepository.findAll().size();
        AgeGroupDTO ageGroupDTO4 = ageGroupMapper.toDTO(createEntity(AGE_GROUP_NAME_BABIES_TYPE));

        assertTrue(ageGroupService.updateId(ageGroup2.getId(), ageGroupDTO4));
        assertTrue(ageGroupRepository.existById(ageGroupDTO4.getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupRepository.findById(ageGroupDTO4.getId()).get().getType());
        assertEquals(sizeBeforeUpdate, ageGroupRepository.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = ageGroupRepository.findAll().size();

        ageGroupService.delete(ageGroupMapper.toDTO(ageGroup1));
        assertFalse(ageGroupRepository.existById(ageGroup1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, ageGroupRepository.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = ageGroupRepository.findAll().size();
        List<AgeGroupDTO> ageGroupsDTODelete = List.of(ageGroupMapper.toDTO(ageGroup1), ageGroupMapper.toDTO(ageGroup2));

        ageGroupService.deleteAll(ageGroupsDTODelete);
        assertFalse(ageGroupRepository.existById(ageGroup1.getId()));
        assertFalse(ageGroupRepository.existById(ageGroup2.getId()));
        assertTrue(ageGroupRepository.existById(ageGroup3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, ageGroupRepository.findAll().size());
    }
}
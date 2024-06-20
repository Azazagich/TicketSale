package com.study.serviceTest;

import com.study.repository.AgeGroupRepository;
import com.study.service.AgeGroupService;
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

    private final String AGE_GROUP_ADULT_TYPE = "Дорослий";
    private final String AGE_GROUP_NAME_CHILD_TYPE = "Дитина";
    private final String AGE_GROUP_NAME_BABIES_TYPE = "Малюки";
    private final String AGE_GROUP_RETIREE_TYPE = "Пенсіонер";

    private final int FIRST_ELEMENT_AGE_GROUP = 0;
    private final int SECOND_ELEMENT_AGE_GROUP = 1;
    private final int PRIMARY_LIST_AGE_GROUP_SIZE = 3;

    private final int EXPECTED_SIZE_ADDITION = 1;
    private final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private AgeGroupDTO ageGroupDTO1;
    private AgeGroupDTO ageGroupDTO2;
    private AgeGroupDTO ageGroupDTO3;

    private AgeGroupService ageGroupService;
    private AgeGroupMapper ageGroupMapper;
    private AgeGroupRepository ageGroupRepository;

    private AgeGroupDTO createDTO(String type){
        return new AgeGroupDTO().type(type);
    }

    @BeforeEach
    void setUp() {
        ageGroupRepository = new AgeGroupRepository();
        ageGroupMapper = new AgeGroupMapper();

        ageGroupService = new AgeGroupService(ageGroupRepository, ageGroupMapper);

        ageGroupDTO1 = ageGroupService.save(createDTO(AGE_GROUP_ADULT_TYPE));
        ageGroupDTO2 = ageGroupService.save(createDTO(AGE_GROUP_RETIREE_TYPE));
        ageGroupDTO3 = ageGroupService.save(createDTO(AGE_GROUP_NAME_CHILD_TYPE));
    }

    @AfterEach
    void tearDown() {
       ageGroupService.deleteAll();

    }

    @Test
    void save() {
        int sizeBeforeSave = ageGroupService.findAll().size();
        AgeGroupDTO saved = ageGroupService.save(createDTO(AGE_GROUP_NAME_BABIES_TYPE));

        assertTrue(ageGroupService.existById(saved.getId()));

        AgeGroupDTO saved2 = ageGroupService.findById(saved.getId()).orElseThrow();

        assertEquals(AGE_GROUP_NAME_BABIES_TYPE, saved.getType());
        assertEquals(saved2.getType(), saved.getType());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, ageGroupService.findAll().size());
    }

     @Test
    void saveAll() {
         int sizeBeforeSaveAll = ageGroupService.findAll().size();
         List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();

        AgeGroupDTO ageGroupDTO4 = createDTO(AGE_GROUP_NAME_BABIES_TYPE);
        AgeGroupDTO ageGroupDTO5 = createDTO(AGE_GROUP_RETIREE_TYPE);

        ageGroupsDTO.add(ageGroupDTO4);
        ageGroupsDTO.add(ageGroupDTO5);

        List<AgeGroupDTO> ageGroupDTOS = ageGroupService.saveAll(ageGroupsDTO);

        assertTrue(ageGroupService.existById(ageGroupDTOS.get(FIRST_ELEMENT_AGE_GROUP).getId()));
        assertTrue(ageGroupService.existById(ageGroupDTOS.get(SECOND_ELEMENT_AGE_GROUP).getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupDTOS.get(FIRST_ELEMENT_AGE_GROUP).getType());
        assertEquals(ageGroupDTO5.getType(), ageGroupDTOS.get(SECOND_ELEMENT_AGE_GROUP).getType());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, ageGroupService.findAll().size());
     }

    @Test
    void findById() {
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        AgeGroupDTO saved = ageGroupService.findById(ageGroupDTO1.getId()).orElseThrow();
        assertEquals(ageGroupDTO1.getType(), saved.getType());
    }

    @Test
    void findAll() {
        //final int PRIMARY_LIST_AGEGROUP_SIZE = 3;
        assertEquals(PRIMARY_LIST_AGE_GROUP_SIZE, ageGroupService.findAll().size());
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));
        assertNotNull(ageGroupService.findAll());
    }

    @Test
    void updateId() {
        int sizeBeforeUpdate = ageGroupService.findAll().size();
        AgeGroupDTO ageGroupDTO4 = createDTO(AGE_GROUP_NAME_BABIES_TYPE);

        assertTrue(ageGroupService.updateId(ageGroupDTO2.getId(), ageGroupDTO4));
        assertTrue(ageGroupService.existById(ageGroupDTO4.getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupService.findById(ageGroupDTO4.getId()).get().getType());
        assertEquals(sizeBeforeUpdate, ageGroupService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = ageGroupService.findAll().size();

        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        ageGroupService.delete(ageGroupDTO1);
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, ageGroupService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = ageGroupService.findAll().size();
        List<AgeGroupDTO> ageGroupsDTODelete = List.of(ageGroupDTO1, ageGroupDTO2);

        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));

        ageGroupService.deleteAll(ageGroupsDTODelete);
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
        assertFalse(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, ageGroupService.findAll().size());
    }
}
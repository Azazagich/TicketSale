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
    private final String AGE_GROUP_RETIREE_CHILD_TYPE = "Пенсіонер";

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
        ageGroupDTO2 = ageGroupService.save(createDTO(AGE_GROUP_RETIREE_CHILD_TYPE));
        ageGroupDTO3 = ageGroupService.save(createDTO(AGE_GROUP_NAME_CHILD_TYPE));
    }

    @AfterEach
    void tearDown() {
       ageGroupService.deleteAll();

    }

    @Test
    void save() {
        AgeGroupDTO saved = ageGroupService.save(createDTO(AGE_GROUP_ADULT_TYPE));

        assertTrue(ageGroupService.existById(saved.getId()));

        AgeGroupDTO saved2 = ageGroupService.findById(saved.getId()).orElseThrow();

        assertEquals(AGE_GROUP_ADULT_TYPE, saved.getType());
        assertEquals(saved2.getType(), saved.getType());
    }

     @Test
    void saveAll() {
        List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();
        AgeGroupDTO ageGroupDTO4 = createDTO(AGE_GROUP_NAME_BABIES_TYPE);
        AgeGroupDTO ageGroupDTO5 = createDTO(AGE_GROUP_RETIREE_CHILD_TYPE);
        ageGroupsDTO.add(ageGroupDTO4);
        ageGroupsDTO.add(ageGroupDTO5);
        List<AgeGroupDTO> ageGroupDTOS = ageGroupService.saveAll(ageGroupsDTO);
        assertTrue(ageGroupService.existById(ageGroupDTOS.get(0).getId()));
        assertTrue(ageGroupService.existById(ageGroupDTOS.get(1).getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupDTOS.get(0).getType());
        assertEquals(ageGroupDTO5.getType(), ageGroupDTOS.get(1).getType());
    }

    @Test
    void findById() {
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
    }

    @Test
    void findAll() {
        assertEquals(3, ageGroupService.findAll().size());
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));
        assertNotNull(ageGroupService.findAll());
    }


    @Test
    void updateId() {
        AgeGroupDTO ageGroupDTO4 = createDTO(AGE_GROUP_NAME_BABIES_TYPE);

        assertTrue(ageGroupService.updateId(ageGroupDTO2.getId(), ageGroupDTO4));
        assertTrue(ageGroupService.existById(ageGroupDTO4.getId()));
        assertEquals(ageGroupDTO4.getType(), ageGroupService.findById(ageGroupDTO4.getId()).get().getType());
    }


    @Test
    void delete() {
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        ageGroupService.delete(ageGroupDTO1);
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
    }


    @Test
    void deleteAll() {
        List<AgeGroupDTO> ageGroupsDTODelete = List.of(ageGroupDTO1, ageGroupDTO2);

        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));

        ageGroupService.deleteAll(ageGroupsDTODelete);
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
        assertFalse(ageGroupService.existById(ageGroupDTO2.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));
    }
}
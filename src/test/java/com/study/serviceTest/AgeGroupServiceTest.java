package com.study.serviceTest;

import com.study.domain.AgeGroup;
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
 *
 */
public class AgeGroupServiceTest {

    private AgeGroupDTO ageGroupDTO1;
    private AgeGroupDTO ageGroupDTO2;
    private AgeGroupDTO ageGroupDTO3;

    private AgeGroupService ageGroupService;
    private AgeGroupMapper ageGroupMapper;
    private AgeGroupRepository ageGroupRepository;

    @BeforeEach
    void setUp() {
        ageGroupRepository = new AgeGroupRepository();
        ageGroupMapper = new AgeGroupMapper();

        ageGroupService = new AgeGroupService(ageGroupRepository, ageGroupMapper);

        ageGroupDTO1 = ageGroupService.save(new AgeGroupDTO().type("Інвалід"));
        ageGroupDTO2 = ageGroupService.save(new AgeGroupDTO().type("Військовий"));
        ageGroupDTO3 = ageGroupService.save(new AgeGroupDTO().type("Дитина"));
    }

    @AfterEach
    void tearDown() {
       ageGroupService.deleteAll();

    }

    @Test
    void save() {
        AgeGroupDTO saved = ageGroupService.save(new AgeGroupDTO().type("test"));

        assertTrue(ageGroupService.existById(saved.getId()));

        AgeGroupDTO saved2 = ageGroupService.findById(saved.getId()).orElseThrow();

        assertEquals("test", saved.getType());
        assertEquals(saved2.getType(), saved.getType());
    }

    @Test
    void saveAll() {
        List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();
        ageGroupsDTO.add(ageGroupDTO1);
        ageGroupsDTO.add(ageGroupDTO2);
        List<AgeGroupDTO> ageGroupDTOS = ageGroupService.saveAll(ageGroupsDTO);
        assertTrue(ageGroupService.existById(ageGroupDTOS.get(0).getId()));
        assertTrue(ageGroupService.existById(ageGroupDTOS.get(1).getId()));
    }

    @Test
    void findById() {
        ageGroupService.save(ageGroupDTO1);
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
    }

    @Test
    void findAll() {
        assertEquals(3, ageGroupService.findAll().size());
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));

    }


    @Test
    void updateId() {
        assertTrue(ageGroupService.updateId(ageGroupDTO2.getId(), ageGroupDTO3));
        assertTrue(ageGroupService.existById(ageGroupDTO3.getId()));
    }


    @Test
    void delete() {
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        ageGroupService.delete(ageGroupDTO1);
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
    }


    @Test
    void DeleteAll() {
        ageGroupService.save(ageGroupDTO1);
        ageGroupService.save(ageGroupDTO2);
        ageGroupService.save(ageGroupDTO3);

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
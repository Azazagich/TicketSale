package com.study.serviceTests;

import com.study.repository.AgeGroupRepository;
import com.study.service.AgeGroupService;
import com.study.service.dto.AgeGroupDTO;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeGroupServiceTest {

    private AgeGroupDTO ageGroupDTO1;
    private AgeGroupDTO ageGroupDTO2;
    private AgeGroupDTO ageGroupDTO3;

    private static AgeGroupService ageGroupService;

    @BeforeEach
    void setUp() {
        ageGroupService = new AgeGroupService();

        ageGroupDTO1 = new AgeGroupDTO().id(1).type("Інвалід");
        ageGroupDTO2 = new AgeGroupDTO().id(2).type("Військовий");
        ageGroupDTO3 = new AgeGroupDTO().id(3).type("Дитина");

//        List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();
//        ageGroupsDTO.add(ageGroupDTO1);
//        ageGroupsDTO.add(ageGroupDTO2);
//        ageGroupsDTO.add(ageGroupDTO3);
//        ageGroupService.saveAll(ageGroupsDTO);
    }

    @AfterEach
    void tearDown() {
       ageGroupService.deleteAll();
    }

    @Test
    void save() {
        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
        ageGroupService.save(ageGroupDTO1);
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertFalse(ageGroupService.existById(ageGroupDTO3.getId()));
    }

    @Test
    void saveAll() {
//        List<AgeGroupDTO> ageGroupsDTO = new ArrayList<>();
//        ageGroupsDTO.add(ageGroupDTO1);
//        ageGroupsDTO.add(ageGroupDTO2);
//        assertFalse(ageGroupService.existById(ageGroupDTO1.getId()));
//        ageGroupService.saveAll(ageGroupsDTO);
//        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
//        assertTrue(ageGroupService.existById(ageGroupDTO2.getId()));
    }

    @Test
    void findById() {
        ageGroupService.save(ageGroupDTO1);
        assertTrue(ageGroupService.existById(ageGroupDTO1.getId()));
        assertTrue(ageGroupService.findById(ageGroupDTO1.getId().get());
    }

    @Test
    void findAll() {
    }

    @Test
    void existById() {

    }

    @Test
    void updateId() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void testDeleteAll() {
    }
}
package com.study.service;

import com.study.service.dto.EconomyDTO;

import java.util.List;
import java.util.Optional;

public class EconomyService implements CrudService<EconomyDTO>{
    @Override
    public EconomyDTO save(EconomyDTO economyDTO) {
        return null;
    }

    @Override
    public List<EconomyDTO> saveAll(List<EconomyDTO> economyDTOS) {
        return List.of();
    }

    @Override
    public Optional<EconomyDTO> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<EconomyDTO> findAll() {
        return List.of();
    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }

    @Override
    public boolean updateId(Integer id, EconomyDTO newDTO) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(EconomyDTO economyDTO) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteAll(List<EconomyDTO> economyDTOS) {

    }
}

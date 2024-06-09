package com.study.repository;

import com.study.domain.AgeGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AgeGroupRepository implements CrudRepository<AgeGroup> {

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, AgeGroup> groups = new HashMap<>();

    //CRUD
    @Override
    public AgeGroup save(AgeGroup ageGroup) {
        if (ageGroup != null) {
            ageGroup.setId(++id);
            groups.put(id, ageGroup);
        }
        return ageGroup;
    }


    @Override
    public List<AgeGroup> saveAll(List<AgeGroup> ageGroups) {
        for (AgeGroup ageGroup : ageGroups){
            if (ageGroup != null) {
                ageGroup.setId(++id);
                groups.put(id, ageGroup);
            }
        }
        return ageGroups;
    }


    @Override
    public Optional<AgeGroup> findById(Integer id){
        return Optional.of(groups.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && groups.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, AgeGroup nwAgeGroup){
        if (nwAgeGroup != null){
            nwAgeGroup.setId(id);
            groups.put(id, nwAgeGroup);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id){
        if (id != null){
            groups.remove(id);
        }
    }


    @Override
    public void delete(AgeGroup ageGroup){
        if (ageGroup != null){
            deleteById(ageGroup.getId());
        }
    }


    @Override
    public void deleteAll(){
        groups.clear();
    }


    @Override
    public void deleteAll(List<AgeGroup> ageGroups) {
        if (ageGroups != null) {
            for (AgeGroup ageGroup : ageGroups) {
                if (ageGroup != null) {
                    deleteById(ageGroup.getId());
                }
            }
        }
    }

}

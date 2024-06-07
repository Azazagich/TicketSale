package com.study.repository;

import com.study.domain.AgeGroup;

import java.util.HashMap;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AgeGroupRepository {

    private static Integer id = 0;

    // private static Logger LOGGER = LogManager.getLogger();

    HashMap<Integer, AgeGroup> groups = new HashMap<>();

    //CRUD

    //boolean
    public AgeGroup save(AgeGroup ageGroup) {
        if (ageGroup != null) {
            ageGroup.setId(id++);
            groups.put(id, ageGroup);
            return ageGroup;
        }
        return new AgeGroup();
    }

    public Optional<AgeGroup> findById(Integer id){
        Optional<AgeGroup> ageGroup;
        if (id != null && groups.containsKey(id)) {
            return Optional.of(groups.get(id));
        }
        return Optional.empty();
    }

    public boolean existById(Integer id){
            return id != null && groups.containsKey(id);
    }

    public boolean updateById(Integer id, AgeGroup nwAgeGroup){
        if (nwAgeGroup != null && groups.containsKey(id)){
            nwAgeGroup.setId(id);
            groups.put(id, nwAgeGroup);
            return true;
        }
        return false;
    }

    public void deleteById(Integer id){
        if (groups.containsKey(id) && id != null) {
            groups.remove(id);
        }
    }

    public void delete(AgeGroup ageGroup){
        if (groups.containsKey(id) && id != null && ageGroup != null) {
            groups.remove(ageGroup.getId(), ageGroup);
        }
    }

    public void deleteAll(){
        groups.clear();
    }
}

/////////////////////////////////////////////////////////////////////
//
//    public AgeGroup readAgeGroup(int id){
//        for (AgeGroup ageGroup : groups){
//            if (ageGroup.getId() == id){
//                LOGGER.debug(ageGroup);
//                return ageGroup;
//            }
//        }
//        return new AgeGroup();
//    }
//
//
//    public boolean updateAgeGroup(int id, String newType){
//        for (AgeGroup ageGroup : groups){
//            if (ageGroup.getId() == id){
//                ageGroup.setType(newType);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean deleteAgeGroup(int id){
//        for (AgeGroup ageGroup : groups){
//            if (ageGroup.getId() == id){
//                groups.remove(ageGroup);
//                return true;
//            }
//        }
//        return false;
//    }
//}

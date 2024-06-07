package com.study.repository;

import com.study.domain.AgeGroup;

import java.util.HashMap;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AgeGroupRepository /*implements CrudRepository<AgeGroup>*/ {

    private static Integer id = 0;

    private static Logger LOGGER = LogManager.getLogger();

    private static final HashMap<Integer, AgeGroup> groups = new HashMap<>();

    //CRUD

    public AgeGroup save(AgeGroup ageGroup) {
        if (ageGroup != null) {
            ageGroup.setId(++id);
            groups.put(id, ageGroup);
        }
        return ageGroup;
    }

    public Optional<AgeGroup> findById(Integer id){
        return Optional.of(groups.get(id));
    }

    //@Override
    public boolean existById(Integer id){
        return groups.containsKey(id);
    }

    public boolean updateById(Integer id, AgeGroup nwAgeGroup){
        if (nwAgeGroup != null){
            nwAgeGroup.setId(id);
            groups.put(id, nwAgeGroup);
            return true;
        }
        return false;
    }

    public void deleteById(Integer id){
            groups.remove(id);
    }

    public void delete(AgeGroup ageGroup){
            groups.remove(ageGroup.getId(), ageGroup);
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

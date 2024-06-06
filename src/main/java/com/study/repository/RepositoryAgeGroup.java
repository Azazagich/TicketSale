package com.study.repository;

import com.study.domain.AgeGroup;
import java.util.HashSet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class RepositoryAgeGroup {

    private static Logger LOGGER = LogManager.getLogger();
    //CRUD
    HashSet<AgeGroup> groups = new HashSet<>();

    public boolean createAgeGroup(int id, String type){
        Optional<AgeGroup> ageGroup = Optional.of(new AgeGroup(id, type));
        if (ageGroup.isPresent()){
            groups.add(ageGroup.get());
            return true;
        }
        return false;
    }

    public AgeGroup readAgeGroup(int id){
        for (AgeGroup ageGroup : groups){
            if (ageGroup.getId() == id){
                LOGGER.debug(ageGroup);
                return ageGroup;
            }
        }
        return new AgeGroup();
    }


    public boolean updateAgeGroup(int id, String newType){
        for (AgeGroup ageGroup : groups){
            if (ageGroup.getId() == id){
                ageGroup.setType(newType);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAgeGroup(int id){
        for (AgeGroup ageGroup : groups){
            if (ageGroup.getId() == id){
                groups.remove(ageGroup);
                return true;
            }
        }
        return false;
    }
}


//    public void createAgeGroup(Optional<AgeGroup> ageGroup){
//        if (ageGroup.isPresent())
//            groups.add(ageGroup);
//    }

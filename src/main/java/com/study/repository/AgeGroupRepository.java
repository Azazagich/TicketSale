package com.study.repository;

import com.study.domain.AgeGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Repository implementation for managing AgeGroup entities.
 * */
public class AgeGroupRepository implements CrudRepository<AgeGroup> {

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for AgeGroup entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for AgeGroup entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, AgeGroup> groups = new HashMap<>();

    /**
     * Saves a single AgeGroup entity.
     * @param ageGroup | The AgeGroup entity to be saved.
     * @return The saved AgeGroup entity.
     * */
    @Override
    public AgeGroup save(AgeGroup ageGroup) {
        if (ageGroup != null) {
            //TODO
            ageGroup.setId(++id);
            groups.put(id, ageGroup);
            LOGGER.debug("Saved AgeGroup with id {}", id);
        }
        return ageGroup;
    }

    /**
     * Saves a list of AgeGroup entities.
     * @param ageGroups | The list of AgeGroup entities to be saved.
     * @return The list of saved AgeGroup entities.
     * */
    @Override
    public List<AgeGroup> saveAll(List<AgeGroup> ageGroups) {
        for (AgeGroup ageGroup : ageGroups){
            if (ageGroup != null) {
                ageGroup.setId(++id);
                groups.put(id, ageGroup);
                LOGGER.debug("Saved AgeGroup with id {}", id);
            }
        }
        return ageGroups;
    }


    /**
     * Retrieves an AgeGroup entity by its identifier.
     * @param id | The identifier of the AgeGroup entity to be retrieved.
     * @return An optional containing the retrieved AgeGroup entity, or empty if not found.
     * */
    @Override
    public Optional<AgeGroup> findById(Integer id){
        LOGGER.debug("Finding AgeGroup with id {}", id);

        return Optional.ofNullable(groups.get(id));
    }

    /**
     * Checks if an AgeGroup entity with the given identifier exists.
     * @param id The identifier of the AgeGroup entity to check.
     * @return true if the AgeGroup entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && groups.containsKey(id);
        LOGGER.debug("Existence check for AgeGroup with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of an AgeGroup entity.
     * @param id The old identifier of the AgeGroup entity.
     * @param nwAgeGroup The AgeGroup entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, AgeGroup nwAgeGroup){
        if (nwAgeGroup != null && id != null){
            groups.remove(id);
            groups.put(nwAgeGroup.getId(), nwAgeGroup);
            LOGGER.debug("Updated AgeGroup with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update AgeGroup with id {}", id);
        return false;
    }

    /**
     * Deletes an AgeGroup entity by its identifier.
     * @param id The identifier of the AgeGroup entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            groups.remove(id);
            LOGGER.debug("Deleted AgeGroup with id {}", id);
        }
    }

    /**
     * Deletes a single AgeGroup entity.
     * @param ageGroup The AgeGroup entity to be deleted.
     * */
    @Override
    public void delete(AgeGroup ageGroup){
        if (ageGroup != null){
            deleteById(ageGroup.getId());
            LOGGER.debug("Deleted AgeGroup: {}", ageGroup);
        }
    }

    /**
     * Deletes all AgeGroup entities.
     * */
    @Override
    public void deleteAll(){
        groups.clear();
        LOGGER.debug("Deleted all AgeGroups");
    }

    /**
     * Deletes a list of AgeGroup entities.
     * @param ageGroups The list of AgeGroup entities to be deleted.
     * */
    @Override
    public void deleteAll(List<AgeGroup> ageGroups) {
        if (ageGroups != null) {
            for (AgeGroup ageGroup : ageGroups) {
                if (ageGroup != null) {
                    deleteById(ageGroup.getId());
                    LOGGER.debug("Deleted ageGroup with id {}", ageGroup.getId());
                }
            }
        }
    }

}

package com.study.repository;

import com.study.domain.Economy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation for managing Economy entities.
 * */
public class EconomyRepository implements CrudRepository<Economy>{

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for Economy entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for Economy entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, Economy> classEconomies = new HashMap<>();

    /**
     * Saves a single Economy entity.
     * @param economy The Economy entity to be saved.
     * @return The saved Economy entity.
     * */
    @Override
    public Economy save(Economy economy) {
        if (economy != null) {
            economy.setId(++id);
            classEconomies.put(id, economy);
            LOGGER.info("Saved Economy with id {}", id);
        }
        return economy;
    }

    /**
     * Saves a list of Economy entities.
     * @param economies The list of Economy entities to be saved.
     * @return The list of saved Economy entities.
     * */
    @Override
    public List<Economy> saveAll(List<Economy> economies) {
        for (Economy economy : economies){
            if (economy != null) {
                economy.setId(++id);
                classEconomies.put(id, economy);
                LOGGER.info("Saved Economy with id {}", id);
            }
        }
        return economies;
    }

    /**
     * Retrieves an Economy entity by its identifier.
     * @param id The identifier of the Economy entity to be retrieved.
     * @return An optional containing the retrieved Economy entity, or empty if not found.
     * */
    @Override
    public Optional<Economy> findById(Integer id){
        LOGGER.info("Finding Economy with id {}", id);
        return Optional.of(classEconomies.get(id));
    }

    /**
     * Checks if an Economy entity with the given identifier exists.
     * @param id The identifier of the Economy entity to check.
     * @return true if the Economy entity exist, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && classEconomies.containsKey(id);
        LOGGER.info("Existence check for Economy with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of an Economy entity.
     * @param id The current identifier of the Economy entity.
     * @param nwEconomy The Economy entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, Economy nwEconomy){
        if (nwEconomy != null){
            nwEconomy.setId(id);
            classEconomies.put(id, nwEconomy);
            LOGGER.info("Updated Economy with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update Economy with id {}", id);
        return false;
    }

    /**
     * Deletes an Economy entity by its identifier.
     * @param id The identifier of the Economy entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            classEconomies.remove(id);
            LOGGER.info("Deleted Economy with id {}", id);
        }
    }

    /**
     * Deletes a single Economy entity.
     * @param economy The Economy entity to be deleted.
     * */
    @Override
    public void delete(Economy economy){
        if (economy != null){
            deleteById(economy.getId());
            LOGGER.info("Deleted Economy: {}", economy);
        }
    }

    /**
     * Deletes all Economy entities.
     * */
    @Override
    public void deleteAll(){
        classEconomies.clear();
        LOGGER.info("Deleted all Economies");
    }

    /**
     * Deletes a list of Economy entities.
     * @param economies The list of Economy entities to be deleted.
     * */
    @Override
    public void deleteAll(List<Economy> economies) {
        if (economies != null) {
            for (Economy economy : economies) {
                if (economy != null) {
                    deleteById(economy.getId());
                    LOGGER.info("Deleted Economy with id {}", economy.getId());
                }
            }
        }
    }

}

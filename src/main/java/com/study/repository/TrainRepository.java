package com.study.repository;

import com.study.domain.Station;
import com.study.domain.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * Repository implementation for managing Train entities.
 */
public class TrainRepository implements CrudRepository<Train> {

    /**
     * Logger for this class.
     */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for Train entities.
     */
    private static Integer id = 0;

    /**
     * Storage for Train entities, using a HashMap with IDs as keys.
     */
    private static final HashMap<Integer, Train> trains = new HashMap<>();

    /**
     * Saves a single Train entity.
     * @param train The Train entity to be saved.
     * @return The saved Train entity.
     * */
    @Override
    public Train save(Train train) {
        if (train != null) {
            train.setId(++id);
            trains.put(id, train);
            LOGGER.debug("Saved Train with id {}", id);
        }
        return train;
    }

    /**
     * Saves a list of Train entities.
     * @param trains The list of Train entities to be saved.
     * @return The list of saved Train entities.
     * */
    @Override
    public List<Train> saveAll(List<Train> trains) {
        for (Train train : trains){
            if (train != null) {
                train.setId(++id);
                this.trains.put(id, train);
                LOGGER.debug("Saved Train with id {}", id);
            }
        }
        return trains;
    }

    /**
     * Retrieves a Train entity by its identifier.
     * @param id The identifier of the Train entity to be retrieved.
     * @return An optional containing the retrieved Train entity, or empty if not found.
     * */
    @Override
    public Optional<Train> findById(Integer id){
        LOGGER.debug("Finding Train with id {}", id);
        return Optional.of(trains.get(id));
    }

    /**
     * Checks if a Train entity with the given identifier exists.
     * @param id The identifier of the Train entity to check.
     * @return true if the Train entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && trains.containsKey(id);
        LOGGER.debug("Existence check for Train with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of a Train entity.
     * @param id The current identifier of the Train entity.
     * @param nwTrain The Train entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, Train nwTrain){
        if (nwTrain != null){
            trains.remove(id);
            trains.put(nwTrain.getId(), nwTrain);
            LOGGER.debug("Updated Train with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update Train with id {}", id);
        return false;
    }

    /**
     * Deletes a Train entity by its identifier.
     * @param id The identifier of the Train entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            trains.remove(id);
            LOGGER.debug("Deleted Train with id {}", id);
        }
    }

    /**
     * Deletes a single Train entity.
     * @param train The Train entity to be deleted.
     */
    @Override
    public void delete(Train train){
        if (train != null){
            deleteById(train.getId());
            LOGGER.debug("Deleted Train: {}", train);
        }
    }

    /**
     * Deletes all Train entities.
     */
    @Override
    public void deleteAll(){
        trains.clear();
        LOGGER.debug("Deleted all Trains");
    }


    /**
     * Deletes a list of Train entities.
     * @param trains The list of Train entities to be deleted.
     */
    @Override
    public void deleteAll(List<Train> trains) {
        if (trains != null) {
            for (Train train : trains) {
                if (train != null) {
                    deleteById(train.getId());
                    LOGGER.debug("Deleted Train with id {}", train.getId());
                }
            }
        }
    }

}

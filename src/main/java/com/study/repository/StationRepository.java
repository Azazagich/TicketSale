package com.study.repository;

import com.study.domain.Economy;
import com.study.domain.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation for managing Station entities.
 * */
public class StationRepository implements CrudRepository<Station>{

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for Station entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for Station entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, Station> stations = new HashMap<>();

    /**
     * Saves a single Station entity.
     * @param station The Station entity to be saved.
     * @return The saved Station entity.
     * */
    @Override
    public Station save(Station station) {
        if (station != null) {
            station.setId(++id);
            stations.put(id, station);
            LOGGER.debug("Saved Station with id {}", id);
        }
        return station;
    }

    /**
     * Saves a list of Station entities.
     * @param stations The list of Station entities to be saved.
     * @return The list of saved Station entities.
     * */
    @Override
    public List<Station> saveAll(List<Station> stations) {
        for (Station station : stations){
            if (station != null) {
                station.setId(++id);
                this.stations.put(id, station);
                LOGGER.debug("Saved Station with id {}", id);
            }
        }
        return stations;
    }

    /**
     * Retrieves a Station entity by its identifier.
     * @param id The identifier of the Station entity to be retrieved.
     * @return An optional containing the retrieved Station entity, or empty if not found.
     * */
    @Override
    public Optional<Station> findById(Integer id){
        LOGGER.debug("Finding Station with id {}", id);
        return Optional.of(stations.get(id));
    }

    /**
     * Retrieves all Station objects from the repository.
     * This method returns a list containing all the Station objects that are currently stored
     * in the repository.
     * @return a list of all Station objects in the repository.
     * */
    @Override
    public List<Station> findAll() {
        return stations.values().stream().toList();
    }

    /**
     * Checks if a Station entity with the given identifier exists.
     * @param id The identifier of the Station entity to check.
     * @return true if the Station entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && stations.containsKey(id);
        LOGGER.debug("Existence check for Station with id {}: {}", id, exist);
        return exist;
    }


    /**
     * Updates the identifier of a Station entity.
     * @param id The current identifier of the Station entity.
     * @param nwStation The Station entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, Station nwStation){
        if (nwStation != null && id != null){
            stations.remove(id);
            stations.put(nwStation.getId(), nwStation);
            LOGGER.debug("Updated Station with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update Station with id {}", id);
        return false;
    }

    /**
     * Deletes a Station entity by its identifier.
     * @param id The identifier of the Station entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            stations.remove(id);
            LOGGER.debug("Deleted Station with id {}", id);
        }
    }

    /**
     * Deletes a single Station entity.
     * @param station The Station entity to be deleted.
     * */
    @Override
    public void delete(Station station){
        if (station != null){
            deleteById(station.getId());
            LOGGER.debug("Deleted Station: {}", station);
        }
    }

    /**
     * Deletes all Station entities.
     * */
    @Override
    public void deleteAll(){
        stations.clear();
        LOGGER.debug("Deleted all Stations");
    }

    /**
     * Deletes a list of Station entities.
     * @param stations The list of Station entities to be deleted.
     * */
    @Override
    public void deleteAll(List<Station> stations) {
        if (stations != null) {
            for (Station station : stations) {
                if (station != null) {
                    deleteById(station.getId());
                    LOGGER.debug("Deleted Station with id {}", station.getId());
                }
            }
        }
    }

}

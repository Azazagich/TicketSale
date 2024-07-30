package com.study.service;

import com.study.repository.StationRepository;
import com.study.service.dto.StationDTO;
import com.study.service.mapper.StationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing {@link StationDTO} entities.
 * Implements {@link CrudService} to provide CRUD operations for StationDTO objects.
 */
public class StationService implements CrudService<StationDTO>{

    /**
     * Repository for performing CRUD operations on Station entities.
     */
    private final StationRepository stationRepository;

    /**
     * Mapper for converting between Station entities and StationDTOs.
     */
    private final StationMapper stationMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public StationService(){
        this(new StationRepository(), new StationMapper());
    }

    public StationService(StationRepository stationRepository, StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }

    /**
     * Saves a StationDTO entity.
     *
     * @param stationDTO The StationDTO object to save.
     * @return The saved StationDTO object.
     */
    @Override
    public StationDTO save(StationDTO stationDTO) {
        LOGGER.debug("Saving StationDTO: {}", stationDTO);
        return stationMapper.toDTO(stationRepository.save(stationMapper.toEntity(stationDTO)));
    }

    /**
     * Saves a list of StationDTO entities.
     *
     * @param stationsDTO The list of StationDTO objects to save.
     * @return The list of saved StationDTO objects.
     */
    @Override
    public List<StationDTO> saveAll(List<StationDTO> stationsDTO) {
        LOGGER.debug("Saving all StationsDTO");
        return stationMapper.toDTO(stationRepository.saveAll(stationMapper.toEntity(stationsDTO)));
    }

    /**
     * Finds a StationDTO entity by its ID.
     *
     * @param id The ID of the StationDTO entity to find.
     * @return An Optional containing the found StationDTO object, or empty if not found.
     */
    @Override
    public Optional<StationDTO> findById(Integer id) {
        LOGGER.debug("Find StationDTO by id {}", id);
        return stationMapper.toDTO(stationRepository.findById(id));
    }

    /**
     * Retrieves all StationDTO entities.
     *
     * @return A list of all StationDTO objects.
     */
    @Override
    public List<StationDTO> findAll() {
        LOGGER.debug("Find All StationsDTO elements");
        return stationMapper.toDTO(stationRepository.findAll());
    }

    /**
     * Checks if a StationDTO entity exists by its ID.
     *
     * @param id The ID of the StationDTO entity to check.
     * @return true if the StationDTO exists, false otherwise.
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of Station by ID: {}", id);
        return stationRepository.existById(id);
    }

    /**
     * Updates a StationDTO entity with a new DTO object.
     *
     * @param id           The ID of the StationDTO entity to update.
     * @param newStationDTO The new StationDTO object to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateId(Integer id, StationDTO newStationDTO) {
        LOGGER.debug("Updating Station with ID: {}", id);
        if (id != null && newStationDTO != null){
            return stationRepository.updateId(id, stationMapper.toEntity(newStationDTO));
        }
        return false;
    }

    /**
     * Deletes a StationDTO entity by its ID.
     *
     * @param id The ID of the StationDTO entity to delete.
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting Station by ID: {}", id);
        stationRepository.deleteById(id);
    }

    /**
     * Deletes a StationDTO entity.
     *
     * @param stationDTO The StationDTO object to delete.
     */
    @Override
    public void delete(StationDTO stationDTO) {
        LOGGER.debug("Deleting Station: {}", stationDTO);
        stationRepository.delete(stationMapper.toEntity(stationDTO));
    }

    /**
     * Deletes all StationDTO entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Stations");
        stationRepository.deleteAll();
    }

    /**
     * Deletes a list of StationDTO entities.
     *
     * @param stationsDTO The list of StationDTO objects to delete.
     */
    @Override
    public void deleteAll(List<StationDTO> stationsDTO) {
        LOGGER.debug("Deleting all Stations list");
        stationRepository.deleteAll(stationMapper.toEntity(stationsDTO));
    }
}

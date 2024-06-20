package com.study.service;

import com.study.repository.DiscountRepository;
import com.study.repository.TrainRepository;
import com.study.service.dto.TrainDTO;
import com.study.service.mapper.DiscountMapper;
import com.study.service.mapper.TrainMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing {@link TrainDTO} entities.
 * Implements {@link CrudService} to provide CRUD operations for TrainDTO objects.
 */
public class TrainService implements CrudService<TrainDTO>{

    /**
     * Repository for performing CRUD operations on Train entities.
     */
    private final TrainRepository trainRepository;

    /**
     * Mapper for converting between Trains entities and TrainsDTO.
     */
    private final TrainMapper trainMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public TrainService(){
        this(new TrainRepository(), new TrainMapper());
    }

    public TrainService(TrainRepository trainRepository, TrainMapper trainMapper) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
    }

    /**
     * Saves a TrainDTO entity.
     *
     * @param trainDTO The TrainDTO object to save.
     * @return The saved TrainDTO object.
     */
    @Override
    public TrainDTO save(TrainDTO trainDTO) {
        LOGGER.debug("Saving TrainDTO: {}", trainDTO);
        return trainMapper.toDTO(trainRepository.save(trainMapper.toEntity(trainDTO)));
    }

    /**
     * Saves a list of TrainDTO entities.
     *
     * @param trainsDTO The list of TrainDTO objects to save.
     * @return The list of saved TrainDTO objects.
     */
    @Override
    public List<TrainDTO> saveAll(List<TrainDTO> trainsDTO) {
        LOGGER.debug("Saving all TrainsDTO");
        return trainMapper.toDTO(trainRepository.saveAll(trainMapper.toEntity(trainsDTO)));
    }

    /**
     * Finds a TrainDTO entity by its ID.
     *
     * @param id The ID of the TrainDTO entity to find.
     * @return An Optional containing the found TrainDTO object, or empty if not found.
     */
    @Override
    public Optional<TrainDTO> findById(Integer id) {
        LOGGER.debug("Find TrainDTO by id {}", id);
        return trainMapper.toDTO(trainRepository.findById(id));
    }

    /**
     * Retrieves all TrainDTO entities.
     *
     * @return A list of all TrainDTO objects.
     */
    @Override
    public List<TrainDTO> findAll() {
        LOGGER.debug("Find All TrainsDTO elements");
        return trainMapper.toDTO(trainRepository.findAll());
    }

    /**
     * Checks if a TrainDTO entity exists by its ID.
     *
     * @param id The ID of the TrainDTO entity to check.
     * @return true if the TrainDTO exists, false otherwise.
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of Train by ID: {}", id);
        return trainRepository.existById(id);
    }

    /**
     * Updates a TrainDTO entity with a new DTO object.
     *
     * @param id           The ID of the TrainDTO entity to update.
     * @param nwTrainDTO The new TrainDTO object to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateId(Integer id, TrainDTO nwTrainDTO) {
        LOGGER.debug("Updating Train with ID: {}", id);
        if (id != null && nwTrainDTO != null){
            return trainRepository.updateId(id, trainMapper.toEntity(nwTrainDTO));
        }
        return false;
    }

    /**
     * Deletes a TrainDTO entity by its ID.
     *
     * @param id The ID of the TrainDTO entity to delete.
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting Train by ID: {}", id);
        trainRepository.deleteById(id);
    }

    /**
     * Deletes a TrainDTO entity.
     *
     * @param trainDTO The TrainDTO object to delete.
     */
    @Override
    public void delete(TrainDTO trainDTO) {
        LOGGER.debug("Deleting Train: {}", trainDTO);
        trainRepository.delete(trainMapper.toEntity(trainDTO));
    }

    /**
     * Deletes all TrainDTO entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Trains");
        trainRepository.deleteAll();
    }

    /**
     * Deletes a list of TrainDTO entities.
     *
     * @param trainsDTO The list of TrainDTO objects to delete.
     */
    @Override
    public void deleteAll(List<TrainDTO> trainsDTO) {
        LOGGER.debug("Deleting all Trains list");
        trainRepository.deleteAll(trainMapper.toEntity(trainsDTO));
    }
}

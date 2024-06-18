package com.study.service;

import com.study.repository.AgeGroupRepository;
import com.study.service.dto.AgeGroupDTO;
import com.study.service.mapper.AgeGroupMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing AgeGroupDTO entities.
 * Provides methods for CRUD operations and other business logic related to AgeGroups.
 */
public class AgeGroupService {

    /**
     * Repository for performing CRUD operations on AgeGroup entities.
     */
    private final AgeGroupRepository ageGroupRepository;

    /**
     * Mapper for converting between AgeGroup entities and AgeGroupDTOs.
     */
    private final AgeGroupMapper ageGroupMapper;

    /**
     * Logger for logging service operations.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    public AgeGroupService(){
        this(new AgeGroupRepository(), new AgeGroupMapper());
    }

    public AgeGroupService(AgeGroupRepository ageGroupRepository, AgeGroupMapper ageGroupMapper) {
        this.ageGroupRepository = ageGroupRepository;
        this.ageGroupMapper = ageGroupMapper;
    }

    /**
     * Saves a new AgeGroup entity based on the provided AgeGroupDTO.
     *
     * @param ageGroupDTO the AgeGroupDTO containing the details of the AgeGroup to be saved
     * @return the saved AgeGroup entity
     */
    public AgeGroupDTO save(AgeGroupDTO ageGroupDTO){
        LOGGER.debug("Saving AgeGroupDTO: {}", ageGroupDTO);
        return ageGroupMapper.toDTO(ageGroupRepository.save(ageGroupMapper.toEntity(ageGroupDTO)));
    }

    /**
     * Saves a list of AgeGroupDTOs.
     *
     * @param ageGroupsDTO the list of AgeGroupDTOs to be saved
     * @return the list of saved AgeGroup entities
     */
    public List<AgeGroupDTO> saveAll(List<AgeGroupDTO> ageGroupsDTO){
        LOGGER.debug("Saving all AgeGroupsDTO");
        return ageGroupMapper.toDTO(ageGroupRepository.saveAll(ageGroupMapper.toEntity(ageGroupsDTO)));
    }

    /**
     * Finds an AgeGroup by its ID and returns an optional AgeGroupDTO.
     *
     * @param id the ID of the AgeGroup to be found
     * @return an Optional containing the AgeGroupDTO if found, or empty if not found
     */
    public Optional<AgeGroupDTO> findById(Integer id){
        LOGGER.debug("Find AgeGroupDTO by id {}", id);
        return ageGroupMapper.toDTO(ageGroupRepository.findById(id));
    }

    /**
     * Finds all AgeGroups and returns a list of AgeGroupDTOs.
     *
     * @return the list of AgeGroupDTOs
     */
    public List<AgeGroupDTO> findAll(){
        LOGGER.debug("Find All AgeGroupsDTO elements");
        return ageGroupMapper.toDTO(ageGroupRepository.findAll());
    }

    /**
     * Checks if an AgeGroup exists by its ID.
     *
     * @param id the ID of the AgeGroup to be checked
     * @return true if the AgeGroup exists, false otherwise
     */
    public boolean existById(Integer id){
        LOGGER.debug("Checking existence of AgeGroup by ID: {}", id);
        return ageGroupRepository.existById(id);
    }

    /**
     * Updates an AgeGroup identified by its ID with new values from a given AgeGroupDTO.
     *
     * @param id the ID of the AgeGroup to be updated
     * @param nwAgeGroupDTO the AgeGroupDTO containing new values
     * @return true if the update was successful, false otherwise
     */
    public boolean updateId(Integer id, AgeGroupDTO nwAgeGroupDTO){
        LOGGER.debug("Updating AgeGroup with ID: {}", id);
        if (id != null && nwAgeGroupDTO != null){
           return ageGroupRepository.updateId(id, ageGroupMapper.toEntity(nwAgeGroupDTO));
        }
        return false;
    }

    /**
     * Deletes an AgeGroup by its ID.
     *
     * @param id the ID of the AgeGroup to be deleted
     */
    public void deleteById(Integer id){
        LOGGER.debug("Deleting AgeGroup by ID: {}", id);
        ageGroupRepository.deleteById(id);
    }

    /**
     * Deletes an AgeGroup based on the provided AgeGroupDTO.
     *
     * @param ageGroupDTO the AgeGroupDTO of the AgeGroup to be deleted
     */
    public void delete(AgeGroupDTO ageGroupDTO){
        LOGGER.debug("Deleting AgeGroup: {}", ageGroupDTO);
        ageGroupRepository.delete(ageGroupMapper.toEntity(ageGroupDTO));
    }

    /**
     * Deletes all AgeGroups.
     */
    public void deleteAll(){
        LOGGER.debug("Deleting all AgeGroups");
        ageGroupRepository.deleteAll();
    }

    /**
     * Deletes a list of AgeGroups based on the provided list of AgeGroupDTOs.
     *
     * @param ageGroupsDTO the list of AgeGroupDTOs of the AgeGroups to be deleted
     */
    public void deleteAll(List<AgeGroupDTO> ageGroupsDTO){
        LOGGER.debug("Deleting all AgeGroups list");
        ageGroupRepository.deleteAll(ageGroupMapper.toEntity(ageGroupsDTO));
    }

}
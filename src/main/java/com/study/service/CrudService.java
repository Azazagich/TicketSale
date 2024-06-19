package com.study.service;

import com.study.service.dto.AgeGroupDTO;

import java.util.List;
import java.util.Optional;

/**
 * A generic interface for CRUD operations on entities of type {@link DTO}.
 * Provides methods to save, find, check existence, update, and delete entities.
 *
 * @param <DTO> the type of the data transfer object (DTO) that this service handles
 */
public interface CrudService<DTO> {

    /**
     * Saves a given DTO entity.
     *
     * @param dto the DTO entity to save
     * @return the saved DTO entity
     */
    DTO save (DTO dto);

    /**
     * Saves all given DTO entities.
     *
     * @param dtos the list of DTO entities to save
     * @return the list of saved DTO entities
     */
    List<DTO> saveAll(List<DTO> dtos);

    /**
     * Finds a DTO entity by its ID.
     *
     * @param id the ID of the DTO entity to find
     * @return an {@link Optional} containing the found DTO entity, or empty if not found
     */
    Optional<DTO> findById(Integer id);

    /**
     * Finds all DTO entities.
     *
     * @return the list of all DTO entities
     */
    List<DTO> findAll();

    /**
     * Checks if a DTO entity exists by its ID.
     *
     * @param id the ID to check for existence
     * @return true if the DTO entity exists, false otherwise
     */
    boolean existById(Integer id);

    /**
     * Updates the ID of a given DTO entity.
     *
     * @param id the ID of the DTO entity to update
     * @param newDTO the new DTO entity with the updated ID
     * @return true if the update was successful, false otherwise
     */
    boolean updateId(Integer id, DTO newDTO);

    /**
     * Deletes a DTO entity by its ID.
     *
     * @param id the ID of the DTO entity to delete
     */
    void deleteById(Integer id);

    /**
     * Deletes a given DTO entity.
     *
     * @param dto the DTO entity to delete
     */
    void delete(DTO dto);

    /**
     * Deletes all DTO entities.
     */
    void deleteAll();

    /**
     * Deletes all given DTO entities.
     *
     * @param dtos the list of DTO entities to delete
     */
    void deleteAll(List<DTO> dtos);
}

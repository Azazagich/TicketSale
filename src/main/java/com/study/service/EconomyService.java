package com.study.service;

import com.study.repository.EconomyRepository;
import com.study.service.dto.EconomyDTO;
import com.study.service.mapper.EconomyMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing {@link EconomyDTO} entities.
 * Implements {@link CrudService} to provide CRUD operations for EconomyDTO objects.
 */
public class EconomyService implements CrudService<EconomyDTO>{

    /**
     * Repository for performing CRUD operations on Economy entities.
     */
    private final EconomyRepository economyRepository;

    /**
     * Mapper for converting between Economy entities and EconomyDTO.
     */
    private final EconomyMapper economyMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public EconomyService(){
        this(new EconomyRepository(), new EconomyMapper());
    }

    public EconomyService(EconomyRepository economyRepository, EconomyMapper economyMapper) {
        this.economyRepository = economyRepository;
        this.economyMapper = economyMapper;
    }

    /**
     * Saves an EconomyDTO entity.
     *
     * @param economyDTO The EconomyDTO object to save.
     * @return The saved EconomyDTO object.
     */
    @Override
    public EconomyDTO save(EconomyDTO economyDTO) {
        LOGGER.debug("Saving EconomyDTO: {}", economyDTO);
        return economyMapper.toDTO(economyRepository.save(economyMapper.toEntity(economyDTO)));
    }

    /**
     * Saves a list of EconomyDTO entities.
     *
     * @param economiesDTO The list of EconomyDTO objects to save.
     * @return The list of saved EconomyDTO objects.
     */
    @Override
    public List<EconomyDTO> saveAll(List<EconomyDTO> economiesDTO) {
        LOGGER.debug("Saving all EconomyDTO");
        return economyMapper.toDTO(economyRepository.saveAll(economyMapper.toEntity(economiesDTO)));
    }

    /**
     * Finds an EconomyDTO entity by its ID.
     *
     * @param id The ID of the EconomyDTO entity to find.
     * @return An Optional containing the found EconomyDTO object, or empty if not found.
     */
    @Override
    public Optional<EconomyDTO> findById(Integer id) {
        LOGGER.debug("Find EconomyDTO by id {}", id);
        return economyMapper.toDTO(economyRepository.findById(id));
    }

    /**
     * Retrieves all EconomyDTO entities.
     *
     * @return A list of all EconomyDTO objects.
     */
    @Override
    public List<EconomyDTO> findAll() {
        LOGGER.debug("Find All EconomiesDTO elements");
        return economyMapper.toDTO(economyRepository.findAll());
    }

    /**
     * Checks if an EconomyDTO entity exists by its ID.
     *
     * @param id The ID of the EconomyDTO entity to check.
     * @return true if the EconomyDTO exists, false otherwise.
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of Economy by ID: {}", id);
        return economyRepository.existById(id);
    }

    /**
     * Updates an EconomyDTO entity with a new DTO object.
     *
     * @param id           The ID of the EconomyDTO entity to update.
     * @param nwEconomyDTO The new EconomyDTO object to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateId(Integer id, EconomyDTO nwEconomyDTO) {
        LOGGER.debug("Updating Economy with ID: {}", id);
        if (id != null && nwEconomyDTO != null){
            return economyRepository.updateId(id, economyMapper.toEntity(nwEconomyDTO));
        }
        return false;
    }

    /**
     * Deletes an EconomyDTO entity by its ID.
     *
     * @param id The ID of the EconomyDTO entity to delete.
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting Economy by ID: {}", id);
        economyRepository.deleteById(id);
    }

    /**
     * Deletes an EconomyDTO entity.
     *
     * @param economyDTO The EconomyDTO object to delete.
     */
    @Override
    public void delete(EconomyDTO economyDTO) {
        LOGGER.debug("Deleting Economy: {}", economyDTO);
        economyRepository.delete(economyMapper.toEntity(economyDTO));
    }

    /**
     * Deletes all EconomyDTO entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Economies");
        economyRepository.deleteAll();
    }

    /**
     * Deletes a list of EconomyDTO entities.
     *
     * @param economiesDTO The list of EconomyDTO objects to delete.
     */
    @Override
    public void deleteAll(List<EconomyDTO> economiesDTO) {
        LOGGER.debug("Deleting all Economies list");
        economyRepository.deleteAll(economyMapper.toEntity(economiesDTO));
    }
}

package com.study.service;

import com.study.repository.DiscountRepository;
import com.study.service.dto.DiscountDTO;
import com.study.service.mapper.DiscountMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link DiscountDTO} entities.
 * This class implements the {@link CrudService} interface to provide
 * CRUD operations for Discount entities.
 */
public class DiscountService implements CrudService<DiscountDTO>{

    /**
     * Repository for performing CRUD operations on Discount entities.
     */
    private final DiscountRepository discountRepository;

    /**
     * Mapper for converting between Discounts entities and DiscountsDTO.
     */
    private final DiscountMapper discountMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public DiscountService(){
        this(new DiscountRepository(), new DiscountMapper());
    }

    public DiscountService(DiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    /**
     * Saves a {@link DiscountDTO} entity.
     *
     * @param discountDTO the DiscountDTO to save
     * @return the saved DiscountDTO
     */
    @Override
    public DiscountDTO save(DiscountDTO discountDTO){
        LOGGER.debug("Saving DiscountDTO: {}", discountDTO);
        return discountMapper.toDTO(discountRepository.save(discountMapper.toEntity(discountDTO)));
    }

    /**
     * Saves a list of {@link DiscountDTO} entities.
     *
     * @param discountsDTO the list of DiscountDTOs to save
     * @return the list of saved DiscountDTOs
     */
    @Override
    public List<DiscountDTO> saveAll(List<DiscountDTO> discountsDTO) {
        LOGGER.debug("Saving all DiscountsDTO");
        return discountMapper.toDTO(discountRepository.saveAll(discountMapper.toEntity(discountsDTO)));
    }

    /**
     * Finds a {@link DiscountDTO} by its ID.
     *
     * @param id the ID of the DiscountDTO to find
     * @return an Optional containing the found DiscountDTO, or an empty Optional if not found
     */
    @Override
    public Optional<DiscountDTO> findById(Integer id) {
        LOGGER.debug("Find DiscountDTO by id {}", id);
        return discountMapper.toDTO(discountRepository.findById(id));
    }

    /**
     * Finds all {@link DiscountDTO} entities.
     *
     * @return the list of all DiscountDTOs
     */
    @Override
    public List<DiscountDTO> findAll() {
        LOGGER.debug("Find All DiscountsDTO elements");
        return discountMapper.toDTO(discountRepository.findAll());
    }

    /**
     * Checks if a {@link DiscountDTO} exists by its ID.
     *
     * @param id the ID of the DiscountDTO to check
     * @return true if the DiscountDTO exists, false otherwise
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of Discount by ID: {}", id);
        return discountRepository.existById(id);
    }

    /**
     * Updates a {@link DiscountDTO} by its ID.
     *
     * @param id the ID of the DiscountDTO to update
     * @param nwDiscountDTO the new DiscountDTO data
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateId(Integer id, DiscountDTO nwDiscountDTO) {
        LOGGER.debug("Updating Discount with ID: {}", id);
        if (id != null && nwDiscountDTO != null){
            return discountRepository.updateId(id, discountMapper.toEntity(nwDiscountDTO));
        }
        return false;
    }

    /**
     * Deletes a {@link DiscountDTO} by its ID.
     *
     * @param id the ID of the DiscountDTO to delete
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting Discount by ID: {}", id);
        discountRepository.deleteById(id);
    }

    /**
     * Deletes a {@link DiscountDTO}.
     *
     * @param discountDTO the DiscountDTO to delete
     */
    @Override
    public void delete(DiscountDTO discountDTO) {
        LOGGER.debug("Deleting Discount: {}", discountDTO);
        discountRepository.delete(discountMapper.toEntity(discountDTO));
    }

    /**
     * Deletes all {@link DiscountDTO} entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Discounts");
        discountRepository.deleteAll();
    }

    /**
     * Deletes a list of {@link DiscountDTO} entities.
     *
     * @param discountsDTO the list of DiscountDTOs to delete
     */
    @Override
    public void deleteAll(List<DiscountDTO> discountsDTO) {
        LOGGER.debug("Deleting all Discounts list");
        discountRepository.deleteAll(discountMapper.toEntity(discountsDTO));
    }
}

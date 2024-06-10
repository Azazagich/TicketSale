package com.study.repository;

import com.study.domain.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation for managing Discount entities.
 * */
public class DiscountRepository implements CrudRepository<Discount>{

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for Discount entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for Discount entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, Discount> discounts = new HashMap<>();

    /**
     * Saves a single Discount entity.
     * @param discount The Discount entity to be saved.
     * @return The saved Discount entity.
     * */
    @Override
    public Discount save(Discount discount) {
       if (discount != null){
           discount.setId(++id);
           discounts.put(id, discount);
           LOGGER.info("Saved Discount with id {}", id);
       }
       return discount;
    }

    /**
     * Saves a list of Discount entities.
     * @param discounts The list of Discount entities to be saved.
     * @return The list of saved Discount entities.
     * */
    @Override
    public List<Discount> saveAll(List<Discount> discounts) {
        for (Discount discount : discounts){
            if (discount != null) {
                discount.setId(++id);
                this.discounts.put(id, discount);
                LOGGER.info("Saved Discount with id {}", id);
            }
        }
        return discounts;
    }

    /**
     * Retrieves a Discount entity by its identifier.
     * @param id The identifier of the Discount entity to be retrieved.
     * @return An optional containing the retrieved Discount entity, or empty if not found.
     * */
    @Override
    public Optional<Discount> findById(Integer id) {
        LOGGER.info("Finding Discount with id {}", id);
        return Optional.of(discounts.get(id));
    }

    /**
     * Checks if a Discount entity with the given identifier exists.
     * @param id The identifier of the Discount entity to check.
     * @return true if the Discount entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id) {
        boolean exist = id != null && discounts.containsKey(id);
        LOGGER.info("Existence check for Discount with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of a Discount entity.
     * @param id The current identifier of the Discount entity.
     * @param nwDiscount The Discount entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, Discount nwDiscount) {
        if (nwDiscount != null){
            nwDiscount.setId(id);
            discounts.put(id, nwDiscount);
            LOGGER.info("Updated Discount with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update Discount with id {}", id);
        return false;
    }

    /**
     * Deletes a Discount entity by its identifier.
     * @param id The identifier of the Discount entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id) {
        if (id != null){
            discounts.remove(id);
            LOGGER.info("Deleted Discount with id {}", id);
        }
    }


    /**
     * Deletes a single Discount entity.
     * @param discount The Discount entity to be deleted.
     * */
    @Override
    public void delete(Discount discount) {
        if (discount != null){
            deleteById(discount.getId());
            LOGGER.info("Deleted Discount: {}", discount);
        }
    }

    /**
     * Deletes all Discount entities.
     * */
    @Override
    public void deleteAll() {
        discounts.clear();
        LOGGER.info("Deleted all Discounts");
    }

    /**
     * Deletes a list of Discount entities.
     * @param discounts The list of Discount entities to be deleted.
     * */
    @Override
    public void deleteAll(List<Discount> discounts) {
        if (discounts != null) {
            for (Discount discount : discounts) {
                if (discount != null) {
                    deleteById(discount.getId());
                    LOGGER.info("Deleted discount with id {}", discount.getId());
                }
            }
        }
    }

}

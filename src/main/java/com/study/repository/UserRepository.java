package com.study.repository;

import com.study.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation for managing User entities.
 * */
public class UserRepository implements CrudRepository<User>{

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Counter to generate unique IDs for User entities.
     * */
    private static Integer id = 0;

    /**
     * Storage for User entities, using a HashMap with IDs as keys.
     * */
    private static final HashMap<Integer, User> users = new HashMap<>();

    /**
     * Saves a single User entity.
     * @param user The User entity to be saved.
     * @return The saved User entity.
     * */
    @Override
    public User save(User user) {
        if (user != null) {
            user.setId(++id);
            users.put(id, user);
            LOGGER.debug("Saved User with id {}", id);
        }
        return user;
    }

    /**
     * Saves a list of User entities.
     * @param users The list of User entities to be saved.
     * @return The list of saved User entities.
     * */
    @Override
    public List<User> saveAll(List<User> users) {
        for (User user : users){
            if (user != null) {
                user.setId(++id);
                this.users.put(id,user);
                LOGGER.debug("Saved User with id {}", id);
            }
        }
        return users;
    }

    /**
     * Retrieves a User entity by its identifier.
     * @param id The identifier of the User entity to be retrieved.
     * @return An optional containing the retrieved User entity, or empty if not found.
     * */
    @Override
    public Optional<User> findById(Integer id){
        LOGGER.debug("Finding User with id {}", id);
        return Optional.of(users.get(id));
    }

    /**
     * Checks if a User entity with the given identifier exists.
     * @param id The identifier of the User entity to check.
     * @return true if the User entity exists, otherwise false.
     * */
    @Override
    public boolean existById(Integer id){
        boolean exist = id != null && users.containsKey(id);
        LOGGER.debug("Existence check for User with id {}: {}", id, exist);
        return exist;
    }

    /**
     * Updates the identifier of a User entity.
     * @param id The current identifier of the User entity.
     * @param nwUser The User entity with the updated identifier.
     * @return true if the update was successful, otherwise false.
     * */
    @Override
    public boolean updateId(Integer id, User nwUser){
        if (nwUser != null && id != null){
            users.remove(id);
            users.put(nwUser.getId(), nwUser);
            LOGGER.debug("Updated User with id {}", id);
            return true;
        }
        LOGGER.warn("Failed to update User with id {}", id);
        return false;
    }

    /**
     * Deletes a User entity by its identifier.
     * @param id The identifier of the User entity to be deleted.
     * */
    @Override
    public void deleteById(Integer id){
        if (id != null){
            users.remove(id);
            LOGGER.debug("Deleted User with id {}", id);

        }
    }

    /**
     * Deletes a single User entity.
     * @param user The User entity to be deleted.
     * */
    @Override
    public void delete(User user){
        if (user != null){
            deleteById(user.getId());
            LOGGER.debug("Deleted User: {}", user);
        }
    }

    /**
     * Deletes all User entities.
     * */
    @Override
    public void deleteAll(){
        users.clear();
        LOGGER.debug("Deleted all Users");
    }

    /**
     * Deletes a list of User entities.
     * @param users The list of User entities to be deleted.
     * */
    @Override
    public void deleteAll(List<User> users) {
        if (users != null) {
            for (User user : users) {
                if (user != null) {
                    deleteById(user.getId());
                    LOGGER.debug("Deleted User with id {}", user.getId());
                }
            }
        }
    }

}

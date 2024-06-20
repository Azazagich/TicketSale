package com.study.service;

import com.study.repository.UserRepository;
import com.study.service.dto.UserDTO;
import com.study.service.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing {@link UserDTO} entities.
 * Implements {@link CrudService} to provide CRUD operations for UserDTO objects.
 */
public class UserService implements CrudService<UserDTO>{

    /**
     * Repository for performing CRUD operations on User entities.
     */
    private final UserRepository userRepository;

    /**
     * Mapper for converting between User entities and UserDTOs.
     */
    private final UserMapper userMapper;

    private final static Logger LOGGER = LogManager.getLogger();

    public UserService(){
        this(new UserRepository(), new UserMapper());
    }

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Saves a UserDTO entity.
     *
     * @param userDTO The UserDTO object to save.
     * @return The saved UserDTO object.
     */
    @Override
    public UserDTO save(UserDTO userDTO) {
        LOGGER.debug("Saving UserDTO: {}", userDTO);
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    /**
     * Saves a list of UserDTO entities.
     *
     * @param usersDTO The list of UserDTO objects to save.
     * @return The list of saved UserDTO objects.
     */
    @Override
    public List<UserDTO> saveAll(List<UserDTO> usersDTO) {
        LOGGER.debug("Saving all UsersDTO");
        return userMapper.toDTO(userRepository.saveAll(userMapper.toEntity(usersDTO)));
    }

    /**
     * Finds a UserDTO entity by its ID.
     *
     * @param id The ID of the UserDTO entity to find.
     * @return An Optional containing the found UserDTO object, or empty if not found.
     */
    @Override
    public Optional<UserDTO> findById(Integer id) {
        LOGGER.debug("Find UserDTO by id {}", id);
        return userMapper.toDTO(userRepository.findById(id));
    }


    /**
     * Retrieves all UserDTO entities.
     *
     * @return A list of all UserDTO objects.
     */
    @Override
    public List<UserDTO> findAll() {
        LOGGER.debug("Find All UsersDTO elements");
        return userMapper.toDTO(userRepository.findAll());
    }

    /**
     * Checks if a UserDTO entity exists by its ID.
     *
     * @param id The ID of the UserDTO entity to check.
     * @return true if the UserDTO exists, false otherwise.
     */
    @Override
    public boolean existById(Integer id) {
        LOGGER.debug("Checking existence of User by ID: {}", id);
        return userRepository.existById(id);
    }

    /**
     * Updates a UserDTO entity with a new DTO object.
     *
     * @param id       The ID of the UserDTO entity to update.
     * @param nwUserDTO The new UserDTO object to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateId(Integer id, UserDTO nwUserDTO) {
        LOGGER.debug("Updating User with ID: {}", id);
        if (id != null && nwUserDTO != null){
            return userRepository.updateId(id, userMapper.toEntity(nwUserDTO));
        }
        return false;
    }

    /**
     * Deletes a UserDTO entity by its ID.
     *
     * @param id The ID of the UserDTO entity to delete.
     */
    @Override
    public void deleteById(Integer id) {
        LOGGER.debug("Deleting User by ID: {}", id);
        userRepository.deleteById(id);
    }

    /**
     * Deletes a UserDTO entity.
     *
     * @param userDTO The UserDTO object to delete.
     */
    @Override
    public void delete(UserDTO userDTO) {
        LOGGER.debug("Deleting User: {}", userDTO);
        userRepository.delete(userMapper.toEntity(userDTO));
    }

    /**
     * Deletes all UserDTO entities.
     */
    @Override
    public void deleteAll() {
        LOGGER.debug("Deleting all Users");
        userRepository.deleteAll();
    }

    /**
     * Deletes a list of UserDTO entities.
     *
     * @param usersDTO The list of UserDTO objects to delete.
     */
    @Override
    public void deleteAll(List<UserDTO> usersDTO) {
        LOGGER.debug("Deleting all Users list");
        userRepository.deleteAll(userMapper.toEntity(usersDTO));
    }
}

package com.study.service.mapper;

import com.study.domain.User;
import com.study.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Mapper class for converting between User and UserDTO objects.
 */
public class UserMapper implements Mapper<UserDTO, User>{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts a User object to a UserDTO object.
     * Logs the conversion if the user is not null.
     *
     * @param user the User object to be converted.
     * @return the converted UserDTO object.
     */
    @Override
    public UserDTO toDTO(User user) {
        if (user != null) {
            LOGGER.debug("Converted from User to UserDTO: {}", user);
            return new UserDTO()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .middleName(user.getMiddleName())
                    .lastName(user.getLastName())
                    .dateOfBirth(user.getDateOfBirth())
                    .gender(user.getGender())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .password(user.getPassword());
        }
        return new UserDTO();
    }

    /**
     * Converts an Optional<User> object to an Optional<UserDTO> object.
     * Logs the conversion if the user is present.
     *
     * @param user the Optional<User> object to be converted.
     * @return the converted Optional<UserDTO> object.
     */
    @Override
    public Optional<UserDTO> toDTO(Optional<User> user) {
        if (user.isPresent()){
            LOGGER.debug("Converted from Optional<User> to Optional<UserDTO>: {}", user);
            return Optional.of(toDTO(user.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of User objects to a list of UserDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param users the list of User objects to be converted.
     * @return the converted list of UserDTO objects.
     */
    @Override
    public List<UserDTO> toDTO(List<User> users) {
        if (!users.isEmpty()){
            LOGGER.debug("Converting list of Users to list of UsersDTO");
            return users.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts a UserDTO object to a User object.
     * Logs the conversion if the userDTO is not null.
     *
     * @param userDTO the UserDTO object to be converted.
     * @return the converted User object.
     */
    @Override
    public User toEntity(UserDTO userDTO) {
        LOGGER.debug("Converted from UserDTO to User: {}", userDTO);
        if (userDTO != null){
            return new User()
                    .id(userDTO.getId())
                    .firstName(userDTO.getFirstName())
                    .middleName(userDTO.getMiddleName())
                    .lastName(userDTO.getLastName())
                    .dateOfBirth(userDTO.getDateOfBirth())
                    .gender(userDTO.getGender())
                    .email(userDTO.getEmail())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .password(userDTO.getPassword());
        }
        return new User();
    }

    /**
     * Converts a list of UserDTO objects to a list of User objects.
     * Logs the conversion if the list is not empty.
     *
     * @param usersDTO the list of UserDTO objects to be converted.
     * @return the converted list of User objects.
     */
    @Override
    public List<User> toEntity(List<UserDTO> usersDTO) {
         if (!usersDTO.isEmpty()){
             LOGGER.debug("Converting list of UserDTOs to list of Users");
             return usersDTO.stream()
                     .filter(Objects::nonNull)
                     .map(this::toEntity)
                     .toList();
         }
         return List.of();
    }
}

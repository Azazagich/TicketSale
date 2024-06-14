package com.study.service.mapper;

import com.study.domain.AgeGroup;
import com.study.service.dto.AgeGroupDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Mapper class for converting between AgeGroup entities and AgeGroupDTOs.
 */
public class AgeGroupMapper implements Mapper<AgeGroupDTO, AgeGroup> {

    /**
     * Logger for this class.
     * */
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Converts an AgeGroup entity to an AgeGroupDTO.
     *
     * @param ageGroup the AgeGroup entity to be converted
     * @return the converted AgeGroupDTO
     */
    @Override
    public AgeGroupDTO toDTO(AgeGroup ageGroup) {
        if (ageGroup != null) {
            LOGGER.debug("Converted from ageGroup to AgeGroupDTO: {}", ageGroup);
            return new AgeGroupDTO().id(ageGroup.getId()).type(ageGroup.getType());
        }
        return new AgeGroupDTO();
    }

    /**
     * Converts an Optional AgeGroup entity to an Optional AgeGroupDTO.
     *
     * @param ageGroup the optional AgeGroup entity to be converted
     * @return an optional containing the converted AgeGroupDTO, or empty if the input was empty
     */
    @Override
    public Optional<AgeGroupDTO> toDTO(Optional<AgeGroup> ageGroup){
        if (ageGroup.isPresent()){
            LOGGER.debug("Converted from Optional<ageGroup> to Optional<AgeGroupDTO> {}:", ageGroup);
            return Optional.of(toDTO(ageGroup.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of AgeGroup entities to a list of AgeGroupDTOs.
     *
     * @param ageGroups the list of AgeGroup entities to be converted
     * @return the list of converted AgeGroupDTOs
     */
    @Override
    public List<AgeGroupDTO> toDTO(List<AgeGroup> ageGroups) {
        if (ageGroups.isEmpty()){
            return ageGroups.stream().filter(ageGroup -> ageGroup != null)
                                     .map(ageGroup -> toDTO(ageGroup)).toList();
        }
        return List.of();
    }

    /**
     * Converts an AgeGroupDTO to an AgeGroup entity.
     *
     * @param ageGroupDTO the AgeGroupDTO to be converted
     * @return the converted AgeGroup entity
     */
    @Override
    public AgeGroup toEntity(AgeGroupDTO ageGroupDTO) {
        LOGGER.debug("Converted from AgeGroupDTO to AgeGroup: {}", ageGroupDTO);
        if (ageGroupDTO != null){
            return new AgeGroup().id(ageGroupDTO.getId()).type(ageGroupDTO.getType());
        }
        return new AgeGroup();
    }

    /**
     * Converts a list of AgeGroupDTOs to a list of AgeGroup entities.
     *
     * @param ageGroupsDTO the list of AgeGroupDTOs to be converted
     * @return the list of converted AgeGroup entities
     */
    @Override
    public List<AgeGroup> toEntity(List<AgeGroupDTO> ageGroupsDTO) {
        if (ageGroupsDTO.isEmpty()){
            return ageGroupsDTO.stream().filter(ageGroupDTO -> ageGroupDTO != null)
                .map(ageGroupDTO -> toEntity(ageGroupDTO)).toList();
        }
        return List.of();
    }
}

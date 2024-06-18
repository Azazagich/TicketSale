package com.study.service.mapper;

import com.study.domain.Economy;
import com.study.service.dto.EconomyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Mapper class for converting between Economy and EconomyDTO objects.
 */
public class EconomyMapper implements Mapper<EconomyDTO, Economy>{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts an Economy object to an EconomyDTO object.
     * Logs the conversion if the economy is not null.
     *
     * @param economy the Economy object to be converted.
     * @return the converted EconomyDTO object.
     */
    @Override
    public EconomyDTO toDTO(Economy economy) {
        if (economy != null) {
            LOGGER.debug("Converted from Economy to EconomyDTO: {}", economy);
            return new EconomyDTO()
                    .id(economy.getId())
                    .type(economy.getType());
        }
        return new EconomyDTO();
    }

    /**
     * Converts an Optional<Economy> object to an Optional<EconomyDTO> object.
     * Logs the conversion if the economy is present.
     *
     * @param economy the Optional<Economy> object to be converted.
     * @return the converted Optional<EconomyDTO> object.
     */
    @Override
    public Optional<EconomyDTO> toDTO(Optional<Economy> economy) {
        if (economy.isPresent()){
            LOGGER.debug("Converted from Optional<economy> to Optional<EconomyDTO> {}:", economy);
            return Optional.of(toDTO(economy.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of Economy objects to a list of EconomyDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param economies the list of Economy objects to be converted.
     * @return the converted list of EconomyDTO objects.
     */
    @Override
    public List<EconomyDTO> toDTO(List<Economy> economies) {
        if (!economies.isEmpty()){
            LOGGER.debug("Converting list of Economies to list of EconomiesDTO");
            return economies.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts an EconomyDTO object to an Economy object.
     * Logs the conversion if the economyDTO is not null.
     *
     * @param economyDTO the EconomyDTO object to be converted.
     * @return the converted Economy object.
     */
    @Override
    public Economy toEntity(EconomyDTO economyDTO) {
        LOGGER.debug("Converted from EconomyDTO to Economy: {}", economyDTO);
        if (economyDTO != null){
            return new Economy()
                    .id(economyDTO.getId())
                    .type(economyDTO.getType());
        }
        return new Economy();
    }

    /**
     * Converts a list of EconomyDTO objects to a list of Economy objects.
     * Logs the conversion if the list is not empty.
     *
     * @param economiesDTO the list of EconomyDTO objects to be converted.
     * @return the converted list of Economy objects.
     */
    @Override
    public List<Economy> toEntity(List<EconomyDTO> economiesDTO) {
        if (!economiesDTO.isEmpty()){
            LOGGER.debug("Converting list of EconomiesDTO to list of Economies");
            return economiesDTO.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .toList();
        }
        return List.of();
    }
}
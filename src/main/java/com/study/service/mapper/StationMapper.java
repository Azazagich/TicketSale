package com.study.service.mapper;

import com.study.domain.Station;
import com.study.service.dto.StationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Mapper class for converting between Station and StationDTO objects.
 */
public class StationMapper implements Mapper<StationDTO, Station>{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts a Station object to a StationDTO object.
     * Logs the conversion if the station is not null.
     *
     * @param station the Station object to be converted.
     * @return the converted StationDTO object.
     */
    @Override
    public StationDTO toDTO(Station station) {
        if (station != null) {
            LOGGER.debug("Converted from Station to StationDTO: {}", station);
            return new StationDTO()
                    .id(station.getId())
                    .nameOfStation(station.getNameOfStation())
                    .addressLocation(station.getAddressLocation())
                    .stationPhone(station.getStationPhone());
        }
        return new StationDTO();
    }

    /**
     * Converts an Optional<Station> object to an Optional<StationDTO> object.
     * Logs the conversion if the station is present.
     *
     * @param station the Optional<Station> object to be converted.
     * @return the converted Optional<StationDTO> object.
     */
    @Override
    public Optional<StationDTO> toDTO(Optional<Station> station) {
        if (station.isPresent()){
            LOGGER.debug("Converted from Optional<Station> to Optional<StationDTO> {}:", station);
            return Optional.of(toDTO(station.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of Station objects to a list of StationDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param stations the list of Station objects to be converted.
     * @return the converted list of StationDTO objects.
     */
    @Override
    public List<StationDTO> toDTO(List<Station> stations) {
        if (!stations.isEmpty()){
            LOGGER.debug("Converting list of Stations to list of StationsDTO");
            return stations.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts a StationDTO object to a Station object.
     * Logs the conversion if the stationDTO is not null.
     *
     * @param stationDTO the StationDTO object to be converted.
     * @return the converted Station object.
     */
    @Override
    public Station toEntity(StationDTO stationDTO) {
        LOGGER.debug("Converted from StationDTO to Station: {}", stationDTO);
        if (stationDTO != null){
            return new Station()
                    .id(stationDTO.getId())
                    .nameOfStation(stationDTO.getNameOfStation()).
                    addressLocation(stationDTO.getAddressLocation())
                    .stationPhone(stationDTO.getStationPhone());
        }
        return new Station();
    }

    /**
     * Converts a list of StationDTO objects to a list of Station objects.
     * Logs the conversion if the list is not empty.
     *
     * @param stationsDTO the list of StationDTO objects to be converted.
     * @return the converted list of Station objects.
     */
    @Override
    public List<Station> toEntity(List<StationDTO> stationsDTO) {
        if (!stationsDTO.isEmpty()){
            LOGGER.debug("Converting list of StationsDTO to list of Stations");
            return stationsDTO.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .toList();
        }
        return List.of();
    }
}

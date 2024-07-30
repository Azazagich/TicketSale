package com.study.service.mapper;

import com.study.domain.Train;
import com.study.service.dto.TrainDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Mapper class for converting between Train and TrainDTO objects.
 */
public class TrainMapper implements Mapper<TrainDTO, Train>{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts a Train object to a TrainDTO object.
     * Logs the conversion if the train is not null.
     *
     * @param train the Train object to be converted.
     * @return the converted TrainDTO object.
     */
    @Override
    public TrainDTO toDTO(Train train) {
        if (train != null) {
            LOGGER.debug("Converted from Train to TrainDTO: {}", train);
            return new TrainDTO()
                    .id(train.getId())
                    .amountOfSeats(train.getAmountOfSeats())
                    .trainModel(train.getTrainModel());
        }
        return new TrainDTO();
    }


    /**
     * Converts an Optional<Train> object to an Optional<TrainDTO> object.
     * Logs the conversion if the train is present.
     *
     * @param train the Optional<Train> object to be converted.
     * @return the converted Optional<TrainDTO> object.
     */
    @Override
    public Optional<TrainDTO> toDTO(Optional<Train> train) {
        if (train.isPresent()){
            LOGGER.debug("Converted from Optional<Train> to Optional<TrainDTO> {}:", train);
            return Optional.of(toDTO(train.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of Train objects to a list of TrainDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param trains the list of Train objects to be converted.
     * @return the converted list of TrainDTO objects.
     */
    @Override
    public List<TrainDTO> toDTO(List<Train> trains) {
        if (!trains.isEmpty()){
            LOGGER.debug("Converting list of Trains to list of TrainsDTO");
            return trains.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts a TrainDTO object to a Train object.
     * Logs the conversion if the trainDTO is not null.
     *
     * @param trainDTO the TrainDTO object to be converted.
     * @return the converted Train object.
     */
    @Override
    public Train toEntity(TrainDTO trainDTO) {
        LOGGER.debug("Converted from TrainDTO to Train: {}", trainDTO);
        if (trainDTO != null){
            return new Train()
                    .id(trainDTO.getId())
                    .amountOfSeats(trainDTO.getAmountOfSeats())
                    .trainModel(trainDTO.getTrainModel());
        }
        return new Train();
    }

    /**
     * Converts a list of TrainDTO objects to a list of Train objects.
     * Logs the conversion if the list is not empty.
     *
     * @param trainsDTO the list of TrainDTO objects to be converted.
     * @return the converted list of Train objects.
     */
    @Override
    public List<Train> toEntity(List<TrainDTO> trainsDTO) {
        if (!trainsDTO.isEmpty()){
            LOGGER.debug("Converting list of TrainsDTO to list of Trains");
            return trainsDTO.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .toList();
        }
        return List.of();
    }
}

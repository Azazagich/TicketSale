package com.study.service.mapper;

import com.study.domain.Discount;
import com.study.service.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mapper class for converting between Discount and DiscountDTO objects.
 */
public class DiscountMapper implements Mapper<DiscountDTO, Discount>{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Converts a Discount object to a DiscountDTO object.
     * Logs the conversion if the discount is not null.
     *
     * @param discount the Discount object to be converted.
     * @return the converted DiscountDTO object.
     */
    @Override
    public DiscountDTO toDTO(Discount discount) {
        if (discount != null) {
            LOGGER.debug("Converted from Discount to DiscountDTO: {}", discount);
            return new DiscountDTO()
                    .id(discount.getId())
                    .type(discount.getType())
                    .percent(discount.getPercent())
                    .startAt(discount.getStartAt())
                    .endAt(discount.getEndAt());
        }
        return new DiscountDTO();
    }

    /**
     * Converts an Optional<Discount> object to an Optional<DiscountDTO> object.
     * Logs the conversion if the discount is present.
     *
     * @param discount the Optional<Discount> object to be converted.
     * @return the converted Optional<DiscountDTO> object.
     */
    @Override
    public Optional<DiscountDTO> toDTO(Optional<Discount> discount) {
        if (discount.isPresent()){
            LOGGER.debug("Converted from Optional<discount> to Optional<DiscountDTO> {}:", discount);
            return Optional.of(toDTO(discount.get()));
        }
        return Optional.empty();
    }

    /**
     * Converts a list of Discount objects to a list of DiscountDTO objects.
     * Logs the conversion if the list is not empty.
     *
     * @param discounts the list of Discount objects to be converted.
     * @return the converted list of DiscountDTO objects.
     */
    @Override
    public List<DiscountDTO> toDTO(List<Discount> discounts) {
        if (!discounts.isEmpty()){
            LOGGER.debug("Converting list of Discounts to list of DiscountsDTO");
            return discounts.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .toList();
        }
        return List.of();
    }

    /**
     * Converts a set of Discount objects to a set of DiscountDTO objects.
     * Logs the conversion if the set is not empty.
     *
     * @param discounts the set of Discount objects to be converted.
     * @return the converted set of DiscountDTO objects.
     */
    public Set<DiscountDTO> toDTO(Set<Discount> discounts) {
        if (!discounts.isEmpty()){
            LOGGER.debug("Converting set of Discounts to set of DiscountsDTO");
            return discounts.stream()
                    .filter(Objects::nonNull)
                    .map(this::toDTO)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    /**
     * Converts a DiscountDTO object to a Discount object.
     * Logs the conversion if the discountDTO is not null.
     *
     * @param discountDTO the DiscountDTO object to be converted.
     * @return the converted Discount object.
     */
    @Override
    public Discount toEntity(DiscountDTO discountDTO) {
        LOGGER.debug("Converted from DiscountDTO to Discount: {}", discountDTO);
        if (discountDTO != null){
            return new Discount()
                    .id(discountDTO.getId())
                    .type(discountDTO.getType())
                    .percent(discountDTO.getPercent())
                    .startAt(discountDTO.getStartAt())
                    .endAt(discountDTO.getEndAt());
        }
        return new Discount();
    }

    /**
     * Converts a list of DiscountDTO objects to a list of Discount objects.
     * Logs the conversion if the list is not empty.
     *
     * @param discountsDTO the list of DiscountDTO objects to be converted.
     * @return the converted list of Discount objects.
     */
    @Override
    public List<Discount> toEntity(List<DiscountDTO> discountsDTO) {
        if (!discountsDTO.isEmpty()){
            LOGGER.debug("Converting list of DiscountsDTO to list of Discounts");
            return discountsDTO.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .toList();
        }
        return List.of();
    }

    public Set<Discount> toEntity(Set<DiscountDTO> discounts) {
        if (!discounts.isEmpty()){
            LOGGER.debug("Converting set of DiscountsDTO to set of Discounts");
            return discounts.stream()
                    .filter(Objects::nonNull)
                    .map(this::toEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}

package com.study.service.mapper;

import com.study.domain.Discount;
import com.study.service.dto.DiscountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link DiscountMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class DiscountMapperTest {

    private static final String DISCOUNT_TYPE_SOCIAL = "Social Discount";
    private static final String DISCOUNT_TYPE_MILITARY = "Military Discount";
    private static final String DISCOUNT_TYPE_CHILDREN = "Children Discount";

    private static final int ID_1 = 1;
    private static final int ID_3 = 3;

    private DiscountMapper discountMapper;

    private DiscountDTO discountDTO;
    private Discount discount;

    private DiscountDTO createDTO(){
        return new DiscountDTO();
    }

    private DiscountDTO createDTO(String type){
        return new DiscountDTO().type(type);
    }

    private DiscountDTO createDTO(int id, String type){
        return new DiscountDTO().id(id).type(type);
    }

    private Discount createEntity(){
        return new Discount();
    }

    private Discount createEntity(String type){
        return new Discount().type(type);
    }

    private Discount createEntity(int id, String type){
        return new Discount().id(id).type(type);
    }

    @BeforeEach
    void setUp() {
        discountMapper = new DiscountMapper();

        discountDTO = createDTO(DISCOUNT_TYPE_SOCIAL);
        discount = createEntity(DISCOUNT_TYPE_MILITARY);
    }

    @Test
    void testToDTO() {
        assertEquals(discountMapper.toDTO(discount), createDTO(DISCOUNT_TYPE_MILITARY));
        assertEquals(discountMapper.toDTO(createEntity()), createDTO());
    }

    @Test
    void testToDTOs() {
        Discount discount2 = createEntity(ID_3, DISCOUNT_TYPE_CHILDREN);
        List<Discount> discounts = List.of(discount, discount2);
        List<DiscountDTO> expectedDTOs = List.of(createDTO(DISCOUNT_TYPE_SOCIAL),
                createDTO(ID_3, DISCOUNT_TYPE_CHILDREN));
        assertIterableEquals(discountMapper.toDTO(discounts), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Discount> discount = Optional.of(createEntity(ID_1,DISCOUNT_TYPE_SOCIAL));
        assertEquals(discountMapper.toDTO(discount), Optional.of(createDTO(ID_1,DISCOUNT_TYPE_SOCIAL)));
    }

    @Test
    void testToEntity() {
        assertEquals(discountMapper.toEntity(discountDTO), createEntity(DISCOUNT_TYPE_SOCIAL));
    }

    @Test
    void testToEntities() {
        DiscountDTO discount2DTO = createDTO(ID_3, DISCOUNT_TYPE_CHILDREN);
        List<DiscountDTO> discountsDTO = List.of(discountDTO, discount2DTO);
        List<Discount> expectedEntities = List.of(createEntity(DISCOUNT_TYPE_SOCIAL),
                createEntity(ID_3,DISCOUNT_TYPE_CHILDREN));
        assertIterableEquals(discountMapper.toEntity(discountsDTO), expectedEntities);
    }
}

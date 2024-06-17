package com.study.serviceTest.mapperTest;

import com.study.domain.Discount;
import com.study.service.dto.DiscountDTO;
import com.study.service.mapper.DiscountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link DiscountMapper}.
 * This class contains unit tests for the {@link DiscountMapper} class which converts
 * between {@link Discount} and {@link DiscountDTO} objects.
 */
public class DiscountMapperTest {

    private DiscountMapper discountMapper;

    private DiscountDTO discountDTO;
    private Discount discount;

    @BeforeEach
    void setUp() {
        discountMapper = new DiscountMapper();

        discountDTO = new DiscountDTO().type("Пенсійний");
        discount = new Discount().type("Військовим");
    }

    @Test
    void testToDTO() {
        assertEquals(discountMapper.toDTO(discount), new DiscountDTO().type("Військовим"));
        assertEquals(discountMapper.toDTO(new Discount()), new DiscountDTO());
        assertInstanceOf(DiscountDTO.class, discountMapper.toDTO(new Discount()));
    }

    @Test
    void testToDTOs() {
        Discount discount2 = new Discount().id(3).type("Сиротам");
        List<Discount> discounts = List.of(discount, discount2);
        List<DiscountDTO> expectedDTOs = List.of(new DiscountDTO().type("Пенсійний"), new DiscountDTO().id(3).type("Дитина"));
        assertEquals(discountMapper.toDTO(discounts), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<Discount> discount = Optional.of(new Discount().id(1).type("Пенсійний"));
        assertEquals(discountMapper.toDTO(discount), Optional.of(new DiscountDTO().id(1).type("Пенсійний")));
    }

    @Test
    void testToEntity() {
        assertEquals(discountMapper.toEntity(discountDTO), new Discount().type("Пенсійний"));
        assertInstanceOf(Discount.class, discountMapper.toEntity(new DiscountDTO()));
    }

    @Test
    void testToEntities() {
        DiscountDTO discount2DTO = new DiscountDTO().id(3).type("Сиротам");
        List<DiscountDTO> discountsDTO = List.of(discountDTO, discount2DTO);
        List<Discount> expectedEntities = List.of(new Discount().type("Пенсійний"), new Discount().id(3).type("Сиротам"));
        assertEquals(discountMapper.toEntity(discountsDTO), expectedEntities);
    }
}

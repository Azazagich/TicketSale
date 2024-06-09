package com.study.repository;

import com.study.domain.AgeGroup;
import com.study.domain.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DiscountRepository implements CrudRepository<Discount>{

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, Discount> discounts = new HashMap<>();


    @Override
    public Discount save(Discount discount) {
       if (discount != null){
           discount.setId(++id);
           discounts.put(id, discount);
       }
       return discount;
    }


    @Override
    public List<Discount> saveAll(List<Discount> discounts) {
        for (Discount discount : discounts){
            if (discount != null) {
                discount.setId(++id);
                this.discounts.put(id, discount);
            }
        }
        return discounts;
    }


    @Override
    public Optional<Discount> findById(Integer id) {
        return Optional.of(discounts.get(id));
    }


    @Override
    public boolean existById(Integer id) {
        return id != null && discounts.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, Discount nwDiscount) {
        if (nwDiscount != null){
            nwDiscount.setId(id);
            discounts.put(id, nwDiscount);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id) {
        if (id != null){
            discounts.remove(id);
        }
    }


    @Override
    public void delete(Discount discount) {
        if (discount != null){
            deleteById(discount.getId());
        }
    }

    @Override
    public void deleteAll() {
        discounts.clear();
    }

    @Override
    public void deleteAll(List<Discount> discounts) {
        if (discounts != null) {
            for (Discount discount : discounts) {
                if (discount != null) {
                    deleteById(discount.getId());
                }
            }
        }
    }

}

package com.study.repository;

import com.study.domain.AgeGroup;
import com.study.domain.Economy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EconomyRepository implements CrudRepository<Economy>{

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, Economy> classEconomies = new HashMap<>();

    //CRUD
    @Override
    public Economy save(Economy economy) {
        if (economy != null) {
            economy.setId(++id);
            classEconomies.put(id, economy);
        }
        return economy;
    }


    @Override
    public List<Economy> saveAll(List<Economy> economies) {
        for (Economy economy : economies){
            if (economy != null) {
                economy.setId(++id);
                classEconomies.put(id, economy);
            }
        }
        return economies;
    }


    @Override
    public Optional<Economy> findById(Integer id){
        return Optional.of(classEconomies.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && classEconomies.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, Economy nwEconomy){
        if (nwEconomy != null){
            nwEconomy.setId(id);
            classEconomies.put(id, nwEconomy);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id){
        if (id != null){
            classEconomies.remove(id);
        }
    }


    @Override
    public void delete(Economy economy){
        if (economy != null){
            deleteById(economy.getId());
        }
    }


    @Override
    public void deleteAll(){
        classEconomies.clear();
    }


    @Override
    public void deleteAll(List<Economy> economies) {
        if (economies != null) {
            for (Economy economy : economies) {
                if (economy != null) {
                    deleteById(economy.getId());
                }
            }
        }
    }

}

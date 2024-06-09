package com.study.repository;

import com.study.domain.Station;
import com.study.domain.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TrainRepository implements CrudRepository<Train> {

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, Train> trains = new HashMap<>();

    //CRUD
    @Override
    public Train save(Train train) {
        if (train != null) {
            train.setId(++id);
            trains.put(id, train);
        }
        return train;
    }


    @Override
    public List<Train> saveAll(List<Train> trains) {
        for (Train train : trains){
            if (train != null) {
                train.setId(++id);
                this.trains.put(id, train);
            }
        }
        return trains;
    }


    @Override
    public Optional<Train> findById(Integer id){
        return Optional.of(trains.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && trains.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, Train nwTrain){
        if (nwTrain != null){
            nwTrain.setId(id);
            trains.put(id, nwTrain);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Integer id){
        if (id != null){
            trains.remove(id);
        }
    }

    @Override
    public void delete(Train train){
        if (train != null){
            deleteById(train.getId());
        }
    }


    @Override
    public void deleteAll(){
        trains.clear();
    }


    @Override
    public void deleteAll(List<Train> trains) {
        if (trains != null) {
            for (Train train : trains) {
                if (train != null) {
                    deleteById(train.getId());
                }
            }
        }
    }

}

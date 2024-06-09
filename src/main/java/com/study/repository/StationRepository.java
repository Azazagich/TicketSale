package com.study.repository;

import com.study.domain.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StationRepository implements CrudRepository<Station>{

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, Station> stations = new HashMap<>();

    //CRUD
    @Override
    public Station save(Station station) {
        if (station != null) {
            station.setId(++id);
            stations.put(id, station);
        }
        return station;
    }

    @Override
    public List<Station> saveAll(List<Station> stations) {
        for (Station station : stations){
            if (station != null) {
                station.setId(++id);
                this.stations.put(id, station);
            }
        }
        return stations;
    }

    @Override
    public Optional<Station> findById(Integer id){
        return Optional.of(stations.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && stations.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, Station nwStation){
        if (nwStation != null){
            nwStation.setId(id);
            stations.put(id, nwStation);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id){
        if (id != null){
            stations.remove(id);
        }
    }

    @Override
    public void delete(Station station){
        if (station != null){
            deleteById(station.getId());
        }
    }


    @Override
    public void deleteAll(){
        stations.clear();
    }


    @Override
    public void deleteAll(List<Station> stations) {
        if (stations != null) {
            for (Station station : stations) {
                if (station != null) {
                    deleteById(station.getId());
                }
            }
        }
    }

}

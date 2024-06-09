package com.study.repository;

import com.study.domain.Station;
import com.study.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRepository<User>{

    private static Logger LOGGER = LogManager.getLogger();

    private static Integer id = 0;

    private static final HashMap<Integer, User> users = new HashMap<>();

    //CRUD
    @Override
    public User save(User user) {
        if (user != null) {
            user.setId(++id);
            users.put(id, user);
        }
        return user;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        for (User user : users){
            if (user != null) {
                user.setId(++id);
                this.users.put(id,user);
            }
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id){
        return Optional.of(users.get(id));
    }


    @Override
    public boolean existById(Integer id){
        return id != null && users.containsKey(id);
    }


    @Override
    public boolean updateId(Integer id, User nwUser){
        if (nwUser != null){
            nwUser.setId(id);
            users.put(id, nwUser);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(Integer id){
        if (id != null){
            users.remove(id);
        }
    }

    @Override
    public void delete(User user){
        if (user != null){
            deleteById(user.getId());
        }
    }


    @Override
    public void deleteAll(){
        users.clear();
    }


    @Override
    public void deleteAll(List<User> users) {
        if (users != null) {
            for (User user : users) {
                if (user != null) {
                    deleteById(user.getId());
                }
            }
        }
    }

}

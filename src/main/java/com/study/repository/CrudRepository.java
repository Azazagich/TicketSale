package com.study.repository;

import java.util.List;
import java.util.Optional;

/**
 * An interface for CRUD (Create, Read, Update, Delete) operations on entities.
 * @param <E> The type of entity managed by this repository.
 * */
public interface CrudRepository<E> {

     /**
      * Saves a single entity.
      * @param entity | The entity to be saved.
      * @return The saved entity.
      * */
     E save(E entity);

     /**
      * Saves a list of entities.
      * @param entities | The list of entities to be saved.
      * @return The list of saved entities.
      * */
     List<E> saveAll(List<E> entities);

     /**
      * Retrieves an entity by its identifier.
      * @param id | The identifier of the entity to be retrieved.
      * @return An optional containing the retrieved entity, or empty if not found.
      * */
     Optional<E> findById(Integer id);

     /**
      * Finds all entities of type E in the provided list.
      * @param entities The list of entities to search within.
      * @return A list containing all entities of type E found in the provided list.
      * */
     List<E> findAll();

     /**
      * Checks if an entity with the given identifier exists.
      * @param id | The identifier of the entity to check.
      * @return true if the entity exists, otherwise false.
      * */
     boolean existById(Integer id);

     /**
      * Updates the identifier of an entity.
      * @param id | The current identifier of the entity.
      * @param entity | The entity with the updated identifier.
      * @return true if the update was successful, otherwise false.
      * */
     boolean updateId(Integer id, E entity);

     /**
      * Deletes an entity by its identifier.
      * @param id The identifier of the entity to be deleted.
      */
     void deleteById(Integer id);

     /**
      * Deletes a single entity.
      * @param entity The entity to be deleted.
      */
     void delete(E entity);

     /**
      * Deletes all entities.
      */
     void deleteAll();

     /**
      * Deletes a list of entities.
      * @param entities The list of entities to be deleted.
      */
     void deleteAll(List<E> entities);

}

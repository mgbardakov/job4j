package ru.job4j.generic;

/**
 * interface for storing objects
 * @param <T> - type of stored object
 * @author mbardakov
 * @since 25.05.2020
 */
public interface Store<T extends Base> {
    /**
     * adds object to the store
     * @param model - object
     */
    void add(T model);

    /**
     * replaces object with another by id
     * @param id - id of an object to replace
     * @param model - new object
     * @return success/failure
     */
    boolean replace(String id, T model);

    /**
     * deletes object by id
     * @param id - id of the object
     * @return success/failure
     */
    boolean delete(String id);

    /**
     * finds object by id
     * @param id - id of an object
     * @return searched object
     */
    T findById(String id);
}
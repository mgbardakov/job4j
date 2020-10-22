package ru.job4j.concurrent.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cache with check-and-swap behavior
 * @author mbardakov
 * @since 24.10.2020
 */
public class CASCache {
    private final Map<Integer, Base> cache;

    public CASCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * adds new element to the cache
     * @param model new element
     */
    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    /**
     * updates element
     * @param model
     */
    public void update(Base model) {
        var id = model.getId();
        var currentBase = cache.get(id);
        model.setVersion(currentBase.getVersion());
        cache.computeIfPresent(id, (x, y) -> {
            if (model.getVersion() != y.getVersion()) {
                throw new OptimisticException("Oops");
            }
            model.setVersion(model.getVersion() + 1);
            return model;
        });
    }

    /**
     * gets element from the cache
     * @param id model id
     * @return model
     */
    public Base get(int id) {
        return cache.get(id);
    }

    /**
     * deletes model
     * @param model model to delete
     */
    public void delete(Base model) {
        cache.remove(model.getId());
    }
}

package ru.job4j.concurrent.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mbarakov
 * @since 18.10.2020
 */
@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> store = new ConcurrentHashMap<>();

    /**
     * adds user to the store
     * @param user user to add
     * @return success / failure
     */
    public synchronized boolean add(User user) {
        store.put(user.getId(), user);
        return true;
    }

    /**
     * updated user
     * @param user user to update
     * @return success / failure
     */
    public synchronized boolean update(User user) {
        var id = user.getId();
        store.put(id, new User(id, user.getAmount()));
        return true;
    }

    /**
     * deletes user
     * @param user user to delete
     * @return success / failure
     */
    public synchronized boolean delete(User user) {
        var id = user.getId();
        store.remove(id);
        return false;
    }

    /**
     * transfers money from one user to another
     * @param idFrom first user id
     * @param idTo second user id
     * @param amount amount of money to transfer
     * @return success / failure
     */
    public synchronized boolean transfer(int idFrom, int idTo, int amount)
                                            throws Exception {
        if (!store.containsKey(idFrom) || !store.containsKey(idTo)) {
            throw new InvalidUserException();
        }
        var fromUser = store.get(idFrom);
        var fromAmount = store.get(idFrom).getAmount();
        if (fromAmount < amount) {
            throw new NotEnoughMoneyException();
        }
        var toUser = store.get(idTo);
        var toAmount =  toUser.getAmount();
        fromUser.setAmount(fromAmount - amount);
        toUser.setAmount(toAmount + amount);
        return true;
    }
}

package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        List<Account> list = users.get(user);
        if (user != null && !list.contains(account)) {
            list.add(account);
        }
    }

    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
            }
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User user = findByPassport(passport);
        for (Account acc : users.get(user)) {
            if (acc.getRequisite().equals(requisite)) {
                result = acc;
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAcc = findByRequisite(srcPassport, srcRequisite);
        Account dstAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc != null && dstAcc != null && srcAcc.getBalance() >= amount) {
            rsl = true;
            srcAcc.setBalance(srcAcc.getBalance() - amount);
            dstAcc.setBalance(dstAcc.getBalance() + amount);
        }
        return rsl;
    }
}
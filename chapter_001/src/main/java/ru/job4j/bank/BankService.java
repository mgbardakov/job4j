package ru.job4j.bank;

import java.util.*;

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
        return users.entrySet().stream().filter(x -> x.getKey().getPassport().equals(passport)).findAny()
                    .map(Map.Entry::getKey).orElse(null);
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        try {
            result = users.get(Optional.ofNullable(findByPassport(passport))
                    .orElseThrow(() -> new UserNotFoundException("User not found"))).stream()
                    .filter(x -> x.getRequisite().equals(requisite))
                    .findAny().orElse(null);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
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
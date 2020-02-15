package ru.job4j.tracker;

import java.util.*;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();


    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(new Date().getTime() + new Random().nextLong());
    }

    /**
     * Метод возвращает массив всех объектов
     * @return - массив объектов
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Метод возвращает массив всех объектов с переданным именем
     * @param key - имя
     * @return - массив объектов
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }


    /**
     * Метод возвращает объект с переданным ID
     * @param id - ID
     * @return - найденный объект или null, если объект не найден
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод заменяет объект на переданный
     * @param id - ID
     * @param  item - объект на который производится замена
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        item.setId(id);
        items.set(index, item);
        return true;
    }

    /**
     * Метод удаляет объект
     * @param id - ID
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items.remove(index);
        return true;
    }
    /**
     * Метод возвращает индекс объекта по ID
     * @param id - ID
     * @return - индекс объекта или null, если объект не найден
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}
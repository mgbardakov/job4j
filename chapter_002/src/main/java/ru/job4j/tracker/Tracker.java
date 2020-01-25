package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items[this.position++] = item;
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
    public Item[] findAll() {
        if (position == 0) {
            return null;
        }
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод возвращает массив всех объектов с переданным именем
     * @param key - имя
     * @return - массив объектов
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int size = 0;
        for (int index = 0; index < position; index++) {
            if (items[index].getName().equals(key)) {
                result[size++] = items[index];
            }
        }
        if (size == 0) {
            return null;
        }
        return Arrays.copyOf(result, size);
    }


    /**
     * Метод возвращает объект с переданным ID
     * @param id - ID
     * @return - найденный объект или null, если объект не найден
     */
    public Item findById(String id) {
        if (indexOf(id) == -1) {
            return null;
        }
        return items[indexOf(id)];
    }

    /**
     * Метод заменяет объект на переданный
     * @param id - ID
     * @param  item - объект на который производится замена
     */
    public void replace(String id, Item item) {
        item.setId(id);
        items[indexOf(id)] = item;
    }

    /**
     * Метод удаляет объект
     * @param id - ID
     */
    public void delete(String id) {
        if (indexOf(id) == -1) {
            return;
        }
        System.arraycopy(items, indexOf(id) + 1, items, indexOf(id), position - indexOf(id));
        items[position] = null;
        position--;

    }
    /**
     * Метод возвращает индекс объекта по ID
     * @param id - ID
     * @return - индекс объекта или null, если объект не найден
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}
package ru.job4j.array;

/**
 * Check класс для поиска элемента массива
 * @author Maxim Bardakov (mgbardakov@gmail.com)
 * @since 12.01.2020
 * @version 1.0
 */
public class Check {
    /**
     * Проверка однородности массива
     * @param data - массив
     * @return result - однороден/неоднороден массив
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
          for (int i = 0; i < data.length; i++) {
              if(data[i] != data[0]){
                  result = false;
                  break;
              }
          }
        return result;
    }
}

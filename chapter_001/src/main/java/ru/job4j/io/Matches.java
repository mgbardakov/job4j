package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 11;
        while (count > 0) {
            System.out.print("Возьми от 1 до 3 спичек: ");
            int select = Integer.parseInt(input.nextLine());
            if (select >= 1 && select <= 3) {
                count = count - select;
                if (count > 0) {
                    System.out.printf("Осталось %s", count);
                    System.out.println();
                }
            } else {
                System.out.println("Столько спичек взять нельзя");
            }
        }
        System.out.println("Ты победил!");
    }
}

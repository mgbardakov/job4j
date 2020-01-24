package ru.job4j.pojo;

public class Library {
    private static Book[] shelf;

    private static void replace(int first, int second) {
        Book temp = shelf[first];
        shelf[first] = shelf[second];
        shelf[second] = temp;
    }

    public static void main(String[] args) {
        Book state = new Book("Государство", 100);
        Book code = new Book("Clean code", 350);
        Book ethic = new Book("Никомахова этика", 97);
        Book biography = new Book("Pumping Iron", 124);
        shelf = new Book[]{state, code, ethic, biography};
        for (int index = 0; index < shelf.length; index++) {
            System.out.println(shelf[index]);
        }
        System.out.println();
        replace(0, 3);
        for (int index = 0; index < shelf.length; index++) {
            System.out.println(shelf[index]);
        }
        System.out.println();

        for (int index = 0; index < shelf.length; index++) {
            if (shelf[index].getName().equals("Clean code")) {
                System.out.println(shelf[index]);
            }
        }



    }
}

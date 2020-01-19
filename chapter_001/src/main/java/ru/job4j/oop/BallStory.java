package ru.job4j.oop;


public class BallStory {
    public static void main(String[] args) {
        Ball kolobok = new Ball();
        Hare hare = new Hare();
        Wolf grey = new Wolf();
        Fox cunning = new Fox();

        hare.tryEat(kolobok);
        grey.tryEat(kolobok);
        cunning.tryEat(kolobok);
        cunning.eat(kolobok);
    }
}

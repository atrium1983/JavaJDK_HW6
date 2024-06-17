package ru.gb;

public class Main {
    public static void main(String[] args) {
        MontyHallGame game = new MontyHallGame(3,1000);
        game.startGame();
        System.out.println(game.getStatistic());
    }
}

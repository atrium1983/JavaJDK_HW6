package ru.gb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MontyHallGame {
    private int doors;
    private int attempts;
    private int whereIsPrice;
    private int playerChoice;
    private int montyDoor;
    private Map<Integer, Boolean> stayStatistic;
    private Map<Integer, Boolean> changeStatistic;
    public MontyHallGame(int doors, int attempts) {
        this.doors = doors;
        this.attempts = attempts;
        stayStatistic = new HashMap<>();
        changeStatistic = new HashMap<>();
    }

    public void ifStay(){
        for (int i = 0; i < attempts; i++) {
            whereIsPrice = getRandom();
            playerChoice = getRandom();
            do{
                montyDoor = getRandom();
            } while (montyDoor == whereIsPrice || montyDoor == playerChoice);
            if(playerChoice == whereIsPrice){
                stayStatistic.put(i, true);
            } else {
                stayStatistic.put(i, false);
            }
        }
    }

    public void ifChange(){
        for (int i = 0; i < attempts; i++) {
            whereIsPrice = getRandom();
            playerChoice = getRandom();
            do{
                montyDoor = getRandom();
            } while (montyDoor == whereIsPrice || montyDoor == playerChoice);
            if(whereIsPrice != playerChoice){
                changeStatistic.put(i, true);
            } else {
                changeStatistic.put(i, false);
            }
        }
    }

    public int getWinStatistic(Map<Integer, Boolean> statistic){
        int wins = 0;
        for (Map.Entry<Integer, Boolean> element : statistic.entrySet()) {
            if (element.getValue()) {
                wins++;
            }
        }
        return wins;
    }

    public int getRandom(){
        return ThreadLocalRandom.current().nextInt((doors));
    }

    public void startGame(){
        ifStay();
        ifChange();
    }

    public String getStatistic(){
        return "Игрок, который не менял первоначальное решение, выйграл: "
                + getWinStatistic(stayStatistic) + " из " + attempts + "\n" +
                "Вероятность выигрыша составила => " + (getWinStatistic(stayStatistic) * 100 / attempts) + " %; \n" +
                "Игрок, который изменил первоначальное решение, выйграл: "
                + getWinStatistic(changeStatistic) + " из " + attempts + "\n" +
                "Вероятность выигрыша составила => " + (getWinStatistic(changeStatistic) * 100 / attempts) + " %;";
    }
}

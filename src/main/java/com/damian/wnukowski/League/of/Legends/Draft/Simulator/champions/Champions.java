package com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions;

import java.util.List;
import java.util.Random;

public class Champions {

    private List<String> champions;
    private Random random;

    public Champions(List<String> champions){
        this.champions = champions;
        random = new Random();
    }

    public boolean exists(String champion){
        return champions.contains(champion);
    }

    public String getRandomChamp(){
        int i = random.nextInt(champions.size());
        return champions.get(i);
    }

    public Iterable<String> getChampions(){
        return champions;
    }
}

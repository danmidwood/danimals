package com.danmidwood.danimals.selection;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.Coord;
import com.danmidwood.danimals.Population;

public class Roulette implements Selection {
    public Coord select(Population pop) {
        int totalFitness = pop.totalFitness();
        double fitnessSoFar = (double) 0;
        double aRandom = Math.random() * totalFitness;
        for (Coord key : pop.keySet()) {
            //for (int pointIndex=0; pointIndex<allStrings.size(); pointIndex++) {
            BitString thisString = (BitString) pop.get(key);
            fitnessSoFar += thisString.getFitness();
            if (fitnessSoFar >= aRandom) return key;
        }
        return null;

    }

}

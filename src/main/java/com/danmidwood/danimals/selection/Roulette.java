package com.danmidwood.danimals.selection;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.Coord;
import com.danmidwood.danimals.Population;

public class Roulette extends Selection {
    public Object select(Population pop) {
        int totalFitness = pop.totalFitness();
        int fitnessSoFar = 0;
        int aRandom = new Double(Math.random() * totalFitness).intValue();
        for (Coord key : pop.keySet()) {
            //for (int pointIndex=0; pointIndex<allStrings.size(); pointIndex++) {
            BitString thisString = (BitString) pop.get(key);
            fitnessSoFar += thisString.getFitness();
            if (fitnessSoFar >= aRandom) return key;
        }
        return null;

    }


    public boolean needsChild() {
        return false;
    }

    public boolean needsPreselectedString() {
        return false;
    }
}

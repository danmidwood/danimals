package com.danmidwood.danimals;

import java.util.ArrayList;
import java.util.BitSet;

public class BitString {
    private final BitSet self;
    private final ArrayList<Double> scores = new ArrayList<Double>();

    public BitString(BitSet self) {
        this.self = self;

    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < self.size(); i++) {
            if (self.get(i)) stringBuilder.append("1");
            else stringBuilder.append("0");

        }
        return stringBuilder.toString();
    }


    public void addScore(Double newScore) {
        scores.add(newScore);
    }


    public BitSet getSubBitSet(int fromIndex, int toIndex) {
        return self.get(fromIndex, toIndex);
    }


    /**
     * Get the fitness. An average of the composite of all
     * added scores.
     *
     * @return The fitness.
     */
    public double getFitness() {
        double totalScore = 0;
        for (Double score : scores) {
            totalScore = totalScore + score;
        }
        return totalScore / scores.size();
    }


    public BitSet getBits() {
        return self;
    }
}

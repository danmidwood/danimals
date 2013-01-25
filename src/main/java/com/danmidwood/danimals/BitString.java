package com.danmidwood.danimals;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author Dan Midwood
 * @version 0.1
 */
public class BitString extends BitSet {
    static int populationCount = 0;
    int id;
    double fitness = 0;
    ArrayList scores = new ArrayList();


    /**
     * Constructor for objects of class com.danmidwood.danimals.BitString
     */
    public BitString(int size) {
        super(size);
        populationCount++;
        id = populationCount;
    }

    public void setBits(BitString newBits) {
        System.out.println(doubleValue());
        clear();
        or(newBits);
        System.out.println(doubleValue());
    }

    public String toString() {
        String bitsAsString = new String();
        for (int i = 0; i < this.size(); i++) {
            if (get(i)) bitsAsString += "1";
            else bitsAsString += "0";

        }
        return bitsAsString;
    }


    /**
     * addScore
     * Add an achieved score. This will be used to calculate
     * the fitness of the string.
     *
     * @param newScore The score to add
     */
    public void addScore(double newScore) {
        scores.add(new Double(newScore));
        setFitness();
    }

    /**
     * A method to set the fitness to the average of all
     * currently achieved scores.
     */
    private void setFitness() {
        double totalScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            totalScore = totalScore + ((Double) scores.get(i)).doubleValue();
        }
        fitness = totalScore / scores.size();

    }

    /**
     * Get the value of a section of the string.
     *
     * @param fromIndex index of the first bit to include.
     * @param toIndex   index after the last bit to include.
     * @return double The value of the specified bits.
     */
    public double doubleValue(int fromIndex, int toIndex) {
        final double base = 2;
        double total = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            if (get(i)) {
                int power = toIndex - 1 - i;
                total = total + Math.pow(base, power);
            }
        }
        return total;
    }

    /**
     * Get the value of the whole string.
     *
     * @return The value of this com.danmidwood.danimals.BitString.
     */
    public double doubleValue() {
        return doubleValue(0, this.size());
    }


    /**
     * Get the fitness. An average of the composite of all
     * added scores.
     *
     * @return The fitness.
     */
    public double getFitness() {
        return fitness;
    }


    /**
     * Mutates the bitString. The likelyhood of mutation
     * will be specified by the user; Zero offers no mutation
     * while one will flip every bit. Values inbetween will act
     * accordingly on the string.
     *
     * @param mutateRate How likely a bit is to mutate.
     */
    public void mutate(double mutateRate) {
        for (int bit = 0; bit < size(); bit++) {
            if (mutateRate > Math.random()) {
                flip(bit);
            }
        }
    }


}

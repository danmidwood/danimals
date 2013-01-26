package com.danmidwood.danimals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Environment {
    int stringSize = 64;
    double mutateRate = 0.02;
    java.util.List<Integer> crossOverPoints = new java.util.ArrayList<Integer>();
    Game thisGame;
    Population pop;
    int roundsPerBattle = 50;

    private javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    final static int MUTATERATE_ALTERED = java.awt.AWTEvent.RESERVED_ID_MAX + 1;

    public Environment() {
        crossOverPoints.add(0);
        crossOverPoints.add(8);
        crossOverPoints.add(16);
        crossOverPoints.add(24);
        crossOverPoints.add(32);
        crossOverPoints.add(40);
        crossOverPoints.add(48);
        crossOverPoints.add(56);
        crossOverPoints.add(64);
    }


    public Population createPopulation(int rows, int cols) {
        pop = new Population(rows, cols);
        return pop;
    }

    public boolean crossOver(Coord paLocation, Coord maLocation) {
        try {
            //make random points
            Integer[] crossOverPoints = getCrossOverPoints().toArray(new Integer[getCrossOverPoints().size()]);
            BitString pa = (BitString) pop.getValueAt(paLocation);
            BitString ma = (BitString) pop.getValueAt(maLocation);
            GraphicalBitString sprog1 = new GraphicalBitString(getStringSize());
            GraphicalBitString sprog2 = new GraphicalBitString(getStringSize());
            for (int pointIndex = 0; pointIndex < crossOverPoints.length - 1; pointIndex++) {
                int startIndex = crossOverPoints[pointIndex];
                int endIndex = crossOverPoints[pointIndex + 1];
                boolean even = (pointIndex % 2) == 0;
                if (even) {
                    for (int thisBit = startIndex; thisBit < endIndex; thisBit++) {
                        sprog1.set(thisBit, pa.get(thisBit));
                        sprog2.set(thisBit, ma.get(thisBit));
                    }
                } else {
                    for (int thisBit = startIndex; thisBit < endIndex; thisBit++) {
                        sprog1.set(thisBit, ma.get(thisBit));
                        sprog2.set(thisBit, pa.get(thisBit));
                    }
                }
            }
            System.out.println("Crossed " + paLocation + " & " + maLocation);
            sprog1.mutate(getMutateRate());
            sprog2.mutate(getMutateRate());
            // Returns true when no more children can fit in
            return pop.addChildAt(sprog1, paLocation) || pop.addChildAt(sprog2, maLocation);
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
            return false;
        }
    }

    public void doBattle(Coord p1Location, Coord p2Location) {
        BitString p1 = (BitString) pop.getValueAt(p1Location);
        BitString p2 = (BitString) pop.getValueAt(p2Location);
        ArrayList<Result> history = new ArrayList<Result>();
        for (int roundNo = 0; roundNo < roundsPerBattle; roundNo++) {
            history.add(thisGame.doRound(p1, p2, history));
        }
        pop.processResults(p1Location, p2Location, history);
    }

    public void populate(double fillRatio, double initialMutate) {
        for (int thisSlot = 0; thisSlot < pop.getArea(); thisSlot++) {
            if (fillRatio > Math.random()) {
                GraphicalBitString newBits = new GraphicalBitString(getStringSize());
                newBits.mutate(initialMutate);
                pop.put(pop.getCoords(thisSlot), newBits);
            }
        }
    }


    public void fireAction(ActionEvent e) {
        ActionListener[] actionListeners = listeners.getListeners(ActionListener.class);
        for (ActionListener actionListener : actionListeners) {
            actionListener.actionPerformed(e);
        }
    }

    public void setPopulation(Population pop) {
        this.pop = pop;
    }

    public void addActionListener(ActionListener al) {
        listeners.add((Class<ActionListener>) al.getClass(), al);
    }

    public int getStringSize() {
        return stringSize;
    }

    public double getMutateRate() {
        return mutateRate;
    }

    public void setMutateRate(double newMutateRate) {
        ActionEvent ae = new ActionEvent(this, MUTATERATE_ALTERED, "The mutate rate did change");
        fireAction(ae);
        mutateRate = newMutateRate;
    }

    public java.util.List<Integer> getCrossOverPoints() {
        return crossOverPoints;
    }

    public Game getGame() throws Exception {
        if (thisGame == null) throw new Exception("The game is not currently defined");
        return thisGame;
    }

    public void setGame(Game newGame) {
        thisGame = newGame;
    }

}

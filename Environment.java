import java.awt.event.*;
/**
 * Write a description of class EnvironmentVars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Environment
{
    int stringSize = 64;
    double mutateRate = 0.02;
    java.util.List crossOverPoints = new java.util.ArrayList();
    Game thisGame;
    Population pop;
    int roundsPerBattle = 50;

    private javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    final static int MUTATERATE_ALTERED = java.awt.AWTEvent.RESERVED_ID_MAX + 1;
    final static int NEW_POINTS         = java.awt.AWTEvent.RESERVED_ID_MAX + 2;
    final static int NEW_BITSTRINGS     = java.awt.AWTEvent.RESERVED_ID_MAX + 3;

    public Environment() {
        crossOverPoints.add(new Integer(0));
        crossOverPoints.add(new Integer(8));
        crossOverPoints.add(new Integer(16));        
        crossOverPoints.add(new Integer(24));
        crossOverPoints.add(new Integer(32));
        crossOverPoints.add(new Integer(40));
        crossOverPoints.add(new Integer(48));
        crossOverPoints.add(new Integer(56));
        crossOverPoints.add(new Integer(64));
    }
    

    public Population createPopulation(int rows, int cols) { pop = new Population(rows, cols); return pop; }
    public boolean ready() { return (thisGame != null && pop != null); }
    
    
    
    public boolean crossOver(Coord paLocation, Coord maLocation) {
        try {
            //make random points
            int sections = getSections();
            Object[] crossOverPoints = getCrossOverPoints().toArray();
            BitString pa = (BitString)pop.getValueAt(paLocation);
            BitString ma = (BitString)pop.getValueAt(maLocation);
            GraphicalBitString sprog1 = new GraphicalBitString(getStringSize());
            GraphicalBitString sprog2 = new GraphicalBitString(getStringSize());
            for (int pointIndex=0; pointIndex<crossOverPoints.length-1; pointIndex++) {
                int startIndex = ((Integer)crossOverPoints[pointIndex]).intValue();
                int endIndex = ((Integer)crossOverPoints[pointIndex+1]).intValue();
                boolean even = (pointIndex % 2) == 0;
                if (even) {
                    for (int thisBit=startIndex; thisBit<endIndex; thisBit++) {
                        sprog1.set(thisBit, pa.get(thisBit));
                        sprog2.set(thisBit, ma.get(thisBit));
                    }
                } else {
                    for (int thisBit=startIndex; thisBit<endIndex; thisBit++) {
                        sprog1.set(thisBit, ma.get(thisBit));
                        sprog2.set(thisBit, pa.get(thisBit));
                    }
                }
            }
            System.out.println("Crossed " + paLocation + " & " + maLocation);
            sprog1.mutate(getMutateRate());
            sprog2.mutate(getMutateRate());
            // Returns true when no more children can fit in
            if (pop.addChildAt(sprog1, paLocation)) return true; // No room for second string
            if (pop.addChildAt(sprog2, maLocation)) return true;
            return false;
        }catch (Exception e) { System.out.println("Error : " + e.toString()); return false;}
    }
    
    public void doBattle(Coord p1Location, Coord p2Location) {
        BitString p1 = (BitString)pop.getValueAt(p1Location);
        BitString p2 = (BitString)pop.getValueAt(p2Location);
        java.util.ArrayList history = new java.util.ArrayList();
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
        ActionListener[] actionListeners = (ActionListener[])listeners.getListeners(ActionListener.class);        
        for (int listenerIndex=0; listenerIndex<actionListeners.length; listenerIndex++) {
            ActionListener al = (ActionListener)actionListeners[listenerIndex];
            al.actionPerformed(e);
        }
    }
    public void setPopulation(Population pop) { this.pop = pop; }
    public Population getPopulation() { return pop; }
    public void addActionListener(ActionListener al) { listeners.add(al.getClass(),al); }    
    public void removeActionListener(ActionListener al) { listeners.remove(al.getClass(), al); }    
    public int getStringSize() { return stringSize; }    
    public void setStringSize(int newStringSize) { stringSize = newStringSize; }    
    public double getMutateRate() { return mutateRate; }    
    public void setMutateRate(double newMutateRate) {
        ActionEvent ae = new ActionEvent(this, MUTATERATE_ALTERED, "The mutate rate did change");
        fireAction(ae);
        mutateRate = newMutateRate;
    }
    
    public java.util.List getCrossOverPoints() { return crossOverPoints; }    
    public void setCrossOverPoints(java.util.List newCrossOverPoints) { crossOverPoints = newCrossOverPoints; }    
    public int getSections() { return crossOverPoints.size() + 1; }    
    public Game getGame() throws Exception {
        if (thisGame == null) throw new Exception("The game is not currently defined");
        return thisGame;
    }
    
    public void setGame(Game newGame) { thisGame = newGame; }
    
}

package com.danmidwood.danimals;

/**
 * Write a description of class com.danmidwood.danimals.Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location extends Selection {
    int row;
    int col;

    public Location() {
        addParam(new String("radius"), new Integer(10));
    }


    public Object select(Population pop) throws Exception {
        if (!ready()) throw new Exception("Parameters not yet initialized");
        int radius = ((Integer) getParamValue("radius")).intValue();
        // Do not want to alter the current environment so create a
        // temp copy to play with.
        Population malleablePop = (Population) pop.clone();
        Coord currentLocation = (Coord) pop.getCurrentlySelected();
        int row = currentLocation.getRow();
        int col = currentLocation.getCol();
        java.util.Iterator allStrings = pop.keySet().iterator();
        while (allStrings.hasNext()) {
            Coord thisLocation = (Coord) allStrings.next();
            int rowDiff = row - thisLocation.getRow();
            int colDiff = col - thisLocation.getCol();
            double thisRadius = Math.sqrt((colDiff * colDiff) + (rowDiff * rowDiff));
            // The current object should be removed
            if (thisRadius == 0) malleablePop.remove(thisLocation);
            if (thisRadius > radius) {
                malleablePop.remove(thisLocation);
            }
        }
        return malleablePop;
    }


    public boolean needsChild() {
        return true;
    }

    public boolean needsPreselectedString() {
        return true;
    }
}

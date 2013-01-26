package com.danmidwood.danimals.selection;


import com.danmidwood.danimals.Coord;
import com.danmidwood.danimals.Population;

public class Location extends Selection {

    public Location() {
        addParam("radius", 10);
    }


    public Object select(Population pop) throws Exception {
        if (!ready()) throw new Exception("Parameters not yet initialized");
        int radius = (Integer) getParamValue("radius");
        // Do not want to alter the current environment so create a
        // temp copy to play with.
        Population malleablePop = (Population) pop.clone();
        Coord currentLocation = (Coord) pop.getCurrentlySelected();
        int row = currentLocation.getRow();
        int col = currentLocation.getCol();
        for (Coord thisLocation : pop.keySet()) {
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

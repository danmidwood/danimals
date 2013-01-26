package com.danmidwood.danimals.selection;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.Coord;
import com.danmidwood.danimals.Population;

public class Random implements Selection {

    public Coord select(Population pop) {
        Coord thisLocation = new Coord(-1, -1);
        Object bitty;
        boolean carryOn = true;
        while (carryOn) {
            int aRandomInt = (new Double(Math.floor(Math.random() * pop.size()))).intValue();
            thisLocation = pop.getCoords(aRandomInt);
            bitty = pop.getValueAt(thisLocation);
            carryOn = !(bitty instanceof BitString);
        }
        return thisLocation;
    }


}

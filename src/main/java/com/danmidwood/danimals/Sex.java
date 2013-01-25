package com.danmidwood.danimals;

/**
 * Write a description of class com.danmidwood.danimals.Sex here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sex extends Selection {

    public Sex() {
        put(new String("com.danmidwood.danimals.Sex bit"), new Integer(0));
        put(new String("Wanted value"), Boolean.valueOf(true));
    }

    public Object select(Population pop) throws Exception {
        boolean wantedSex = ((Boolean) getParamValue("Desired value")).booleanValue();
        int sexBit = ((Integer) getParamValue("com.danmidwood.danimals.Sex bit")).intValue();
        System.out.println("checking for " + sexBit + " being " + wantedSex);
        if (!ready()) throw new Exception("Parameters not yet initialized");
        // Do not want to alter the current environment so create a
        // temp copy to play with.
        Population malleablePop = (Population) pop.clone();
        java.util.Iterator allStrings = pop.keySet().iterator();
        while (allStrings.hasNext()) {
            java.awt.Point coords = (java.awt.Point) allStrings.next();
            BitString thisString = (BitString) malleablePop.get(coords);
            if (thisString.get(sexBit) != wantedSex) {
                // Remove all unwanted BitStrings
                malleablePop.remove(coords);
            }
        }
        return malleablePop;
    }


    public boolean needsChild() {
        return true;
    }

    public boolean needsPreselectedString() {
        return false;
    }

}

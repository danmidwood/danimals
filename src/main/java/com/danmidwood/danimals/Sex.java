package com.danmidwood.danimals;


public class Sex extends Selection {

    public Sex() {
        put(new String("com.danmidwood.danimals.Sex bit"), new Integer(0));
        put(new String("Wanted value"), Boolean.valueOf(true));
    }

    public Object select(Population pop) throws Exception {
        boolean wantedSex = (Boolean) getParamValue("Desired value");
        int sexBit = (Integer) getParamValue("com.danmidwood.danimals.Sex bit");
        System.out.println("checking for " + sexBit + " being " + wantedSex);
        if (!ready()) throw new Exception("Parameters not yet initialized");
        // Do not want to alter the current environment so create a
        // temp copy to play with.
        Population malleablePop = (Population) pop.clone();
        for (Coord coord : pop.keySet()) {
            BitString thisString = (BitString) malleablePop.get(coord);
            if (thisString.get(sexBit) != wantedSex) {
                // Remove all unwanted BitStrings
                malleablePop.remove(coord);
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

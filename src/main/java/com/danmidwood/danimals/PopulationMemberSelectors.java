package com.danmidwood.danimals;

import java.util.ArrayList;
import java.util.List;

public class PopulationMemberSelectors {


    static public Class[] getSelections() {
        List<Class> rtn = new ArrayList<Class>();
        rtn.add(Roulette.class);
        rtn.add(Sex.class);
        rtn.add(Location.class);
        rtn.add(Random.class);
        return rtn.toArray(new Class[rtn.size()]);
    }


}

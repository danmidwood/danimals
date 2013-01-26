package com.danmidwood.danimals.selection;

import com.danmidwood.danimals.Coord;
import com.danmidwood.danimals.Population;

public interface Selection {

    Coord select(Population pop) throws Exception;


}

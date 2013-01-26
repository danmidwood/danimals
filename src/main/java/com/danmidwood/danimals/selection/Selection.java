package com.danmidwood.danimals.selection;

import com.danmidwood.danimals.Population;

public interface Selection {

    Object select(Population pop) throws Exception;


}

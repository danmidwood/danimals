package com.danmidwood.danimals;

public interface GameModel {
    public boolean ready();

    public java.util.Set unsetValues();

    public Object addResult(String key, Result res) throws Exception;
}

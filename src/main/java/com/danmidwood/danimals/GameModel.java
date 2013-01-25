package com.danmidwood.danimals;

/**
 * Write a description of interface com.danmidwood.danimals.GameModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface GameModel
{
    public void setName(String newName);
    
    public String getName();
    public boolean ready();
    //public com.danmidwood.danimals.Rule addRule(int outcome, int fromIndex, int toIndex) throws Exception;
    //public com.danmidwood.danimals.Clause addClause(com.danmidwood.danimals.Rule thisRule, int back, int oppChoice);
    /**
     * Return a set of all keys in the map with a null value.
     * @return Set of keys.
     */
    public java.util.Set unsetValues();
    public Object addResult(Object key, Result res)throws Exception ;
}

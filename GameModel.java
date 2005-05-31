
/**
 * Write a description of interface GameModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface GameModel
{
    public void setName(String newName);
    
    public String getName();
    public boolean ready();
    //public Rule addRule(int outcome, int fromIndex, int toIndex) throws Exception;
    //public Clause addClause(Rule thisRule, int back, int oppChoice);
    /**
     * Return a set of all keys in the map with a null value.
     * @return Set of keys.
     */
    public java.util.Set unsetValues();
    public Object addResult(Object key, Result res)throws Exception ;
}

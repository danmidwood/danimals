package danmidwood.danimals;

/**
 * Write a description of class Roulette here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roulette extends Selection
{
    public Object select(Population pop) {
        int totalFitness = pop.totalFitness();
        int fitnessSoFar = 0;
        int aRandom = new Double( Math.random() * totalFitness).intValue();
        java.util.Iterator allStrings = pop.keySet().iterator();
        while (allStrings.hasNext()) {
        //for (int pointIndex=0; pointIndex<allStrings.size(); pointIndex++) {
            Object key = allStrings.next();
            BitString thisString = (BitString)pop.get(key);
            fitnessSoFar += thisString.getFitness();
            if (fitnessSoFar >= aRandom) return key;
        }
        return null;

    }
    

    
    public boolean needsChild() {
        return false;
    }
    
    public boolean needsPreselectedString() {
        return false;
    }
}

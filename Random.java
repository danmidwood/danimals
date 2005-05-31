
/**
 * Write a description of class Random here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Random extends Selection
{

    public Object select(Population pop) {
        Coord thisLocation = new Coord(-1,-1);
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

    
    public boolean needsChild() {
        return false;
    }
    
    public boolean needsPreselectedString() {
        return false;
    }
    
}

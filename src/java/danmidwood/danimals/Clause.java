package danmidwood.danimals;

/**
 * Write a description of class Clause here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Clause {
    int howFarBack;
    int oppChoice;
    public Clause (int howFarBack, int oppChoice) {
        this.howFarBack = howFarBack;
        this.oppChoice = oppChoice;
    }
    
    public boolean clauseIsValid(java.util.ArrayList history, int opponentPlayerNo) {
        if (howFarBack > history.size()) {
            return false;
        } else {
            Result prevRes = (Result)history.get(history.size() - howFarBack);
            boolean rtn = (prevRes.getChoice(opponentPlayerNo) == oppChoice);
            return rtn;
        }
    }
    
    //private String getChoice() {
        
    
    public String toString() {
        return ("opponent chose <C>" + oppChoice + "</C> " + howFarBack + " rounds ago");
    }
}
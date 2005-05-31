
/**
 * Write a description of class Result here.
 * 
 * @author Dan Midwood
 * @version 1
 */
public class Result
{
    private int scores[];
    private int choices[];

    /**
     * Constructor for objects of class Result
     */
    public Result(int[] choices, int[] scores) {
        this.scores = scores;
        this.choices = choices;
    }
    
    public Result() {
        scores = new int[2];
        choices = new int[2];
    }
    
    public void setScore(int playerNo, int newScore) {
        scores[playerNo] = newScore;
    }
    
    /**
     * Get the score achieved by the specified player
     * @param int playerNo
     * @return int the score achieved
     */
    public int getScore(int playerNo) throws ArrayIndexOutOfBoundsException {
        try {
            return get(playerNo, scores);
        } catch (invalidPlayerNo e) {
            throw e;          
        }
    }
    
    
    /**
     * Get the score achieved by the specified player
     * @param int playerNo
     * @return int the score achieved
     */
    public int getChoice(int playerNo) {
        try {
            return get(playerNo, choices);
        } catch (invalidPlayerNo e) {
            throw e;
        }
    }  
    
    public void setChoice(int playerNo, int choice) throws IndexOutOfBoundsException{
        choices[playerNo] = choice;
    }
    
    /**
     * Get the a players value from the array
     * @param int playerNo
     * @param array the array to search
     * @return int the value of that player from the array
     */
    private int get(int playerNo, int[] array) throws ArrayIndexOutOfBoundsException {
        try {
            return array[playerNo];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new invalidPlayerNo("Please enter a valid player number (1 - " + array.length + ") : " + playerNo);          
        }
    }      
    
    /**
     * How many players are in this game?
     * @return     The number of players
     */
    public int getPlayerCount()
    {
        return scores.length;
    }
    
    
    public String toString() {
        String rtn = "";
        for (int i=0; i<scores.length; i++) {
            rtn += "Player " + i + ":" + getScore(i) + "\n";
        }
        return rtn;
    }
    
    

    


}

    class invalidPlayerNo extends ArrayIndexOutOfBoundsException {
        public invalidPlayerNo(String msg) {
            super(msg);
        }
    }
    
    
    

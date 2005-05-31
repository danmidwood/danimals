import java.util.ArrayList;

/**
 * Write a description of class Problem here.
 * 
 * @author Dan Midwood 
 * @version 1
 */
public class Game extends java.util.HashMap implements GameModel
{
    String name = "Untitled Game";
    Object[] choices;
    RuleParser parser;

    
    /**
     * Constructor for objects of class Problem
     */
    public Game(Object[] theChoices)
    {
        super(new Double(Math.pow(theChoices.length, 2)).intValue(), 1); // Set the size
        choices = theChoices;

        //int outcomes = new Double(Math.pow(choiceCount, playerCount)).intValue();
        addKeys();
    }
    
    
    public java.util.ArrayList getChoiceNums(String input) {
        java.util.List inputAsList = java.util.Arrays.asList(input.split("\\D+"));
        java.util.Iterator it = inputAsList.iterator();
        java.util.ArrayList rtn = new java.util.ArrayList();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) { 
                int value = Integer.parseInt((String)next);
                if (value >= 0 && value < choices.length) {
                    rtn.add(new Integer(value));
                }
            }
        }
        return rtn;
    }
    
    public java.util.ArrayList getChoiceNames(String input) {
        java.util.ArrayList nums = getChoiceNums(input);
        java.util.ArrayList names = new java.util.ArrayList();
        java.util.Iterator numsToNames = nums.iterator();
        while (numsToNames.hasNext()) {
           int choiceNo = ((Integer)numsToNames.next()).intValue();
           names.add( (String)getChoice(choiceNo));
        }
        return names;
    }
    
    public Object getChoice(int choiceNo) { return choices[choiceNo];  }
    public Object[] getChoices() { return choices; }
    
    public void setParser(RuleParser newParser) { this.parser = newParser; }
    public RuleParser getParser() { return parser; }
    
    public void setName(String newName) { this.name = newName; }    
    public String getName() { return name; }
        
    /**
     * Add all keys to the map
     */
    private void addKeys() {
        addKeys(0, new int[2]);
    }

    /**
     * Private method to add keys to the map.
     * Using iteration and counting from left to right ensures
     * all possible keys are added.
     * @param index The index of the current choice.
     * @param userChoices An array of integers where choice numbers are stored.
     */    
    private void addKeys(int index, int[] userChoices) {
        if (index == userChoices.length-1) {
            for (int thisChoice = 0; thisChoice < choices.length; thisChoice++) {
                userChoices[userChoices.length-1] = thisChoice;
                String key = new String();
                for (int i = 0; i < userChoices.length; i++) {
                    if (i > 0) key = key + "-";
                    key = key + userChoices[i];
                }
                put(key);
            }
        } else {
            for (int thisChoice = 0; thisChoice < choices.length; thisChoice++) {
                userChoices[index] = thisChoice;
                addKeys(index+1, userChoices);
            }
        }
    }
    
    
    /**
     * A simple put method to ease adding keys without values.
     * @param key The key to add.
     */
    private void put(Object key) {
        super.put(key, null);
    }
    
    
    /**
     * Return a set of all keys in the map with a null value.
     * @return Set of keys.
     */
    public java.util.Set unsetValues() {
        java.util.Iterator keysIt = keySet().iterator();
        java.util.TreeSet keysWithoutValue = new java.util.TreeSet();
        while (keysIt.hasNext()) {
            String thisKey = (String)keysIt.next();
            if (get(thisKey) == null) {
                keysWithoutValue.add(thisKey);
            }
        }
        return keysWithoutValue;
    }
    
    
    /**
     * Adds a result to the specified key. All keys should already be in the map
     * so if the search for it proves fruitless an error is thrown.
     * @param key The key to be altered.
     * @param res The result to be added
     * @return The previous value (Or null if there was not any previous)
     */
    public Object addResult(Object key, Result res) throws Exception {
        if (!containsKey(key)) throw new Exception("The key must already be in the map.");
        java.util.ArrayList choices = getChoiceNums(key.toString());
        res.setChoice(0, ((Integer)choices.get(0)).intValue());
        res.setChoice(1, ((Integer)choices.get(1)).intValue());
        return put(key, res);
    }
    
    public void addResult(int p1Choice, int p2Choice, int p1Score, int p2Score) throws Exception {
        int[] choices = {p1Choice, p2Choice};
        int[] scores = {p1Score, p2Score};
        String key = p1Choice + "-" + p2Choice;
        Result res = new Result(choices, scores);
        try {
            addResult(key, res);
        } catch (Exception e) {
            throw e;
        }
    }
        
        
    
    /**
     * Check if all results are set.
     * @return boolean Are all results in place.
     */
    public boolean ready() {
        if (unsetValues().isEmpty() && parser != null) return parser.ready();
        else return false;
    }
    


    

    
  
       
        
    
    /**
     * Pitch the combatants against each other
     * @param combatants An array of bitStrings
     * @param warSoFar The history of these two strings
     * @return The result from this battle
     */
    public Result doRound(BitString p1, BitString p2, ArrayList warSoFar) {
        String playerChoices = new String();
        playerChoices += (parser.parse(p1, warSoFar, 1));
        playerChoices += "-";
        playerChoices += (parser.parse(p2, warSoFar, 2));
        return (Result)get(new String(playerChoices));
    }
     
    
    

    
    

            


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//     
//     public void testAddScores(BitString[] combatants, ArrayList results) {
//         for (int i=0; i<results.size(); i++) {
//             for (int j=0; j<combatants.length; j++) {
//                 int thisScore = ((Result)results.get(i)).getScore(j+1);
//                 combatants[j].addScore(thisScore);
//             }
//         }
//     }
        
    








    
//     public void testBuild()
//     {
//         int[] ccChoices = {0, 0};
//         int[] ccOutcome = {3,3};
//         Result cc = new Result(ccChoices, ccOutcome);
//         int[] cdChoices = {0, 1};
//         int[] cdOutcome = {0,5};
//         Result cd = new Result(cdChoices, cdOutcome);
//         int[] ddChoices = {1, 1};
//         int[] ddOutcome = {1,1};
//         Result dd = new Result(ddChoices, ddOutcome);
//         int[] dcChoices = {1, 0};
//         int[] dcOutcome = {5,0};
//         Result dc = new Result(dcChoices, dcOutcome);
//         try {
//             addResult(new String("1-0"), dc);
//             addResult(new String("0-1"), cd);
//             addResult(new String("1-1"), dd);
//             addResult(new String("0-0"), cc);
//         } catch (Exception e) {
//             System.out.println(e.toString());
//         }
//         if(allResultsSet()) System.out.println("All results correctly set up");
//         try {
//             addRule(0, 0, 8);
//             addRule(1, 8, 16);
//             //addRule(1,17,32,1,0);
//         } catch (Exception e) {
//             System.out.println(e.toString());
//         }       
//     }
    
//     public void testWar(BitString[] bits) {
//         ArrayList results = testDoWar(bits, 10);
//         for (int i=0; i<results.size(); i++) {
//             System.out.println(results.get(i).toString());
//         }        
//     }
    
//     /**
//      * Method to start a round of battles between bitStrings
//      * @param combatants The bitstrings involved
//      * @param rounds How many battles to fight
//      * @return ArrayList Results of each battle in the war
//      */
//     public ArrayList testDoWar(BitString[] combatants, int rounds) {
//         ArrayList allRounds = new ArrayList();
//         for (int i=0; i<rounds; i++) {
//             System.out.println("Round: " + (i+1));
//         //    allRounds.add(doBattle(combatants,allRounds));
//         }
//         return allRounds;
//     }    
}

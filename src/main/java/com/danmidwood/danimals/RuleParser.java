package com.danmidwood.danimals;

import java.util.ArrayList;
import javax.swing.event.*;
/**
 * Write a description of class Parser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RuleParser implements javax.swing.ListModel
{
    ArrayList[] rules;
    Object[] choices;
    int currentChoice = 0;
    ListDataListener ldl = null;

    public RuleParser(Object[] choices) {
        this.choices = choices;
        // An array of ArrayLists, confusing? Nah
        rules = new ArrayList[getNoOfChoices()];
        for (int choiceNo=0; choiceNo< rules.length; choiceNo++) {
            rules[choiceNo] = new ArrayList();
        }
    }
    
    public int getNoOfChoices() { return choices.length; }
    public Object[] getChoices() { return choices; }
    public ArrayList[] getRules() { return rules; }
    public boolean ready() {
        return (rules[0].size() > 0 && rules[1].size() > 0);
    }
    
    /**
     * Parse the com.danmidwood.danimals.BitString according to all rules
     * @param BitString The string to parse
     * @param ArrayList A list containing results of all previous rounds
     * @param int This player number
     */
    public int parse(BitString thisString, ArrayList warSoFar, int thisPlayer) {
        int oppPlayer = getOpponentNo(thisPlayer);
        double[] probChoice = new double[getNoOfChoices()];
        // For all possible outcomes
        for (int thisChoice = 0; thisChoice<probChoice.length; thisChoice++) {
            // And for all rules accociated with the current choice
            for (int thisChoiceRuleIndex = 0; thisChoiceRuleIndex< rules[thisChoice].size(); thisChoiceRuleIndex++) {
                // Get the com.danmidwood.danimals.Rule
                Rule thisRule = (Rule)rules[thisChoice].get(thisChoiceRuleIndex);
                // Get the value of the section of the com.danmidwood.danimals.BitString specified in the rule
                double valueFromBitString = thisString.doubleValue(thisRule.getFromIndex(), thisRule.getToIndex());
                // Get the value from the com.danmidwood.danimals.Rule and add it to the current choices probability of being chosen
                probChoice[thisChoice] = probChoice[thisChoice] + thisRule.getValue(valueFromBitString, warSoFar, oppPlayer);
            }
        }
        // Return the choice with the highest value
        return indexWithHighestValue(probChoice);
    }
    
    
    
    /**
     * Get an opponent player number
     * @param int this players number
     * @return int an opponents player no
     */
    private int getOpponentNo(int thisPlayer) {
        if (thisPlayer >= 1) return 0;
        else return (thisPlayer + 1);
    }
    
    
    /**
     * Returns the index of the array containing the largest value
     * @param double[] Array of doubles
     * @return int An index of the array
     */
    private int indexWithHighestValue(double[] thisArray) {
        int index = 0;
        double value = thisArray[0];
        for (int i = 0; i<thisArray.length; i++) {
            if (thisArray[i] > value) {
                value = thisArray[i];
                index = i;
            }
        }
        return index;
    }
    
    
    /**
     * Add a parsing rule.
     * @param outcome The choice this rule affects.
     * @param fromIndex index of the first bit to include
     * @param toIndex index after the last bit to include
     * @return The rule added
     */    
    public Rule addRule(int outcome, int fromIndex, int toIndex) throws Exception {
        if (outcome > getNoOfChoices()) throw new Exception("Invalid outcome");
        try {
            rules[outcome].add(new Rule(fromIndex, toIndex));
            //((com.danmidwood.danimals.Rule)rules[outcome].get(rules[outcome].size()-1)).addClause(10, 0);
            fireIntervalAdded();
            return (Rule)rules[outcome].get(rules[outcome].size()-1);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void removeRule(int choiceNo, int ruleNo) {
        rules[choiceNo].remove(ruleNo);
        fireIntervalRemoved(ruleNo);
    }
    
    /**
     * Add a clause to an already established rule.
     * The clause works by looking back the the previous battles of the
     * current encounter. If (back) number of rounds ago the opponent chose
     * (oppChoice) then the clause will be valid and will then alter the
     * current round.
     * @param thisRule The rule to modify
     * @param back How far back in the history should the clause look
     * @param oppChoice What the opponent must have chosen
     */
    public Clause addClause(Rule thisRule, int back, int oppChoice) {
        return thisRule.addClause(back, oppChoice);
    }
    
    /**
     * Add a new rule and a clause to the rule.
     * To view info on rule clauses see the addClause method.
     * @param outcome The outcome this rule will affect
     * @param fromIndex index of the first bit to include
     * @param toIndex index after the last bit to include
     * @param back How far back in the history the clause will look
     * @param oppChoice What the opponent must have chosen
     * @return The new rule.
     */
    public Rule addRule(int outcome, int fromIndex, int toIndex, int back, int oppChoice) throws Exception {
        try {
            Rule thisRule = addRule(outcome, fromIndex, toIndex);
            addClause(thisRule, back, oppChoice);
            return thisRule;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setCurrentChoice (int newChoice) { if (newChoice < choices.length && newChoice >= 0) { currentChoice = newChoice; fireContentsChanged(); }}
    public int getCurrentChoice () { return currentChoice; }
    public void addListDataListener(ListDataListener l) { ldl = l; }
    public Object getElementAt(int index) { return rules[currentChoice].get(index); }
    public int getSize() { return getNoOfRules(currentChoice); }
    public int getNoOfRules(int choiceNo) { return rules[choiceNo].size(); }
    public int getNoOfRulesTotal() { return getNoOfRules(0) + getNoOfRules(1); }
    public void removeListDataListener(ListDataListener l)  { ldl = null; }
    private void fireContentsChanged() {
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        if (ldl != null) {
            ldl.contentsChanged(e);
        }
    }
    
    private void fireIntervalAdded() {
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, getSize(), getSize());
        if (ldl != null) {
            ldl.intervalAdded(e);
        }
    }
    
    private void fireIntervalRemoved(int removed) {
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, removed, removed);
        if (ldl != null) {
            ldl.intervalRemoved(e);
        }
    }
            
    
    
    


}

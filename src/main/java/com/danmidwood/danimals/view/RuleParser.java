package com.danmidwood.danimals.view;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.BitStringParser;
import com.danmidwood.danimals.Clause;
import com.danmidwood.danimals.Rule;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class RuleParser implements javax.swing.ListModel {
    List<List<Rule>> rules;
    List<String> choices;
    int currentChoice = 0;
    ListDataListener ldl = null;

    public RuleParser(List<String> choices) {
        this.choices = choices;
        rules = new ArrayList<List<Rule>>();
        for (int choiceNo = 0; choiceNo < getNoOfChoices(); choiceNo++) {
            rules.add(choiceNo, new ArrayList<Rule>());
        }
    }

    public int getNoOfChoices() {
        return choices.size();
    }

    public List<String> getChoices() {
        return choices;
    }

    public List<List<Rule>> getRules() {
        return rules;
    }

    public boolean ready() {
        return (rules.get(0).size() > 0 && rules.get(1).size() > 0);
    }

    public int parse(BitString thisString, ArrayList warSoFar, int thisPlayer) {
        int oppPlayer = getOpponentNo(thisPlayer);
        double[] probChoice = new double[getNoOfChoices()];
        // For all possible outcomes
        for (int thisChoice = 0; thisChoice < probChoice.length; thisChoice++) {
            // And for all rules accociated with the current choice
            for (int thisChoiceRuleIndex = 0; thisChoiceRuleIndex < rules.get(thisChoice).size(); thisChoiceRuleIndex++) {
                // Get the com.danmidwood.danimals.Rule
                Rule thisRule = rules.get(thisChoice).get(thisChoiceRuleIndex);
                // Get the value of the section of the com.danmidwood.danimals.BitString specified in the rule
                long valueFromBitString = new BitStringParser().value(thisString.getSubBitSet(thisRule.getFromIndex(), thisRule.getToIndex()));
                // Get the value from the com.danmidwood.danimals.Rule and add it to the current choices probability of being chosen
                probChoice[thisChoice] = probChoice[thisChoice] + thisRule.getValue(valueFromBitString, warSoFar, oppPlayer);
            }
        }
        // Return the choice with the highest value
        return indexWithHighestValue(probChoice);
    }


    /**
     * Get an opponent player number
     *
     * @param thisPlayer this players number
     * @return int an opponents player no
     */
    private int getOpponentNo(int thisPlayer) {
        if (thisPlayer >= 1) return 0;
        else return (thisPlayer + 1);
    }


    /**
     * Returns the index of the array containing the largest value
     *
     * @param thisArray Array of doubles
     * @return int An index of the array
     */
    private int indexWithHighestValue(double[] thisArray) {
        int index = 0;
        double value = thisArray[0];
        for (int i = 0; i < thisArray.length; i++) {
            if (thisArray[i] > value) {
                value = thisArray[i];
                index = i;
            }
        }
        return index;
    }


    /**
     * Add a parsing rule.
     *
     * @param outcome   The choice this rule affects.
     * @param fromIndex index of the first bit to include
     * @param toIndex   index after the last bit to include
     * @return The rule added
     */
    public Rule addRule(int outcome, int fromIndex, int toIndex) throws Exception {
        if (outcome > getNoOfChoices()) throw new Exception("Invalid outcome");
        rules.get(outcome).add(new Rule(fromIndex, toIndex));
        fireIntervalAdded();
        return rules.get(outcome).get(rules.get(outcome).size() - 1);
    }

    public void removeRule(int choiceNo, int ruleNo) {
        rules.get(choiceNo).remove(ruleNo);
        fireIntervalRemoved(ruleNo);
    }

    /**
     * Add a clause to an already established rule.
     * The clause works by looking back the the previous battles of the
     * current encounter. If (back) number of rounds ago the opponent chose
     * (oppChoice) then the clause will be valid and will then alter the
     * current round.
     *
     * @param thisRule  The rule to modify
     * @param back      How far back in the history should the clause look
     * @param oppChoice What the opponent must have chosen
     */
    public Clause addClause(Rule thisRule, int back, int oppChoice) {
        return thisRule.addClause(back, oppChoice);
    }

    public void setCurrentChoice(int newChoice) {
        if (newChoice < choices.size() && newChoice >= 0) {
            currentChoice = newChoice;
            fireContentsChanged();
        }
    }

    public void addListDataListener(ListDataListener l) {
        ldl = l;
    }

    public Object getElementAt(int index) {
        return rules.get(currentChoice).get(index);
    }

    public int getSize() {
        return getNoOfRules(currentChoice);
    }

    public int getNoOfRules(int choiceNo) {
        return rules.get(choiceNo).size();
    }

    public int getNoOfRulesTotal() {
        return getNoOfRules(0) + getNoOfRules(1);
    }

    public void removeListDataListener(ListDataListener l) {
        ldl = null;
    }

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

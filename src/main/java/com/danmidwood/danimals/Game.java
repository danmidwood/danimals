package com.danmidwood.danimals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static java.util.Arrays.asList;

public class Game extends java.util.HashMap<String, Result> implements GameModel {
    String name = "Untitled Game";
    String[] choices;
    RuleParser parser;


    public Game(String[] theChoices) {
        super(new Double(Math.pow(theChoices.length, 2)).intValue(), 1); // Set the size
        choices = theChoices;

        addKeys();
    }


    public List<Integer> getChoiceNumbers(String input) {
        List<String> inputAsList = asList(input.split("\\D+"));
        Iterator<String> it = inputAsList.iterator();
        List<Integer> rtn = new ArrayList<Integer>();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null) {
                int value = Integer.parseInt(next);
                if (value >= 0 && value < choices.length) {
                    rtn.add(value);
                }
            }
        }
        return rtn;
    }

    public ArrayList<String> getChoiceNames(String input) {
        List<Integer> nums = getChoiceNumbers(input);
        ArrayList<String> names = new ArrayList<String>();
        for (Integer choiceNo : nums) {
            names.add(getChoice(choiceNo));
        }
        return names;
    }

    public String getChoice(int choiceNo) {
        return choices[choiceNo];
    }

    public Object[] getChoices() {
        return choices;
    }

    public void setParser(RuleParser newParser) {
        this.parser = newParser;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    private void addKeys() {
        addKeys(0, new int[2]);
    }

    /**
     * Private method to add keys to the map.
     * Using iteration and counting from left to right ensures
     * all possible keys are added.
     *
     * @param index       The index of the current choice.
     * @param userChoices An array of integers where choice numbers are stored.
     */
    private void addKeys(int index, int[] userChoices) {
        if (index == userChoices.length - 1) {
            for (int thisChoice = 0; thisChoice < choices.length; thisChoice++) {
                userChoices[userChoices.length - 1] = thisChoice;
                String key = "";
                for (int i = 0; i < userChoices.length; i++) {
                    if (i > 0) key = key + "-";
                    key = key + userChoices[i];
                }
                put(key);
            }
        } else {
            for (int thisChoice = 0; thisChoice < choices.length; thisChoice++) {
                userChoices[index] = thisChoice;
                addKeys(index + 1, userChoices);
            }
        }
    }


    /**
     * A simple put method to ease adding keys without values.
     *
     * @param key The key to add.
     */
    private void put(String key) {
        put(key, null);
    }


    /**
     * Return a set of all keys in the map with a null value.
     *
     * @return Set of keys.
     */
    public java.util.Set unsetValues() {
        TreeSet<String> keysWithoutValue = new TreeSet<String>();
        for (String thisKey : keySet()) {
            if (get(thisKey) == null) {
                keysWithoutValue.add(thisKey);
            }
        }
        return keysWithoutValue;
    }


    /**
     * Adds a result to the specified key. All keys should already be in the map
     * so if the search for it proves fruitless an error is thrown.
     *
     * @param key The key to be altered.
     * @param res The result to be added
     * @return The previous value (Or null if there was not any previous)
     */
    public Object addResult(String key, Result res) throws Exception {
        if (!containsKey(key)) throw new Exception("The key must already be in the map.");
        List<Integer> choices = getChoiceNumbers(key);
        res.setChoice(0, choices.get(0));
        res.setChoice(1, choices.get(1));
        return put(key, res);
    }


    /**
     * Check if all results are set.
     *
     * @return boolean Are all results in place.
     */
    public boolean ready() {
        return unsetValues().isEmpty() && parser != null && parser.ready();
    }


    /**
     * Pitch the combatants against each other
     *
     * @param warSoFar The history of these two strings
     * @return The result from this battle
     */
    public Result doRound(BitString p1, BitString p2, ArrayList warSoFar) {
        String playerChoices = "";
        playerChoices += (parser.parse(p1, warSoFar, 1));
        playerChoices += "-";
        playerChoices += (parser.parse(p2, warSoFar, 2));
        return get(playerChoices);
    }

}

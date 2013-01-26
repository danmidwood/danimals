package com.danmidwood.danimals;


public class Result {
    private int scores[];
    private int choices[];

    public Result() {
        scores = new int[2];
        choices = new int[2];
    }

    public void setScore(int playerNo, int newScore) {
        scores[playerNo] = newScore;
    }

    public int getScore(int playerNo) throws ArrayIndexOutOfBoundsException {
        try {
            return get(playerNo, scores);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public int getChoice(int playerNo) {
        try {
            return get(playerNo, choices);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public void setChoice(int playerNo, int choice) throws IndexOutOfBoundsException {
        choices[playerNo] = choice;
    }


    private int get(int playerNo, int[] array) throws ArrayIndexOutOfBoundsException {
        try {
            return array[playerNo];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a valid player number (1 - " + array.length + ") : " + playerNo);
        }
    }


    public String toString() {
        String rtn = "";
        for (int i = 0; i < scores.length; i++) {
            rtn += "Player " + i + ":" + getScore(i) + "\n";
        }
        return rtn;
    }


}

    
    
    

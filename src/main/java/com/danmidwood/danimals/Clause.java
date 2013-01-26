package com.danmidwood.danimals;

public class Clause {
    int howFarBack;
    int oppChoice;

    public Clause(int howFarBack, int oppChoice) {
        this.howFarBack = howFarBack;
        this.oppChoice = oppChoice;
    }

    public boolean clauseIsValid(java.util.ArrayList history, int opponentPlayerNo) {
        if (howFarBack > history.size()) {
            return false;
        } else {
            Result prevRes = (Result) history.get(history.size() - howFarBack);
            return (prevRes.getChoice(opponentPlayerNo) == oppChoice);
        }
    }


    public String toString() {
        return ("opponent chose <C>" + oppChoice + "</C> " + howFarBack + " rounds ago");
    }
}
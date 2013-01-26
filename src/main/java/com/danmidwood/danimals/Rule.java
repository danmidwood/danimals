package com.danmidwood.danimals;

import java.util.ArrayList;
import java.util.List;

public class Rule extends Section {
    List<Clause> clauses = new ArrayList<Clause>();

    public Rule(int fromIndex, int toIndex) throws Exception {
        super(fromIndex, toIndex);
    }

    public Clause addClause(int back, int choice) {
        Clause newClause = new Clause(back, choice);
        clauses.add(newClause);
        return newClause;
    }

    public double getValue(double value, java.util.ArrayList history, int opponentPlayerNo) {
        // For all clauses
        for (Clause thisClause : clauses) {
            // If the clause is not valid
            if (!thisClause.clauseIsValid(history, opponentPlayerNo)) {
                return 0;
            }
        }
        return value;
    }


    public boolean hasClause() {
        return (!clauses.isEmpty());
    }

    public String toString() {
        String rtn = "Add (" + fromIndex + "-" + toIndex + ")";
        if (hasClause()) {
            java.util.Iterator it = clauses.iterator();
            int clauseCount = 0;
            while (it.hasNext()) {
                if (clauseCount++ == 0) rtn = rtn.concat(" if ");
                else rtn = rtn.concat(" and ");
                Clause thisClause = (Clause) it.next();
                rtn = rtn.concat(thisClause.toString());
            }
        }
        return rtn;
    }

}


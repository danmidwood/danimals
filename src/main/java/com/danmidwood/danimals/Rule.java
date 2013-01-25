package com.danmidwood.danimals;

/**
 * Parse rules for converting a bit string
 * to meaningful data. 
 * @author Dan Midwood 
 * @version 1
 */
public class Rule extends Section
{
    java.util.ArrayList clauses = new java.util.ArrayList();

    public Rule(int fromIndex, int toIndex) throws Exception{
        super(fromIndex, toIndex);
    }
    
    public Clause addClause(int back, int choice) {
        Clause newClause = new Clause(back, choice);
        clauses.add(newClause);
        return newClause;
    }
    
    public java.util.List getClauses() { return clauses; }
    
    public double getValue(double value, java.util.ArrayList history, int opponentPlayerNo) {
        // For all clauses
        for (int i=0; i<clauses.size(); i++) {
            Clause thisClause = (Clause)clauses.get(i);
            // If the clause is not valid
            if (!thisClause.clauseIsValid( history, opponentPlayerNo ) ) {
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
            int clauseCount =0;
            while (it.hasNext()) {
                if (clauseCount++ == 0) rtn = rtn.concat(" if ");
                else rtn = rtn.concat(" and ");
                Clause thisClause = (Clause) it.next();
                rtn = rtn.concat( thisClause.toString() );
            }
        }
        return rtn;
    }

}


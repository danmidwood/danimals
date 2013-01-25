package com.danmidwood.danimals;

import javax.swing.*;
/**
 * Write a description of class com.danmidwood.danimals.ResultPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResultPanel extends JPanel
{
    JSpinner pOneScore = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JSpinner pTwoScore = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    Result res;
    
    public ResultPanel(Result newRes, String title) {
        this.res = newRes;
        setBorder(new javax.swing.border.TitledBorder(title));

        setLayout(new java.awt.GridLayout(2,3,10,0));
        try {
            pOneScore.setValue(new Integer(res.getScore(0)));
            pTwoScore.setValue(new Integer(res.getScore(1)));
        } catch (Exception e) { System.out.println(e); }
        this.add(new JLabel("P1 Score"));
        this.add(new JLabel("P2 Score"));
        this.add(pOneScore);
        this.add(pTwoScore);
    }
    
    public Result getResult() {
        applyChanges();
        return res;
    }
    
    private void applyChanges() {
        res.setScore(0, ((Integer)pOneScore.getValue()).intValue());
        res.setScore(1, ((Integer)pTwoScore.getValue()).intValue());
    }
        
}

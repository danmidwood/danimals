package com.danmidwood.danimals;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class com.danmidwood.danimals.SelectionParamPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionParamPanel extends JPanel implements ActionListener, ChangeListener
{
    java.util.HashMap tm = new java.util.HashMap();
    Selection selec;
    int inputCount = 0;
    
    public SelectionParamPanel(Selection newSelec) throws InvalidSelectionException{
        this.selec = newSelec;
        setBorder(new javax.swing.border.TitledBorder(newSelec.getClass().getName() + " selection"));
        java.util.Set paramKeys = selec.keySet();
        int howManyParams = paramKeys.size();
        if (howManyParams > 0) {
            setPreferredSize(new Dimension(200, howManyParams * 40));
            setLayout(new GridLayout(howManyParams, 2));
            addInputs(paramKeys);
        } else add(new JLabel("Nothing to configure"));
    }
    
    protected void addInputs(java.util.Set s) throws InvalidSelectionException{
        inputCount = s.size();
        setPreferredSize(new java.awt.Dimension(100, 50*inputCount));
        java.util.Iterator paramIt = s.iterator();
        while (paramIt.hasNext()) {
            Object curParam = paramIt.next();
            Class paramClass = selec.getParamClass(curParam);
            add(new JLabel(curParam.toString()));
            if (paramClass == Boolean.class) {
                Boolean[] bools = {Boolean.FALSE, Boolean.TRUE};
                SpinnerModel boolSpinnerModel = new SpinnerListModel(bools);
                JSpinner trueOrFalse = new JSpinner(boolSpinnerModel);
                trueOrFalse.addChangeListener(this);
                trueOrFalse.setValue(selec.getParamValue(curParam));
                add(trueOrFalse);
                tm.put(trueOrFalse, curParam);
            } else if (paramClass == Integer.class) {
                Integer thisNum = (Integer)selec.getParamValue(curParam);
                SpinnerModel numModel = new SpinnerNumberModel(thisNum.intValue(), thisNum.MIN_VALUE, thisNum.MAX_VALUE, 1);
                JSpinner num = new JSpinner(numModel);
                num.addChangeListener(this);
                num.setValue(selec.getParamValue(curParam));
                add(num);
                tm.put(num, curParam);
            } else if (paramClass == String.class){
                JTextField text = new JTextField((selec.getParamValue(curParam)).toString());
                text.addActionListener(this);
                tm.put(text, curParam);
                add(text);
            } else {
                throw new InvalidSelectionException("The current selection technique is invalid", paramClass);
            }
            
                
        }
    }
    public void stateChanged(ChangeEvent ce) {
        eventReceieved(ce.getSource());
    }
    
    public void actionPerformed(ActionEvent ae) {
        eventReceieved(ae.getSource());       
    }
    
    protected void eventReceieved(Object source) {
        java.util.Iterator sources = tm.keySet().iterator();
        while (sources.hasNext()) {
            Object currentSource = sources.next();
            if (source == currentSource) {
                Object curParam = tm.get(source);
                Object value;
                if (source instanceof JSpinner) value = ((JSpinner)source).getValue();
                else if (source instanceof JTextField) value = ((JTextField)source).getText(); 
                else value = selec.getParamValue(curParam);
                try {
                    selec.setParam(curParam, value);
                } catch (Exception e) { System.out.println(e.toString()); }
                
            }
        }
    }

    

}


package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class com.danmidwood.danimals.SelectionPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClassComboBox extends JComboBox
{
    protected Class wantedClass;
    
    public ClassComboBox(ComboBoxModel mod, Class wantedClass)
    {
        this.wantedClass = wantedClass;
        //ComboBoxModel newMod = getNewModel(mod);
        setModel(mod);
        setRenderer(new ClassRenderer());
    }
    
    public ClassComboBox(Object[] obs, Class wantedClass)
    {
        this.wantedClass = wantedClass;
        //ComboBoxModel newMod = getNewModel(mod);
        setModel(obs);
        setRenderer(new ClassRenderer());
    }
    
    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        System.out.println(ae.toString());
    }

    
    public void setModel(Object[] obs) {
        DefaultComboBoxModel newMod = new DefaultComboBoxModel();
        for (int listIndex = 0; listIndex < obs.length; listIndex++) {
            Object thisOb = obs[listIndex];
            addObject(thisOb, newMod);
        }
        newMod.setSelectedItem(null);
        super.setModel(newMod);
        
    }
    
    public void setModel(ComboBoxModel mod) {
        DefaultComboBoxModel newMod = new DefaultComboBoxModel();
        for (int listIndex = 0; listIndex < mod.getSize(); listIndex++) {
            Object thisOb = mod.getElementAt(listIndex);
            addObject(thisOb, newMod);
        }
        super.setModel(newMod);
    }
    
    private void addObject(Object thisOb, ComboBoxModel mod) {
        if (thisOb instanceof Class) {
            Class thisClass = (Class)thisOb;
            if (wantedClass.isAssignableFrom(thisClass)) {
                addClassToModel(mod, thisClass);
            }
        }
    }
    
    
    protected void addClassToModel(ComboBoxModel mod, Class value) {
        ((DefaultComboBoxModel)mod).addElement(value);
    }



}

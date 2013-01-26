package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClassComboBox extends JComboBox {
    protected Class wantedClass;

    public ClassComboBox(ComboBoxModel mod, Class wantedClass) {
        this.wantedClass = wantedClass;
        //ComboBoxModel newMod = getNewModel(mod);
        setModel(mod);
        setRenderer(new ClassRenderer());
    }

    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        System.out.println(ae.toString());
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
            Class thisClass = (Class) thisOb;
            if (wantedClass.isAssignableFrom(thisClass)) {
                addClassToModel(mod, thisClass);
            }
        }
    }


    protected void addClassToModel(ComboBoxModel mod, Class value) {
        ((DefaultComboBoxModel) mod).addElement(value);
    }


}

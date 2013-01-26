package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClassComboBox extends JComboBox {
    protected Class wantedClass;

    public ClassComboBox(ComboBoxModel mod, Class wantedClass) {
        this.wantedClass = wantedClass;
        setModel(mod);
        setRenderer(new ClassRenderer());
    }

    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        System.out.println(ae.toString());
    }


}

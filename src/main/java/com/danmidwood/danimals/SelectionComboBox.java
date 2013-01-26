package com.danmidwood.danimals;

import com.danmidwood.danimals.selection.Selection;

import javax.swing.*;
import java.awt.*;

public class SelectionComboBox extends ClassComboBox {
    protected int id;
    protected int playerNo;
    private Selection curSelection;

    public SelectionComboBox(ComboBoxModel mod, int playerNo) {
        this(mod, playerNo, 0);
    }

    private SelectionComboBox(ComboBoxModel mod, int playerNo, int id) {
        super(mod, Selection.class);
        this.id = id;
        this.playerNo = playerNo;
        int maxWid = new Double(getMaximumSize().getWidth()).intValue();
        setMaximumSize(new Dimension(maxWid, 20));
    }

    public Selection getSelection() {
        return curSelection;
    }

    public int getId() {
        return id;
    }

    public void setModel(ComboBoxModel mod) {
        super.setModel(mod);
    }


    public void fireItemStateChanged(java.awt.event.ItemEvent ie) {
        super.fireItemStateChanged(ie);
        if (ie.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            try {
                Class curClass = (Class) getSelectedItem();
                curSelection = (Selection) curClass.newInstance();
            } catch (Exception ignored) {
            } // Ignore the exception, don't pass it on
        }
    }


}

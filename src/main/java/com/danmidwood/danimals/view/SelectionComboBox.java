package com.danmidwood.danimals.view;

import com.danmidwood.danimals.selection.Selection;

import javax.swing.*;
import java.awt.*;

public class SelectionComboBox extends ClassComboBox {
    protected int playerNo;

    public SelectionComboBox(ComboBoxModel mod, int playerNo) {
        super(mod, Selection.class);
        this.playerNo = playerNo;
        int maxWid = new Double(getMaximumSize().getWidth()).intValue();
        setMaximumSize(new Dimension(maxWid, 20));
    }

    public Selection getSelection() {
        return (Selection) getSelectedItem();
    }


}

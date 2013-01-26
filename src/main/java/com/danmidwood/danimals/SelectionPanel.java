package com.danmidwood.danimals;

import com.danmidwood.danimals.selection.Selection;

import javax.swing.*;
import java.awt.*;


public class SelectionPanel extends JPanel {
    JButton editParam = new JButton("Edit");
    SelectionComboBox scb;
    JViewport vp;
    int playerNo;

    public SelectionPanel(int playerNo) {
        this(playerNo, BoxLayout.Y_AXIS, BorderLayout.SOUTH);
    }

    public SelectionPanel(int playerNo, int axis, String buttonPlacement) {
        this.playerNo = playerNo;
        setBorder(new javax.swing.border.TitledBorder("Player " + playerNo));
        if (buttonPlacement.equals(BorderLayout.CENTER)) buttonPlacement = BorderLayout.NORTH;
        setLayout(new BorderLayout());
        JPanel selectionContainer = new JPanel();
        selectionContainer.setLayout(new BoxLayout(selectionContainer, axis));
        scb = new SelectionComboBox(new DefaultComboBoxModel(), playerNo);

        selectionContainer.add(scb);
        showButton(false);
        add(editParam, buttonPlacement);
        JScrollPane selectionScroll = new JScrollPane(selectionContainer);
        selectionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vp = selectionScroll.getViewport();
        add(selectionScroll, BorderLayout.CENTER);
    }

    public void setModel(Selection[] obs) {
        scb.setModel(new DefaultComboBoxModel(obs));
    }

    public boolean isConfigured() {
        return scb.getSelection() != null;
    }

    private Selection getSelection() {
        return scb.getSelection();
    }

    public Coord select(Population pop) throws Exception {
        return getSelection().select(pop);

    }

    public void showButton(boolean on) {
        editParam.setVisible(on);
    }


}

package com.danmidwood.danimals;

import com.danmidwood.danimals.selection.Selection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


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
        scb = new SelectionComboBox(new javax.swing.DefaultComboBoxModel(), playerNo);

        selectionContainer.add(scb);
        showButton(false);
        add(editParam, buttonPlacement);
        JScrollPane selectionScroll = new JScrollPane(selectionContainer);
        selectionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vp = selectionScroll.getViewport();
        add(selectionScroll, BorderLayout.CENTER);
    }

    public void setModel(Class[] obs) {
        scb.setModel(new javax.swing.DefaultComboBoxModel(obs));
    }

    public boolean isConfigured() {
        return scb.getSelection() != null;
    }

    public java.util.List<Selection> getAllSelections() {
        ArrayList<Selection> rtn = new ArrayList<Selection>();
        rtn.add(scb.getSelection());

        rtn.trimToSize();
        return rtn;
    }

    public Coord select(Population pop) throws Exception {
        java.util.Iterator it = getAllSelections().iterator();
        try {
            Population workingPop = pop;
            while (it.hasNext()) {
                Object next = it.next();
                Selection nextSel = (Selection) next;
                Object afterSelection = nextSel.select(workingPop);
                if (afterSelection instanceof Coord) return (Coord) afterSelection;
                else if (afterSelection instanceof Population) workingPop = (Population) afterSelection;
                else {
                    System.out.println("Unexpected " + afterSelection.getClass().getName() + " found during selection");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in selection : " + e.getMessage());
        }
        throw new Exception("Player " + playerNo + " : Nothing found");
    }

    public void showButton(boolean on) {
        editParam.setVisible(on);
    }


}

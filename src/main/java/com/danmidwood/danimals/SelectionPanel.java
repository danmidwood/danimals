package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SelectionPanel extends JPanel implements ActionListener {
    Box getDetails = Box.createVerticalBox();
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
        scb.addActionListener(this);
        selectionContainer.add(scb);
        showButton(false);
        add(editParam, buttonPlacement);
        editParam.addActionListener(this);
        JScrollPane selectionScroll = new JScrollPane(selectionContainer);
        selectionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vp = selectionScroll.getViewport();
        add(selectionScroll, BorderLayout.CENTER);
    }

    public void setModel(Class[] obs) {
        scb.setModel(new javax.swing.DefaultComboBoxModel(obs));
    }

    public java.util.List<Selection> getAllSelections() {
        ArrayList<Selection> rtn = new ArrayList<Selection>();
        rtn.add(scb.getSelection());
        while (scb.hasChild()) {
            scb = scb.getChild();
            rtn.add(scb.getId(), scb.getSelection());
        }
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

    public void actionPerformed(java.awt.event.ActionEvent ae) {
        if (ae.getSource().getClass() == JButton.class) actionOnButton();
        else if (ae.getSource().getClass() == SelectionComboBox.class) actionOnCombo(ae);
    }

    public void actionOnCombo(java.awt.event.ActionEvent ae) {
        try {
            SelectionComboBox thisCombo = (SelectionComboBox) ae.getSource();
            //if (thisCombo.isPopupVisible()) return;
            Selection thisSelection = thisCombo.getSelection();
            if (thisSelection.needsChild()) {
                thisCombo.addChild();
                int newHeight = (new Double(vp.getViewPosition().getY() + thisCombo.getSize().getHeight())).intValue();
                vp.setViewPosition(new Point(0, newHeight));
            } else {
                if (thisCombo.hasChild()) {
                    thisCombo.removeChild();
                    vp.setViewPosition(new Point(0, 0));
                }
            }
            showButton(scb.needParams());
        } catch (Exception ignored) {
        } // Do nothing
    }

    private void actionOnButton() {
        getDetails.removeAll();
        SelectionComboBox thisCombo = scb;
        try {
            getDetails.add(new SelectionParamPanel(thisCombo.getSelection()));
            while (thisCombo.hasChild()) {
                SelectionComboBox child = thisCombo.getChild();
                SelectionParamPanel spp = new SelectionParamPanel(child.getSelection());
                getDetails.add(spp);
                thisCombo = child;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        JFrame f = new JFrame();
        f.getContentPane().add(getDetails);
        f.setSize(100, 100);
        f.show();
    }

}

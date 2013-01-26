package com.danmidwood.danimals.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideBar extends JPanel implements ActionListener {
    JComboBox selector = new JComboBox();
    CardLayout cards = new CardLayout();
    JPanel cardHolder = new JPanel(cards);

    public SideBar() {
        setLayout(new BorderLayout());
        selector.addActionListener(this);
        add(selector, BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(cardHolder, BorderLayout.CENTER);
    }

    public java.awt.Dimension getMinimumSize() {
        return new java.awt.Dimension(0, 400);
    }

    public void addCard(String name, Component toAdd) {
        for (int comboIndex = 0; comboIndex < selector.getItemCount(); comboIndex++) {
            Object ob = selector.getItemAt(comboIndex);
            String str = (String) ob;
            if (str.equals(name)) {
                System.out.println(str + " is equal to " + name);
                cardHolder.remove(toAdd);
                selector.removeItem(ob);
            }
        }
        cardHolder.add(name, toAdd);
        selector.addItem(name);
    }

    public void actionPerformed(ActionEvent ae) {
        Object selected = selector.getSelectedItem();
        if (selected == null) return;
        ((CardLayout) cardHolder.getLayout()).show(cardHolder, selected.toString());
    }

}

package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;

/**
 * The test class com.danmidwood.danimals.SelectionComboBoxTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SelectionComboBoxTest extends junit.framework.TestCase implements java.awt.event.ActionListener {
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /**
     * Default constructor for test class com.danmidwood.danimals.SelectionComboBoxTest
     */
    public SelectionComboBoxTest() {
    }

    /**
     * Sets up the test fixture.
     * <p/>
     * Called before every test case method.
     */
    protected void setUp() {
    }

    /**
     * Tears down the test fixture.
     * <p/>
     * Called after every test case method.
     */
    protected void tearDown() {
    }

    public void testFred() {
        javax.swing.JFrame bob = new javax.swing.JFrame("Bob");
        javax.swing.DefaultComboBoxModel moddy = new javax.swing.DefaultComboBoxModel();
        moddy.addElement(Random.class);
        moddy.addElement(Roulette.class);
        moddy.addElement(String.class);
        moddy.addElement(Location.class);
        moddy.addElement(Sex.class);


        bob.getContentPane().setLayout(new BorderLayout());
        SelectionComboBox one = new SelectionComboBox(moddy, 1);
        SelectionComboBox two = new SelectionComboBox(moddy, 2);
        one.addActionListener(this);
        two.addActionListener(this);
        bob.getContentPane().add(topPanel, BorderLayout.NORTH);
        bob.getContentPane().add(bottomPanel, BorderLayout.SOUTH);


        topPanel.add(one);
        bottomPanel.add(two);

        bob.setBounds(400, 400, 200, 200);
        bob.show();
    }

    public void actionPerformed(java.awt.event.ActionEvent ae) {
        try {
            SelectionComboBox thisCombo = (SelectionComboBox) ae.getSource();
            Selection thisSelection = (Selection) ((Class) thisCombo.getSelectedItem()).newInstance();
            if (thisSelection.needsChild()) {
                thisCombo.addChild();
            } else {
                if (thisCombo.hasChild()) {
                    thisCombo.removeChild();
                }
            }
        } catch (Exception e) {
        } // Do nothing

    }
}

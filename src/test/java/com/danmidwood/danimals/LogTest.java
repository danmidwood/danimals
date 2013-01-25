package com.danmidwood.danimals;

import javax.swing.*;

/**
 * The test class com.danmidwood.danimals.LogTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LogTest extends junit.framework.TestCase {
    /**
     * Default constructor for test class com.danmidwood.danimals.LogTest
     */
    public LogTest() {
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

    public void testLog() {
        JFrame bob = new JFrame();
        final JList lizzy = new JList(new Log());
        bob.getContentPane().add(lizzy);
        bob.setSize(40, 400);
        bob.show();
        System.out.print("fred smells of poo");
        bob.addWindowListener(new
                                      java.awt.event.WindowAdapter() {
                                          public void windowClosing(java.awt.event.WindowEvent e) {
                                              ((Log) lizzy.getModel()).dispose();
                                          }
                                      }
        );
    }
}

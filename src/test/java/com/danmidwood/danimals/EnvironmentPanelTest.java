package com.danmidwood.danimals;

import javax.swing.*;

/**
 * The test class com.danmidwood.danimals.EnvironmentPanelTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnvironmentPanelTest extends junit.framework.TestCase {
    /**
     * Default constructor for test class com.danmidwood.danimals.EnvironmentPanelTest
     */
    public EnvironmentPanelTest() {
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

    public void testEnvPanel() {
        JFrame bob = new JFrame();
        EnvironmentPanel ep = new EnvironmentPanel(new Environment());
        bob.getContentPane().add(ep);
        bob.setSize(40, 400);
        bob.show();
    }
}

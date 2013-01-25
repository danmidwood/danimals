package com.danmidwood.danimals;

/**
 * The test class com.danmidwood.danimals.LogPanelTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LogPanelTest extends junit.framework.TestCase {
    LogPanel lp;

    /**
     * Default constructor for test class com.danmidwood.danimals.LogPanelTest
     */
    public LogPanelTest() {
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

    public void testLogPanel() {
        javax.swing.JFrame bob = new javax.swing.JFrame();
        Log fred = new Log();
        lp = new LogPanel(fred);
        bob.getContentPane().add(lp);
        bob.setSize(40, 400);
        bob.show();
        System.out.println("fred smells of poo");
        lp.dispose();
    }

}

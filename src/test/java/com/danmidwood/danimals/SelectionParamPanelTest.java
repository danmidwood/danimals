package com.danmidwood.danimals;

import com.danmidwood.danimals.selection.Sex;

/**
 * The test class com.danmidwood.danimals.SelectionParamPanelTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SelectionParamPanelTest extends junit.framework.TestCase {
    Sex sexi = new Sex();

    /**
     * Default constructor for test class com.danmidwood.danimals.SelectionParamPanelTest
     */
    public SelectionParamPanelTest() {
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

    public void testParams() {
        java.awt.Toolkit.getDefaultToolkit().beep();
        javax.swing.JFrame f = new javax.swing.JFrame("Testing param enteration");
        try {
            SelectionParamPanel selec = new SelectionParamPanel(sexi);
            f.getContentPane().add(selec);
            f.show();
        } catch (InvalidSelectionException ise) {
            System.out.println(ise.toString() + ise.getInvalidClass());
        }

    }
}


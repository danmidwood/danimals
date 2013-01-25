package com.danmidwood.danimals;

/**
 * The test class com.danmidwood.danimals.ClassComboPanelTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClassComboPanelTest extends junit.framework.TestCase {
    /**
     * Default constructor for test class com.danmidwood.danimals.ClassComboPanelTest
     */
    public ClassComboPanelTest() {
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
        bob.getContentPane().setLayout(new java.awt.FlowLayout());
        javax.swing.DefaultComboBoxModel moddy = new javax.swing.DefaultComboBoxModel();
        moddy.addElement(Random.class);
        moddy.addElement(Roulette.class);
        moddy.addElement(String.class);


        bob.getContentPane().add(new ClassComboBox(moddy, Selection.class));
        bob.getContentPane().add(new ClassComboBox(moddy, String.class));
        bob.setBounds(400, 400, 200, 200);
        bob.show();
    }
}

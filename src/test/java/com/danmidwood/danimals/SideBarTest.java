package com.danmidwood.danimals;

/**
 * The test class com.danmidwood.danimals.SideBarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SideBarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class com.danmidwood.danimals.SideBarTest
     */
    public SideBarTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    public void testSideBar() {
        javax.swing.JFrame f = new javax.swing.JFrame();
        SideBar sb = new SideBar();
        f.getContentPane().add(sb);
        f.setSize(500,500);
        f.show();
        javax.swing.Box reproSelec = javax.swing.Box.createHorizontalBox();
        reproSelec.add(new SelectionPanel(1, javax.swing.BoxLayout.Y_AXIS, java.awt.BorderLayout.NORTH));
        reproSelec.add(new SelectionPanel(2, javax.swing.BoxLayout.Y_AXIS, java.awt.BorderLayout.NORTH));
        sb.addCard("com.danmidwood.danimals.Selection", reproSelec);
        String[] choices = {"Cooperate", "Defect"};
        RuleParser rp = new RuleParser(choices);
        RuleParserPanel rpp = new RuleParserPanel(rp);
        sb.addCard("Rules", rpp);
    }
}

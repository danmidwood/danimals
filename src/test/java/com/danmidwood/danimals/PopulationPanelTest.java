package com.danmidwood.danimals;

/**
 * The test class com.danmidwood.danimals.PopulationPanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PopulationPanelTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class com.danmidwood.danimals.PopulationPanelTest
     */
    public PopulationPanelTest()
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

    public void testPopulationPanel()
    {
        javax.swing.JFrame f = new javax.swing.JFrame();
        f.getContentPane().setLayout(new java.awt.BorderLayout());
        final PopulationPanel pp = new PopulationPanel();
        Population pop = new Population(10,10);
        Environment env = new Environment();
        env.setMutateRate(0.5);
        env.setPopulation(pop);
        pp.setPopulation(pop);
        env.populate(0.5, 0.5);
        f.getContentPane().add(pp, java.awt.BorderLayout.CENTER);
        javax.swing.JButton jb = new javax.swing.JButton("resize");
        f.getContentPane().add(jb, java.awt.BorderLayout.NORTH);

        jb.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent ae) {
                    BitGrid bg = pp.getBitGrid();
//                     bg.setRowHeight(100);
//                     bg.setColWidth(100);
                }
            });
        f.setSize(500,500);
        f.show();
    }
}


import javax.swing.*;

/**
 * The test class EnvironmentPanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EnvironmentPanelTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class EnvironmentPanelTest
     */
    public EnvironmentPanelTest()
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
    
    public void testEnvPanel() {
        JFrame bob = new JFrame();
        EnvironmentPanel ep = new EnvironmentPanel(new Environment());
        bob.getContentPane().add(ep);
        bob.setSize(40,400);
        bob.show();
    }
}

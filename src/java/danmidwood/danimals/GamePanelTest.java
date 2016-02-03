package danmidwood.danimals;

/**
 * The test class GamePanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GamePanelTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class GamePanelTest
     */
    public GamePanelTest()
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
    
    public void testInFrame() {
        javax.swing.JFrame f = new javax.swing.JFrame("game panel test");
        f.getContentPane().add(new GamePanel());
        f.show();
    }
}

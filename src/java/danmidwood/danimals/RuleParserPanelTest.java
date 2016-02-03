package danmidwood.danimals;

/**
 * The test class RuleParserPanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RuleParserPanelTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class RuleParserPanelTest
     */
    public RuleParserPanelTest()
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

    public void testRuleParserPanel()
    {
        javax.swing.JFrame f = new javax.swing.JFrame("Rule parser panel");
        String[] choices = {"Cooperate", "Defect"};
        RuleParser rp = new RuleParser(choices);
        f.getContentPane().add(new RuleParserPanel(rp));
        f.show();
    }
}




/**
 * The test class CoordTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CoordTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CoordTest
     */
    public CoordTest()
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

	public void testCheckSize()
	{
		Coord coord1 = new Coord(5, 5);
		assertEquals(5, coord1.getCol());
		assertEquals(5, coord1.getRow());
	}
}




/**
 * The test class ClassLoaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClassLoaderTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class ClassLoaderTest
     */
    public ClassLoaderTest()
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
    
    public void testLoad() {
        Class type = Selection.class;
        String dir = ".";
        Class[] classes = ClassLoader.getClasses(type, dir);
        for (int i=0; i<classes.length; i++) {
            System.out.println( classes[i].getName());
        }
    }
}

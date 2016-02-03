package danmidwood.danimals;

/**
 * The test class SelectionPanelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SelectionPanelTest extends junit.framework.TestCase
{
	private Danimals danimals1;

    /**
     * Default constructor for test class SelectionPanelTest
     */
    public SelectionPanelTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		danimals1 = new Danimals();
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testTestSelectionPanel()
	{

	    javax.swing.JFrame frame = new javax.swing.JFrame("Selection test");
	    SelectionPanel selec1 = new SelectionPanel(1,javax.swing.BoxLayout.Y_AXIS, java.awt.BorderLayout.NORTH);
	    SelectionPanel selec2 = new SelectionPanel(2,javax.swing.BoxLayout.Y_AXIS, java.awt.BorderLayout.SOUTH);
	    frame.getContentPane().setLayout(new java.awt.GridLayout(2,1,10,0));
	    frame.getContentPane().add(selec1);
	    frame.getContentPane().add(selec2);
	    javax.swing.DefaultComboBoxModel moddy = new javax.swing.DefaultComboBoxModel();
	    Class type = Selection.class;
        String dir = ".";
        Object[] obs = ClassLoader.getClasses(type, dir);
        //         moddy.addElement(Random.class);
//         moddy.addElement(Roulette.class);
//         moddy.addElement(String.class);
//         moddy.addElement(Location.class);
//         moddy.addElement(Sex.class);
        selec1.setModel(obs);
        selec2.setModel(obs);
        frame.setSize(400,100);
	    frame.show();
	    
	}
}


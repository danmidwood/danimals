package danmidwood.danimals;

import javax.swing.*;
import javax.swing.table.*;

/**
 * The test class ColourPickerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ColorPickerTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class ColourPickerTest
     */
    public ColorPickerTest()
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
    
    public void testButton() {
        JFrame f = new JFrame();
        f.setSize(500,500);
        DefaultTableModel dtm = new DefaultTableModel(new String[] {"fred", "bob", "color"}, 10);
        
        JTable t = new JTable(dtm);
        TableColumn tc = t.getColumn(new String("color"));
        tc.setCellRenderer(new ColorPicker());
        tc.setCellEditor(new ColorPicker());
        f.getContentPane().add(t);
        f.show();
        //t.getTableModel.getColumn
    }
}

import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class BitGrid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BitGrid extends javax.swing.JTable // implements ListSelectionListener //java.awt.event.ActionListener
{
    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    int boxSize = 20;

   
    public BitGrid(Population pop)
    {
        super(pop); // Set the population to be the table data
        setOpaque(false);
        //env.addActionListener(this);
        setShowGrid(true);
        setGridColor(new java.awt.Color(253,253,253));
        rowSelectionAllowed = false; // We only want cell selection
        setRowHeight(boxSize);
        setColWidth(boxSize); //, new Double( boxSize * 1.5 ).intValue());
        setIntercellSpacing(new java.awt.Dimension(5,5)); //boxSize, boxSize));
        setSelectionForeground(java.awt.Color.RED);
        getColumnModel().getSelectionModel().addListSelectionListener(this);
    }
    
   
    public void setRowHeight(int newHeight) { super.setRowHeight(newHeight); BitStringDisplay.setIconHeight(newHeight/2); }
    public void setColWidth(int newWidth) { setColWidth(newWidth, newWidth, newWidth); } 
    public void setColWidth(int newMinWidth, int newMaxWidth) { setColWidth(newMinWidth, newMaxWidth, newMaxWidth); }    
    public void setColWidth(int newMinWidth, int newMaxWidth, int newPrefWidth) { 
        BitStringDisplay.setIconWidth(newMinWidth/2);
        javax.swing.table.TableColumnModel tcm = getColumnModel();
        java.util.Enumeration enu = tcm.getColumns();
        while (enu.hasMoreElements()) {
            javax.swing.table.TableColumn thisCol = (javax.swing.table.TableColumn)enu.nextElement();
            thisCol.setMinWidth(newMinWidth);
            thisCol.setMaxWidth(newMaxWidth);
            if (newPrefWidth >= newMinWidth && newPrefWidth <= newMaxWidth) thisCol.setPreferredWidth(newPrefWidth);
        }
    }
    public void addCellSelectionListener( CellSelectionListener l) { listeners.add( CellSelectionListener.class, l); }    
    public void removeCellSelectionListener( CellSelectionListener l) { listeners.remove( CellSelectionListener.class, l); }    
    public void fireNewCellSelected(CellSelectionEvent e) {
        try {
            CellSelectionListener[] tableModelListeners = (CellSelectionListener[]) listeners.getListeners(CellSelectionListener.class);
            for (int listenerIndex=0; listenerIndex<tableModelListeners.length; listenerIndex++) {
                CellSelectionListener l = (CellSelectionListener)tableModelListeners[listenerIndex];
                l.cellSelectionChanged(e);
            }
        } catch (NullPointerException n) { }// A non-bitstring has been selected, no need to report error. just carry on regardless.
    }
    

    
    /**
     * A listener for the selected value changing. This is called automatically by the program
     * and will alert any object listening for changes.
     * @param ListSelectionEvent The event symbolising the changed value
     */
    public void valueChanged(ListSelectionEvent e)  {
        super.valueChanged(e);
        // If the value is not adjusting let all listeners know a new cell has been selected
        if (!e.getValueIsAdjusting()) {
            fireNewCellSelected(new CellSelectionEvent(this, getSelectedRow(), getSelectedColumn()));
        }
    }
    


        
    




}


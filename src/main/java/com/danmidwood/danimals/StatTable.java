package com.danmidwood.danimals;

import javax.swing.*;
/**
 * Write a description of class com.danmidwood.danimals.StatTable here.
 * 
 * @author Dan Midwood
 * @version 1.0
 */
public class StatTable extends JTable
{
    static int ROW_NAME = 0;
    static int SELECTED = 1;
    static int TOTAL = 2;
    static int AVERAGE = 3;
    static int MIN = 4;
    static int MAX = 5;
    static int COLOR = 6;

    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    
    
    public void addCellSelectionListener( CellSelectionListener l) { listeners.add( CellSelectionListener.class, l); }    
    public void removeCellSelectionListener( CellSelectionListener l) { listeners.remove( CellSelectionListener.class, l); }    
    
    public void fireNewCellSelected(CellSelectionEvent e) {
        try {
            CellSelectionListener[] tableModelListeners = (CellSelectionListener[]) listeners.getListeners(CellSelectionListener.class);
            for (int listenerIndex=0; listenerIndex<tableModelListeners.length; listenerIndex++) {
                CellSelectionListener l = (CellSelectionListener)tableModelListeners[listenerIndex];
                l.cellSelectionChanged(e);
            }
        } catch (NullPointerException n) { System.out.println("null error"); }// A non-bitstring has been selected, no need to report error. just carry on regardless.
    }
    
        
    /**
     * A listener for the selected value changing. This is called automatically by the program
     * and will alert any object listening for changes.
     * @param ListSelectionEvent The event symbolising the changed value
     */
//     public void valueChanged(javax.swing.event.ListSelectionEvent e)  {
//         super.valueChanged(e);
//         if (getSelectedColumn() == COLOR) {
//         // If the value is not adjusting let all listeners know a new cell has been selected
//         //if (!e.getValueIsAdjusting()) {
//             
//                 fireNewCellSelected(new com.danmidwood.danimals.CellSelectionEvent(this, getSelectedRow(), getSelectedColumn()));
//         }
//     }
    
    public void editingStopped(javax.swing.event.ChangeEvent ce) {
        super.editingStopped(ce);
        fireNewCellSelected(new CellSelectionEvent(this, getSelectedRow(), getSelectedColumn()));
    }
}

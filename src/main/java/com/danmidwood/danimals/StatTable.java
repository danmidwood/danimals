package com.danmidwood.danimals;

import javax.swing.*;


public class StatTable extends JTable {
    static int ROW_NAME = 0;
    static int SELECTED = 1;
    static int TOTAL = 2;
    static int AVERAGE = 3;
    static int MIN = 4;
    static int MAX = 5;
    static int COLOR = 6;

    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();


    public void addCellSelectionListener(CellSelectionListener l) {
        listeners.add(CellSelectionListener.class, l);
    }

    public void fireNewCellSelected(CellSelectionEvent e) {
        try {
            CellSelectionListener[] tableModelListeners = listeners.getListeners(CellSelectionListener.class);
            for (CellSelectionListener tableModelListener : tableModelListeners) {
                tableModelListener.cellSelectionChanged(e);
            }
        } catch (NullPointerException n) {
            System.out.println("A non-bitstring has been selected, no need to report error. just carry on regardless.");
        }
    }


    public void editingStopped(javax.swing.event.ChangeEvent ce) {
        super.editingStopped(ce);
        fireNewCellSelected(new CellSelectionEvent(this, getSelectedRow(), getSelectedColumn()));
    }
}

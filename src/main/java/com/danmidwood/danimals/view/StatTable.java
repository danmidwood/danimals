package com.danmidwood.danimals.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;


public class StatTable extends JTable {
    static int ROW_NAME = 0;
    static int SELECTED = 1;
    static int TOTAL = 2;
    static int AVERAGE = 3;
    static int MIN = 4;
    static int MAX = 5;
    static int COLOR = 6;

    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();


    public StatTable() {
        DefaultTableModel stats = new DefaultTableModel(new String[]{"", "Selected", "Total", "Average", "Minimum", "Maximum", "Colour"}, 0);
        setModel(stats);
        DecimalFormatRenderer decimalFormatRenderer = new DecimalFormatRenderer();
        getColumnModel().getColumn(TOTAL).setCellRenderer(decimalFormatRenderer);
        getColumnModel().getColumn(AVERAGE).setCellRenderer(decimalFormatRenderer);
        getColumnModel().getColumn(MIN).setCellRenderer(decimalFormatRenderer);
        getColumnModel().getColumn(MAX).setCellRenderer(decimalFormatRenderer);
    }

    public void addCellSelectionListener(CellSelectionListener l) {
        listeners.add(CellSelectionListener.class, l);
    }

    public void fireNewCellSelected(CellSelectionEvent e) {
        CellSelectionListener[] tableModelListeners = listeners.getListeners(CellSelectionListener.class);
        for (CellSelectionListener tableModelListener : tableModelListeners) {
            tableModelListener.cellSelectionChanged(e);
        }
    }


    public void editingStopped(javax.swing.event.ChangeEvent ce) {
        super.editingStopped(ce);
        fireNewCellSelected(new CellSelectionEvent(this, getSelectedRow(), getSelectedColumn()));
    }

    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        private static final DecimalFormat formatter = new DecimalFormat("#.00");

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // First format the cell value as required

            if (value != null) {
                value = formatter.format(value);
            }

            // And pass it on to parent class

            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}

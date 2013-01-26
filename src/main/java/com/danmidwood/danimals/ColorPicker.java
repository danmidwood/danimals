package com.danmidwood.danimals;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColorPicker extends AbstractCellEditor implements Icon, TableCellEditor, TableCellRenderer, ActionListener {

    final Color colours[] = {null, Color.RED, Color.GREEN, Color.BLUE};
    final String names[] = {"No colour", "Red", "Green", "Blue"};
    int selectedColour = 0;
    static Integer NO_COLOR = 0;
    JButton clicker = new JButton(names[0], this);

    public ColorPicker() {
        clicker.addActionListener(this);
    }


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return null; //value = NO_COLOR;
        //value = new Integer(0);
        Integer colorNo = (Integer) value;
        selectedColour = colorNo;
        return new JLabel(names[colorNo], this, SwingConstants.LEFT);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) return null;
        selectedColour = (Integer) value;
        return clicker;
    }


    public Object getCellEditorValue() {
        return selectedColour;
    }


    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (selectedColour < 3) selectedColour++;
        else selectedColour = 0;
        clicker.setText(names[selectedColour]);

    }


    public int getIconHeight() {
        return 10;
    }

    public int getIconWidth() {
        return 10;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Color thisColour = colours[selectedColour];
        if (thisColour == null) return;
        g.setColor(thisColour);
        g.fillRect(x, y, 10, 10);
    }


}
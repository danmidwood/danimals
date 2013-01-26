package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;

class ClassRenderer extends JLabel implements ListCellRenderer {
    public ClassRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        setText(value.getClass().getSimpleName());
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

        return this;
    }

}
package com.danmidwood.danimals.view;

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
        if (value != null) {
            setText(value.getClass().getSimpleName());
        }

        return this;
    }

}
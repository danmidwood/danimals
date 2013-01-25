package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;

class ClassRenderer extends JLabel implements ListCellRenderer, Icon {
     public ClassRenderer() {
         setOpaque(true);
     }
     
     public Component getListCellRendererComponent(
         JList list,
         Object value,
         int index,
         boolean isSelected,
         boolean cellHasFocus)
     {
         if (value instanceof Class) {
             Class cls = (Class)value;
             setText(cls.getName());
             try {
                Selection selec = (Selection)cls.newInstance();
                if (selec.hasParams()) { setIcon(this); } else setIcon(null);
            } catch (Exception e) {}

         }
         setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
         setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

         return this;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.RED);
        int iconSize = getIconHeight();
        g.drawRect(x, y, iconSize, iconSize);
        g.drawString("p", 
                    x + (iconSize/4),
                    y + iconSize - (iconSize/4) );
    }
    
    public int getIconHeight() {
        return getFont().getSize();
    }
    
    public int getIconWidth() {
        return getFont().getSize();
    }
    
 }
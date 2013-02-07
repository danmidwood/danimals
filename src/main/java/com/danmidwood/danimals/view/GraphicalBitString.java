package com.danmidwood.danimals.view;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.BitStringParser;
import com.danmidwood.danimals.Section;

import java.util.BitSet;

public class GraphicalBitString extends BitString implements javax.swing.Icon {
    int width = 0;
    int height = 0;

    private final BitStringParser parser = new BitStringParser();


    public GraphicalBitString(BitSet self) {
        super(self);
    }


    public int getIconHeight() {
        return height;
    }

    public int getIconWidth() {
        return width;
    }


    public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        int red = getColour(BitStringDisplay.R);
        int green = getColour(BitStringDisplay.G);
        int blue = getColour(BitStringDisplay.B);
        java.awt.Color thisColour = new java.awt.Color(red, green, blue);

        width = BitStringDisplay.getIconWidth(); //new Double(d.getWidth()).intValue();
        height = BitStringDisplay.getIconHeight(); //= new Double(d.getHeight()).intValue();

        int eyeLeft = width / 3;
        int eyeTop = height / 3;
        int eyeSize = 2;

        g.setColor(thisColour);
        g.fillOval(x + 1, y + 1, width - 1, height - 1);
        g.drawLine(width / 2, height / 3, x + width, y + height);
        g.drawLine(width / 2, height / 3, x, y + height);
        g.setColor(new java.awt.Color(1 - red, 1 - green, 1 - blue));
        g.fillOval(x + eyeLeft, y + eyeTop, eyeSize, eyeSize);
        g.fillOval(x + 1 - eyeSize + width - eyeLeft, y + eyeTop, eyeSize, eyeSize);
        g.dispose();

    }

    public int getColour(int colour) {
        try {
            java.util.Iterator thisColoursSections = BitStringDisplay.getSections(colour).iterator();
            int divider = 0;
            int total = 0;
            while (thisColoursSections.hasNext()) {
                divider++;
                Section curSec = (Section) thisColoursSections.next();
                int fromIndex = curSec.getFromIndex();
                int toIndex = curSec.getToIndex();
                int size = toIndex - fromIndex;
                double max = Math.pow(2, size);
                total += parser.value(this.getSubBitSet(fromIndex, toIndex)) / max;
            }
            if (total == 0) return total;
            else return total / divider;
        } catch (Exception e) {
            System.out.println("Error in colour section " + colour + " - " + e.toString());
            return 0;
        }
    }

}

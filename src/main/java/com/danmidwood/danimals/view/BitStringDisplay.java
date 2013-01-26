package com.danmidwood.danimals.view;

import com.danmidwood.danimals.Section;

import java.util.ArrayList;
import java.util.List;

public class BitStringDisplay {
    private static int iconHeight = 10;
    private static int iconWidth = 10;
    private static List<Section> red = new ArrayList<Section>();
    private static List<Section> green = new ArrayList<Section>();
    private static List<Section> blue = new ArrayList<Section>();

    static final int R = 1;
    static final int G = 2;
    static final int B = 3;


    public static void addSection(int colour, Section newSection) throws Exception {
        List<Section> curSec = getSections(colour);
        curSec.add(newSection);
    }

    public static void removeSection(Section toRemove) {
        if (red.contains(toRemove)) red.remove(toRemove);
        if (green.contains(toRemove)) green.remove(toRemove);
        if (blue.contains(toRemove)) blue.remove(toRemove);
    }


    public static List<Section> getSections(int whatColor) throws Exception {
        switch (whatColor) {
            case R:
                return red;
            case G:
                return green;
            case B:
                return blue;
            default:
                throw new Exception("Invalid colour choice");
        }
    }

    public static int getIconHeight() {
        return iconHeight;
    }

    public static int getIconWidth() {
        return iconWidth;
    }

    public static void setIconHeight(int newHeight) {
        iconHeight = newHeight;
    }

    public static void setIconWidth(int newWidth) {
        iconWidth = newWidth;
    }

}

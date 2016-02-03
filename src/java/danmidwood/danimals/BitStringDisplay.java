package danmidwood.danimals;

/**
 * Write a description of class BitStringDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BitStringDisplay 
{
    private static int iconHeight = 10;
    private static int iconWidth = 10;
    private static java.util.ArrayList red = new java.util.ArrayList();
    private static java.util.ArrayList green = new java.util.ArrayList();
    private static java.util.ArrayList blue = new java.util.ArrayList();
    
    static final int R = 1;
    static final int G = 2;
    static final int B = 3;



    

    
    public static void addSection(int colour, int fromIndex, int toIndex) throws Exception {
        java.util.ArrayList curSec = getSections(colour);
        curSec.add(new Section(fromIndex, toIndex));
    }
    
    public static void addSection(int colour, Section newSection) throws Exception {
        java.util.ArrayList curSec = getSections(colour);
        curSec.add(newSection);
    }
    
    public static void removeSection(Section toRemove) {
        if (red.contains(toRemove)) red.remove(toRemove);
        if (green.contains(toRemove)) green.remove(toRemove);
        if (blue.contains(toRemove)) blue.remove(toRemove);
    }
        

    public static java.util.ArrayList getSections(int whatColor) throws Exception {
        java.util.ArrayList currentSections;
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
    
    public static int getIconHeight() { return iconHeight; }    
    public static int getIconWidth() { return iconWidth; }
    public static void setIconHeight(int newHeight) { iconHeight = newHeight; }    
    public static void setIconWidth(int newWidth) { iconWidth = newWidth; }
    
}

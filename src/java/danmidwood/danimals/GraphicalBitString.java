package danmidwood.danimals;

/**
 * Write a description of class GraphicalBitString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraphicalBitString extends BitString implements javax.swing.Icon
{
    int width = 0;
    int height = 0;


    public GraphicalBitString( int size) {
        super(size);
    }

    
    public int getIconHeight() {
        return height;
    }
    
    public int getIconWidth() {
        return width;
    }
    
    
    public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        float red = getColour(BitStringDisplay.R);
        float green = getColour(BitStringDisplay.G);
        float blue = getColour(BitStringDisplay.B);
        java.awt.Color thisColour = new java.awt.Color(red,green,blue);
        java.awt.Color invertedColour = new java.awt.Color(1-red,1-green,1-blue);
        
        java.awt.Dimension d = c.getSize();
        width = BitStringDisplay.getIconWidth(); //new Double(d.getWidth()).intValue();
        height = BitStringDisplay.getIconHeight(); //= new Double(d.getHeight()).intValue();

        int eyeLeft = width / 3;
        int eyeTop = height / 3;
        int eyeSize = 2; //width / 10;
        int indent = width / 10;
//         g.setColor(invertedColour);
//         g.fillRect(x, y, x+width, y+height);
        g.setColor(thisColour);
        g.fillOval(x+1, y+1, width-1, height-1);
        g.drawLine(width/2, height/ 3, x+width, y+height );
        g.drawLine(width/2,  height/3, x, y+height );
        g.setColor(new java.awt.Color(1-red,1-green,1-blue));
        g.fillOval(x+eyeLeft, y+eyeTop, eyeSize, eyeSize);
        g.fillOval(x+1-eyeSize+width-eyeLeft, y+eyeTop, eyeSize, eyeSize);
        g.dispose();

    }

    public float getColour(int colour) {//throws Exception{
        try {
            java.util.Iterator thisColoursSections = BitStringDisplay.getSections(colour).iterator();
            int divider = 0;
            float total = 0;
            while (thisColoursSections.hasNext()) {
                divider++;
                Section curSec = (Section)thisColoursSections.next();
                int fromIndex = curSec.getFromIndex();
                int toIndex = curSec.getToIndex();
                int size = toIndex - fromIndex;
                float max = ( new Double( Math.pow( 2, size ) ) ).floatValue();
                total += ( new Double( doubleValue( fromIndex, toIndex ) ) ).floatValue() / max;
            }
            if (total == 0) return total;
            else return total / divider;
//             fromIndex = displayParser.get(colour, BitStringDisplay.FROM);
//             toIndex = displayParser.get(colour, BitStringDisplay.TO);
        } catch (Exception e) {
            System.out.println("Error in colour section " + colour + " - " + e.toString());
            return 0;
        }
    }

}

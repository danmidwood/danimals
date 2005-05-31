/**
 * 
 * Write a description of class Coord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coord implements Cloneable
{
    private int row;
    private int col;

    /**
     * Constructor for objects of class Key
     */
    public Coord(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
//     public int compareTo(Object objectToCompare) {
//         Coord otherCoord = (Coord)objectToCompare;
//         int otherCol = otherCoord.getCol();
//         int otherRow = otherCoord.getRow();
//         if (matchCols && matchRows) return 0; else return 1;
//     }

    public int hashCode() {
        int rowCode = row * 10000000;
        int colCode = col * 1000;
        return rowCode + colCode;
    }
    
    public boolean equals(Object otherOb) {
        if (!(otherOb instanceof Coord)) return false;
        Coord otherCoord = (Coord)otherOb;
        return (col == otherCoord.getCol() && row == otherCoord.getRow());
    }
    
    public String toString() {
        return ("(" + row + "," + col + ")");
    }
    
    public Object clone() {
        return new Coord(getRow(), getCol());
    }
        
        

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}

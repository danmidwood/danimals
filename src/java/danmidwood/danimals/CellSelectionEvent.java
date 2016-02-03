package danmidwood.danimals;

public class CellSelectionEvent extends java.util.EventObject
{
    int col;
    int row;
    
    public CellSelectionEvent(java.lang.Object source, int row, int col) {
        super(source);
        this.col = col;
        this.row = row;
    }
    

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
}
package com.danmidwood.danimals;

public class Coord implements Cloneable {
    private int row;
    private int col;


    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int hashCode() {
        int rowCode = row * 10000000;
        int colCode = col * 1000;
        return rowCode + colCode;
    }

    public boolean equals(Object otherOb) {
        if (!(otherOb instanceof Coord)) return false;
        Coord otherCoord = (Coord) otherOb;
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

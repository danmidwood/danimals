package com.danmidwood.danimals;

public class Section {
    int fromIndex;
    int toIndex;


    public Section(int fromIndex, int toIndex) throws Exception {
        if (fromIndex >= toIndex) throw new Exception("The end cannot come before the start");
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public boolean equals(Object objToCompare) {
        if (objToCompare instanceof Section) {
            Section toCompare = (Section) objToCompare;
            return (fromIndex == toCompare.fromIndex && toIndex == toCompare.toIndex);
        } else return false;
    }

    public int hashCode() {
        return (fromIndex * 1000000 + toIndex);
    }
}

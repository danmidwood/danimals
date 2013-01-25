package com.danmidwood.danimals;

import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
/**
 * Write a description of class com.danmidwood.danimals.Environment here.
 * 
 * @author Dan Midwood
 * @version 1
 */
public class Population extends AbstractTableModel implements Cloneable, java.util.Map
{
    EventListenerList listeners = new EventListenerList();
    java.util.HashMap data; // Contains the bitStrings
    int rows;
    int cols;
    Object currentlySelected;
    java.util.HashMap newData;



    /**
     * Constructor for objects of class com.danmidwood.danimals.Environment
     */
    public Population(int rows, int cols)
    {
        this.cols = cols;
        this.rows = rows;
        data = new java.util.HashMap(rows*cols);
        newData = new java.util.HashMap(rows*cols);
    }
    
    /**
     * Construct a clone of the com.danmidwood.danimals.Environment passed. All variables and the map
     * will be cloned but any editing of BitStrings contained in the map at the
     * time of cloning will be reflected in both Environments.
     */
    public Population(Population newPop) {
        this.cols = newPop.cols;
        this.rows = newPop.rows;
        this.currentlySelected = newPop.currentlySelected;
        this.data = new java.util.HashMap(newPop.data);
    }
    
   
    // Methods inherited from AbstractTableModel
    public void addTableModelListener(javax.swing.event.TableModelListener l) { listeners.add(javax.swing.event.TableModelListener.class,l); }    
    public void fireUpdate(TableModelEvent e) {
        javax.swing.event.TableModelListener[] tableModelListeners = (javax.swing.event.TableModelListener[])listeners.getListeners(javax.swing.event.TableModelListener.class);        
        for (int listenerIndex=0; listenerIndex<tableModelListeners.length; listenerIndex++) {
            TableModelListener tml = (TableModelListener)tableModelListeners[listenerIndex];
            tml.tableChanged(e);
        }
    }
    
    public Class getColumnClass(int col) { return javax.swing.Icon.class; }
    public int getColumnCount() { return cols; }
    public String getColumnName(int col) { return null; }
    public int getRowCount() { return rows; }
    public Object getValueAt(int row, int col) { return getValueAt(new Coord(row, col)); }
    public Object getValueAt(Coord location) { return get(location); }
    public Object getValueAt(int slot) { return getValueAt(getCoords(slot)); }
    public boolean isCellEditable(int row, int col) { return false; }
    public void removeTableModelListener(javax.swing.event.TableModelListener l) { listeners.remove(javax.swing.event.TableModelListener.class, l); }    
    public void setValueAt(Object newString, int slot) { put(getCoords(slot), newString); }
    public void setValueAt(Object newString, Coord coords) { put(coords, newString); }
    public void setValueAt(Object newString, int row, int col) { put(new Coord(row, col), newString); }
    public boolean addChildAt(Object newString, Coord coords) {
        Coord newLocation = (Coord)coords.clone();
        while (newData.get(newLocation) != null) {
            int newRow =  move(newLocation.getRow(), rows);
            int newCol = move(newLocation.getCol(), cols);
            newLocation = new Coord(newRow, newCol);
        }
        newData.put(newLocation, newString);
        return isItReady();
    }
    private boolean isItReady() {
        if (data.size() == newData.size()) {
            data = (java.util.HashMap)newData.clone();
            newData = new java.util.HashMap(rows*cols);
            TableModelEvent e = new TableModelEvent(this, 0, rows, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
            fireUpdate(e);
            return true;
        }
        return false;
    }
    
    private int move(int oldVal, int max) {
        int newVal = oldVal;
        double rand = Math.random();
        if (rand < 0.33) newVal--;
        else if (rand > 0.66) newVal++;
        
        if (newVal < 0 || newVal >= max) return move(oldVal, max);
        else return newVal;
    }
        

    // Methods inherited from Map
    public void clear() { data.clear(); }
    public boolean containsKey(Object thisKey) { return data.containsKey(thisKey); }    
    public boolean containsValue(Object thisOb) { return data.containsValue(thisOb); }    
    public java.util.Set entrySet() { return data.entrySet(); }
    public Object get(Object key) {
        currentlySelected = key;
        Coord thisSec = (Coord)key;
        Object bla = data.get(key);
        if (bla instanceof BitString) {
            java.awt.event.ActionEvent newSel = new java.awt.event.ActionEvent(bla, 0, "" + thisSec.getRow() + thisSec.getCol());
            fireNewSelected(newSel);
        }
        return bla;
    }
    public void addActionListener(java.awt.event.ActionListener l) { listeners.add(java.awt.event.ActionListener.class,l); }    
    public void fireNewSelected(ActionEvent e) {
        java.awt.event.ActionListener[] actionListeners = (java.awt.event.ActionListener[])listeners.getListeners(java.awt.event.ActionListener.class);        
        for (int listenerIndex=0; listenerIndex<actionListeners.length; listenerIndex++) {
            java.awt.event.ActionListener ae = (java.awt.event.ActionListener)actionListeners[listenerIndex];
            ae.actionPerformed(e);
        }
    }
    public boolean isEmpty() { return data.isEmpty(); }
    public java.util.Set keySet() { return data.keySet(); }   
    public Object put(Object key, Object value) {
        Coord location = (Coord)key;
        int row = location.getRow();
        int col = location.getCol();
        BitString old = (BitString)get(key);
        if (old != null) old.setBits((BitString)value);
        else data.put(location, value);
        TableModelEvent e = new TableModelEvent(this, row,row,col);
        fireUpdate(e);
        return null;
    }
    
    public void putAll(java.util.Map newData) { data.putAll(newData); }
    public Object remove(Object key) { return data.remove(key); }    
    public int size() { return data.size(); }
    public java.util.Collection values() { return data.values(); }    


    public int totalFitness() {
        int total = 0;
        java.util.Iterator allStrings = values().iterator();
        while (allStrings.hasNext()) {
            total += ((BitString)allStrings.next()).getFitness();
        }
        return total;
    }

    
    
    public void processResults(Coord p1, Coord p2, java.util.ArrayList history) {
        System.out.println(p1 + " vs " + p2);
        double p1Score = 0;
        double p2Score = 0;
        BitString p1String = (BitString)get(p1);
        BitString p2String = (BitString)get(p2);
        for (int resultIndex=0; resultIndex<history.size(); resultIndex++) {
            Result thisRound = (Result)history.get(resultIndex);
            p1Score += thisRound.getScore(0);
            p2Score += thisRound.getScore(1);
        }
        p1Score = p1Score / history.size();
        p2Score = p2Score / history.size();
        System.out.println(p1 + " scored " + p1Score);
        System.out.println(p2 + " scored " + p2Score);
        p1String.addScore(p1Score);
        p2String.addScore(p2Score);
    }

    
   
    public Coord getCoords(int slot) {
        int col = slot % cols;
        int row = slot / cols;
        return new Coord(row, col);
    }    


    

    
    public Object getCurrentlySelected() { return currentlySelected; }
    public int getArea() { return getColumnCount() * getRowCount(); }
    /**
     * A method to return a clone of the com.danmidwood.danimals.Environment.
     * All variables and the Map will be cloned but the BitStrings will not.
     * Thus adding and removing from the clone is allowed but alteration of
     * any com.danmidwood.danimals.BitString will be reflected in both Environments.
     * @return The clone of the current environment
     */
    public Object clone() { return new Population(this); }
}
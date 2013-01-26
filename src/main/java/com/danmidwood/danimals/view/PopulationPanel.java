package com.danmidwood.danimals.view;

import com.danmidwood.danimals.BitString;
import com.danmidwood.danimals.Rule;
import com.danmidwood.danimals.Section;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class PopulationPanel extends JSplitPane implements CellSelectionListener, TableModelListener {

    static String FITNESS = "Fitness";


    BitGrid bg;
    Population pop;
    JPanel bsInfo = new JPanel(new BorderLayout());
    JLabel selectedString = new JLabel("A Foray into Life. The Danimals.");
    StatTable popInfo = new StatTable();
    RuleParser parser;

    public PopulationPanel() {
        super(JSplitPane.VERTICAL_SPLIT);
    }

    public void setParser(RuleParser newParser) {
        parser = newParser;
        pop.addTableModelListener(this);
        updateDetails();
    }

    public BitGrid getBitGrid() {
        return bg;
    }

    public void setPopulation(Population pop) {
        this.pop = pop;
        setup();
    }

    private void setup() {
        bg = new BitGrid(pop);
        bg.addCellSelectionListener(this);
        add(bg);
        popInfo.getModel().addTableModelListener(this);
        popInfo.addCellSelectionListener(this);
        // Create the colour column of the stat table
        // The colour picker is slightly buggy
        TableColumn tc = popInfo.getColumn("Colour");
        ColorPicker cp = new ColorPicker();
        tc.setCellRenderer(cp);
        tc.setCellEditor(cp);
        bsInfo.add(new JScrollPane(popInfo), BorderLayout.CENTER);
        bsInfo.add(selectedString, BorderLayout.NORTH);
        add(bsInfo);

    }

    private void updateTable() {
        int noOfRules = parser.getNoOfRulesTotal();
        if (noOfRules + 3 != popInfo.getRowCount()) {
            DefaultTableModel mod = (DefaultTableModel) popInfo.getModel();
            mod.setRowCount(noOfRules + 3);
            int rowCount = 0;
            mod.setValueAt(FITNESS, rowCount++, StatTable.ROW_NAME);
            List<String> choices = parser.getChoices();
            List<List<Rule>> rules = parser.getRules();
            for (int choiceIndex = 0; choiceIndex < choices.size(); choiceIndex++) {
                mod.setValueAt(choices.get(choiceIndex), rowCount++, StatTable.ROW_NAME);
                for (Object o : rules.get(choiceIndex)) {
                    mod.setValueAt(ColorPicker.NO_COLOR, rowCount, StatTable.COLOR);
                    Rule thisRule = (Rule) o;
                    mod.setValueAt(thisRule, rowCount++, StatTable.ROW_NAME);
                }
            }
        }
    }


    private void updateDetails() {
        updateTable();
        int processedStrings = 0;
        DefaultTableModel dtm = (DefaultTableModel) popInfo.getModel();
        //for all rows of bitstrings
        for (int popRow = 0; popRow < pop.getRowCount(); popRow++) {
            // and all columns on each row
            for (int popCol = 0; popCol < pop.getColumnCount(); popCol++) {
                Object thisValue = pop.getValueAt(popRow, popCol);
                // if the cell contains a bitstring
                if (thisValue instanceof BitString) {
                    //increment the string counter
                    processedStrings++;
                    BitString thisString = (BitString) thisValue;
                    // For all rows in the display table
                    for (int rowNo = 0; rowNo < popInfo.getRowCount(); rowNo++) {
                        Object rowType = dtm.getValueAt(rowNo, 0);
                        if (rowType == FITNESS) {
                            double fitness = thisString.getFitness();
                            if (processedStrings == 1) {
                                popInfo.setValueAt(fitness, rowNo, StatTable.TOTAL);
                                popInfo.setValueAt(fitness, rowNo, StatTable.AVERAGE);
                                popInfo.setValueAt(fitness, rowNo, StatTable.MIN);
                                popInfo.setValueAt(fitness, rowNo, StatTable.MAX);
                            } else {
                                Double oldTotal = (Double) popInfo.getValueAt(rowNo, StatTable.TOTAL);
                                Double oldMin = (Double) popInfo.getValueAt(rowNo, StatTable.MIN);
                                Double oldMax = (Double) popInfo.getValueAt(rowNo, StatTable.MAX);
                                Double newTotal = oldTotal + fitness;
                                popInfo.setValueAt(newTotal, rowNo, StatTable.TOTAL);
                                Double newAvg = newTotal / processedStrings;
                                popInfo.setValueAt(newAvg, rowNo, StatTable.AVERAGE);
                                if (fitness < oldMin)
                                    popInfo.setValueAt(fitness, rowNo, StatTable.MIN);
                                if (fitness > oldMax)
                                    popInfo.setValueAt(fitness, rowNo, StatTable.MAX);
                            }
                        } else if (rowType instanceof Rule) {
                            Rule thisRule = (Rule) rowType;
                            int toIndex = thisRule.getToIndex();
                            int fromIndex = thisRule.getFromIndex();
                            double value = thisString.doubleValue(fromIndex, toIndex);
                            // If it is the first string then just reset the data, do not
                            // get the old data
                            if (processedStrings == 1) {
                                popInfo.setValueAt(value, rowNo, StatTable.TOTAL);
                                popInfo.setValueAt(value, rowNo, StatTable.AVERAGE);
                                popInfo.setValueAt(value, rowNo, StatTable.MIN);
                                popInfo.setValueAt(value, rowNo, StatTable.MAX);
                            } else {
                                Double oldTotal = (Double) popInfo.getValueAt(rowNo, StatTable.TOTAL);
                                Double oldMin = (Double) popInfo.getValueAt(rowNo, StatTable.MIN);
                                Double oldMax = (Double) popInfo.getValueAt(rowNo, StatTable.MAX);
                                Double newTotal = oldTotal + value;
                                popInfo.setValueAt(newTotal, rowNo, StatTable.TOTAL);
                                Double newAvg = newTotal / processedStrings;
                                popInfo.setValueAt(newAvg, rowNo, StatTable.AVERAGE);
                                if (value < oldMin)
                                    popInfo.setValueAt(value, rowNo, StatTable.MIN);
                                if (value > oldMax)
                                    popInfo.setValueAt(value, rowNo, StatTable.MAX);
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateDetails(BitString thisString) {
        updateTable();
        int col = StatTable.SELECTED;
        DefaultTableModel dtm = (DefaultTableModel) popInfo.getModel();
        // For all rows in the display table
        for (int rowNo = 0; rowNo < popInfo.getRowCount(); rowNo++) {
            Object rowType = dtm.getValueAt(rowNo, 0);
            if (rowType == FITNESS) {
                popInfo.setValueAt(thisString.getFitness(), rowNo, col);
            } else if (rowType instanceof Rule) {
                Rule thisRule = (Rule) rowType;
                int toIndex = thisRule.getToIndex();
                int fromIndex = thisRule.getFromIndex();
                double value = thisString.doubleValue(fromIndex, toIndex);
                popInfo.setValueAt(value, rowNo, col);
            }
        }
    }


    public void cellSelectionChanged(CellSelectionEvent cse) {
        int row = cse.getRow();
        int col = cse.getCol();
        Object src = cse.getSource();
        if (src == popInfo) {
            Object colorObj = popInfo.getValueAt(row, col);
            Integer colorNo = (Integer) colorObj;
            BitStringDisplay.removeSection((Section) popInfo.getValueAt(row, StatTable.ROW_NAME));
            try {
                BitStringDisplay.addSection(colorNo, (Section) popInfo.getValueAt(row, StatTable.ROW_NAME));
            } catch (Exception e) {
                System.out.println("Error in colour specification : " + e.toString());
            }
        } else if (src == bg) {
            BitString thisString = (BitString) pop.getValueAt(row, col);
            selectedString.setText("(" + row + "," + col + ") " + thisString.toString());
            updateDetails(thisString);
        }
    }


    public void tableChanged(TableModelEvent tme) {
        if (tme.getSource() == pop) {
            System.out.println("Mating complete");
            if (tme.getType() == TableModelEvent.UPDATE)
                updateDetails();
        }
    }

}
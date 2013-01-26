package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class RuleParserPanel extends JPanel implements java.awt.event.ActionListener {
    RuleParser rp;
    JList rules;
    JComboBox choices;
    JSpinner from = new JSpinner();
    JSpinner to = new JSpinner();
    JSpinner howFarBack;
    JComboBox oppChoice;
    JButton addRule;
    JButton removeRule;
    JButton editRule;
    JButton backButton;
    JButton addClause;
    //     JButton removeClause;
    Rule currentRule = null;


    CardLayout ruleOrClause = new CardLayout();


    public RuleParserPanel(RuleParser rp) {
        this.rp = rp;
        setLayout(ruleOrClause);
        // Create panel for inputs
        JPanel inputs = new JPanel(new GridLayout(3, 1));
        inputs.setBorder(new javax.swing.border.TitledBorder("Parse rules"));
        // The choices available for the bit strings
        List<String> choicesToAdd = rp.getChoices();
        choices = new JComboBox(choicesToAdd.toArray());
        choices.addActionListener(this);
        JPanel choiceHolder = new JPanel();
        choiceHolder.setBorder(new javax.swing.border.TitledBorder("Game choice"));
        choiceHolder.add(choices);
        inputs.add(choiceHolder);
        // Create spinners for rule start and end values
        SpinnerModel numModel = new SpinnerNumberModel(0, 0, 6400, 1);
        from = new JSpinner(numModel);
        JPanel fromBitHolder = new JPanel();
        fromBitHolder.setBorder(new javax.swing.border.TitledBorder("From bit"));
        fromBitHolder.add(from);
        SpinnerModel numModel2 = new SpinnerNumberModel(1, ((Integer) numModel.getNextValue()).intValue(), 6400, 1);
        to = new JSpinner(numModel2);
        JPanel toBitHolder = new JPanel();
        toBitHolder.setBorder(new javax.swing.border.TitledBorder("To bit"));
        toBitHolder.add(to);
        JPanel grid = new JPanel(new GridLayout(1, 2));
        grid.add(fromBitHolder);
        grid.add(toBitHolder);
        inputs.add(grid);
        JPanel ruleButtons = new JPanel();
        addRule = new JButton("Add");
        addRule.addActionListener(this);
        removeRule = new JButton("Remove");
        removeRule.addActionListener(this);
        editRule = new JButton("Edit");
        editRule.addActionListener(this);
        ruleButtons.add(addRule);
        ruleButtons.add(removeRule);
        ruleButtons.add(editRule);
        inputs.add(ruleButtons);

        Box ruleBox = Box.createVerticalBox();
        ruleBox.add(inputs);
        rules = new JList(rp);
        ruleBox.add(new JScrollPane(rules));
        add(ruleBox, "Rules");

        setupClausePanel();
    }

    private void setupClausePanel() {
        Box clauseBox = Box.createVerticalBox();
        JPanel inputs = new JPanel();
        inputs.setBorder(new javax.swing.border.TitledBorder("Rule clauses"));
        JPanel howFarBackHolder = new JPanel();
        howFarBackHolder.setBorder(new javax.swing.border.TitledBorder("How far back"));
        SpinnerModel numModel = new SpinnerNumberModel(1, 1, 1000, 1);
        howFarBack = new JSpinner(numModel);
        howFarBackHolder.add(howFarBack);
        JPanel opponentChoiceHolder = new JPanel();
        opponentChoiceHolder.setBorder(new javax.swing.border.TitledBorder("To bit"));
        oppChoice = new JComboBox(rp.getChoices().toArray());
        opponentChoiceHolder.add(oppChoice);

        inputs.setLayout(new GridLayout(2, 2, 2, 2));
        inputs.add(howFarBackHolder);
        inputs.add(opponentChoiceHolder);
        addClause = new JButton("Add");
        backButton = new JButton("Go back");
        addClause.addActionListener(this);
        backButton.addActionListener(this);
        inputs.add(addClause);
        inputs.add(backButton);
        clauseBox.add(inputs);
        add(clauseBox, "Clauses");
    }


    public void actionPerformed(java.awt.event.ActionEvent ae) {
        Object src = ae.getSource();
        if (src instanceof JButton) {
            if (src == addRule) addRule();
            if (src == removeRule) removeRule();
            if (src == editRule) editRule();
            if (src == addClause) addClause();
            if (src == backButton) ruleOrClause.show(this, "Rules");


        } else if (src instanceof JComboBox) {
            rp.setCurrentChoice(choices.getSelectedIndex());
            rules.setModel(rp);
        }
    }

    private void addRule() {
        int outcome = choices.getSelectedIndex();
        int fromIndex = (Integer) from.getValue();
        int toIndex = (Integer) to.getValue();
        try {
            rp.addRule(outcome, fromIndex, toIndex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private void removeRule() {
        int outcome = choices.getSelectedIndex();
        try {
            rp.removeRule(outcome, rules.getSelectedIndex());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private void editRule() {
        setNewCurrentRule((Rule) rules.getSelectedValue());
        ruleOrClause.show(this, "Clauses");
    }

    private void setNewCurrentRule(Rule curRule) {
        currentRule = curRule;
    }


    private void addClause() {
        int back = (Integer) howFarBack.getValue();
        int oppChose = oppChoice.getSelectedIndex();
        try {
            rp.addClause(currentRule, back, oppChose);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
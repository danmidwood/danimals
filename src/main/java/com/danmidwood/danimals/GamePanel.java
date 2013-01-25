package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class com.danmidwood.danimals.GamePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GamePanel extends Box implements GameModel, ActionListener {
    Game game;
    JTextField name = new JTextField("Untitled com.danmidwood.danimals.Game");
    JTextField choiceOne = new JTextField("");
    JTextField choiceTwo = new JTextField("");
    JButton initialize = new JButton("Create com.danmidwood.danimals.Game");
    JButton define = new JButton("Define com.danmidwood.danimals.Game");
    GameDesigner gd;
    boolean gameDefined = false;
    ActionListener listening = null;


    public GamePanel(Game game) {
        super(BoxLayout.Y_AXIS);
        setup();
        this.game = game;
        gd = new GameDesigner(game);
        gd.addActionListener(this);
        fireNewGame();
    }

    public GamePanel() {
        super(BoxLayout.Y_AXIS);
        setup();
    }

    private void setup() {
        Dimension prefDim = new Dimension(100, new Double(name.getPreferredSize().getHeight()).intValue());
        JPanel gameName = new JPanel();
        gameName.setBorder(new javax.swing.border.TitledBorder("com.danmidwood.danimals.Game name"));
        gameName.add(name);
        name.setPreferredSize(prefDim);

        JPanel ch1 = new JPanel();
        ch1.add(choiceOne);
        ch1.setBorder(new javax.swing.border.TitledBorder("Choice 1"));
        choiceOne.setPreferredSize(prefDim);

        JPanel ch2 = new JPanel();
        ch2.add(choiceTwo);
        ch2.setBorder(new javax.swing.border.TitledBorder("Choice 2"));
        choiceTwo.setPreferredSize(prefDim);

        JPanel buttons = new JPanel();
        buttons.setBorder(new javax.swing.border.TitledBorder("Create game"));
        buttons.add(initialize);
        buttons.add(define);
        initialize.addActionListener(this);
        define.setVisible(false);
        define.addActionListener(this);

        add(gameName);
        add(ch1);
        add(ch2);
        add(buttons);
    }

    public void addActionListener(ActionListener l) {
        if (listening == null) listening = l;
    }

    public void removeActionListener() {
        listening = null;
    }


    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String command = e.getActionCommand();
        if (src == initialize) {
            if (isInputValid(name.getText()) && isInputValid(choiceOne.getText())
                    && isInputValid(choiceTwo.getText()) && !choiceOne.getText().equals(choiceTwo.getText())) {
                createGame(name.getText(), choiceOne.getText(), choiceTwo.getText());
            } else System.out.println("com.danmidwood.danimals.Game not created : incomplete information");
        } else if (src == define) defineGame();
        else if (command == GameDesigner.READY) {
            if (listening != null)
                listening.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "com.danmidwood.danimals.Game is ready"));
            gameDefined = true;
            gd.hide();
        } else {
            System.out.println(src.toString() + " " + gd.toString());
            define.setVisible(false);
        }
    }

    private boolean isInputValid(String toCheck) {
        return (toCheck.length() > 0);
    }

    private void fireNewGame() {
        name.setText(getName());
        choiceOne.setText(game.getChoice(0).toString());
        choiceTwo.setText(game.getChoice(1).toString());
        define.setVisible(true);
    }

    public Game getGame() {
        return game;
    }

    public void defineGame() {
        if (gd == null) {
            gd = new GameDesigner(game);
            gd.addActionListener(this);
        }
        gd.show();
    }


    public void createGame(String name, String choiceOne, String choiceTwo) {
        String[] choices = {choiceOne, choiceTwo};
        game = new Game(choices);
        setName(name);
        fireNewGame();
    }

    public void setName(String newName) {
        game.setName(newName);
    }

    public String getName() {
        return game.getName();
    }

    public boolean ready() {
        return gameDefined;
    }

    public java.util.Set unsetValues() {
        return game.unsetValues();
    }

    public Object addResult(Object key, Result res) throws Exception {
        return game.addResult(key, res);
    }


}

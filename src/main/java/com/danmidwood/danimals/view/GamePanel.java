package com.danmidwood.danimals.view;

import com.danmidwood.danimals.Game;
import com.danmidwood.danimals.GameModel;
import com.danmidwood.danimals.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends Box implements GameModel, ActionListener {
    Game game;
    JButton initialize = new JButton("Create Game");
    JButton define = new JButton("Define Game");
    GameDesigner gd;
    boolean gameDefined = false;
    ActionListener listening = null;


    public GamePanel() {
        super(BoxLayout.Y_AXIS);
        setup();
    }

    private void setup() {

        JPanel buttons = new JPanel();
        buttons.setBorder(new javax.swing.border.TitledBorder("Create game"));
        buttons.add(initialize);
        buttons.add(define);
        initialize.addActionListener(this);
        define.setVisible(false);
        define.addActionListener(this);

        add(buttons);
    }

    public void addActionListener(ActionListener l) {
        if (listening == null) listening = l;
    }


    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String command = e.getActionCommand();
        if (src == initialize) {
            createGame();

        } else if (src == define) defineGame();
        else if (command.equals(GameDesigner.READY)) {
            if (listening != null)
                listening.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Game is ready"));
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


    public void createGame() {
        game = new Game();
        fireNewGame();
    }

    public boolean ready() {
        return gameDefined;
    }

    public java.util.Set unsetValues() {
        return game.unsetValues();
    }

    public Object addResult(String key, Result res) throws Exception {
        return game.addResult(key, res);
    }


}

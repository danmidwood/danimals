package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class GameDesigner extends JFrame {
    ActionListener listen;
    Game game;
    java.util.TreeMap<String, ResultPanel> resRefs = new java.util.TreeMap<String, ResultPanel>();
    static public String READY = "The game is now ready";

    public GameDesigner(Game g) {
        super();
        setSize(200, 500);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.game = g;
        Set<String> keys = game.keySet();
        for (String key : keys) {
            addPanel(key);
        }
        JPanel bottom = new JPanel();
        final JButton confirm = new JButton("Confirm");
        bottom.add(confirm);
        getContentPane().add(bottom);
        confirm.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent ae) {
                        for (String thisKey : resRefs.keySet()) {
                            ResultPanel thisResPan = resRefs.get(thisKey);
                            Result thisRes = thisResPan.getResult();
                            try {
                                game.addResult(thisKey, thisRes);
                            } catch (Exception e) {
                                System.out.println(e.toString());
                            }
                        }
                        if (listen != null)
                            listen.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, READY));

                    }
                });
    }

    public void addActionListener(ActionListener al) {
        listen = al;
    }


    public void addPanel(String key) {
        String title = game.getChoiceNames(key).toString();
        Result newRes = game.get(key);
        if (newRes == null) newRes = new Result();
        ResultPanel newPane = new ResultPanel(newRes, title);
        resRefs.put(key, newPane);
        getContentPane().add(newPane);
        getContentPane().add(Box.createVerticalStrut(10));

    }
}

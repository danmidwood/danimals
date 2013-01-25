package com.danmidwood.danimals;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.*;

/**
 * Write a description of class com.danmidwood.danimals.Danimals here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Danimals extends JFrame implements ActionListener, Runnable
{
    Environment env = new Environment();
    Population pop = env.createPopulation(15,15);
    BitGrid bg = new BitGrid(pop);
    PopulationPanel popMain = new PopulationPanel();
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    SideBar optionsSide = new SideBar();
    GamePanel gamePane;
    Log logger = new Log();
    LogPanel logDisplay = new LogPanel(logger);
    boolean canRun = false;
    Thread runner = new Thread(this);
    SelectionPanel p1Fight;
    SelectionPanel p2Fight;
    SelectionPanel p1Repro;
    SelectionPanel p2Repro;
    int fightsBeforeCrossover = 100;
    int speed = 0;
    Object temp;
    int fightNo = 0;
    // Count how many fight-mate seasons have run
    int seasonNo = 0;
    // How many seasons to run before stopping
    int stopAfter = 5;

    
    public Danimals() {
        super("com.danmidwood.danimals.Danimals - A Foray into Life");
        popMain.setPopulation(pop);
        env.populate(1, 0.5);
        env.setMutateRate(0.01);
        getContentPane().setLayout(new BorderLayout());
        JSplitPane eastPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        setupOptions();
        eastPanel.add(optionsSide);
        eastPanel.add(logDisplay);
        //logDisplay.setVisible(false);
        getContentPane().add(popMain, BorderLayout.CENTER);
        getContentPane().add(eastPanel, BorderLayout.EAST);
        eastPanel.setDividerLocation(0.1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);
        start.addActionListener(this);
        stop.addActionListener(this);
        stop.setEnabled(false);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        show();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private void setupOptions() {
        gamePane = new GamePanel();
        optionsSide.addCard("com.danmidwood.danimals.Game", gamePane);
        gamePane.addActionListener(this);


        EnvironmentPanel envPane = new EnvironmentPanel(env);
        optionsSide.addCard("com.danmidwood.danimals.Environment", envPane);
        
        Object[] classes = ClassLoader.getClasses(Selection.class, ".");
        
        JPanel fightPanel = new JPanel(new GridLayout(2,1,10,10));
        p1Fight = new SelectionPanel(1);
        p1Fight.setModel(classes);
        p2Fight = new SelectionPanel(2);
        p2Fight.setModel(classes);
        fightPanel.add(p1Fight);
        fightPanel.add(p2Fight);
        optionsSide.addCard("Fight com.danmidwood.danimals.Selection", fightPanel);
        
        JPanel reproPanel = new JPanel(new GridLayout(2,1,10,10));
        p1Repro = new SelectionPanel(1);
        p1Repro.setModel(classes);
        p2Repro = new SelectionPanel(2);
        p2Repro.setModel(classes);
        reproPanel.add(p1Repro);
        reproPanel.add(p2Repro);
        optionsSide.addCard("Reproduction com.danmidwood.danimals.Selection", reproPanel);
        
    }
    
    public void dispose() {
        logger.dispose();
        super.dispose();
    }
    
    private void setupGame() {
        Game thisGame = gamePane.getGame();
        env.setGame(thisGame);
        RuleParser rp = new RuleParser(thisGame.getChoices());
        thisGame.setParser(rp);
        popMain.setParser(rp);
        RuleParserPanel rpp = new RuleParserPanel(rp);
        optionsSide.addCard("Parsing rules", rpp);
    }
    

    
    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if (src instanceof JButton) {
            JButton srcBut = (JButton)src;
            if (srcBut == start) { startEvo(); }
            else if (srcBut == stop) stopEvo();
        }
        else if (src == gamePane) setupGame();

    }
        
    
    
    private void isReadyToRun() {
        System.out.println("Running isready");
        temp = null;
        try {
            temp = env.getGame();
        } catch (Exception e) {
            if (gamePane.ready()) {
                temp = gamePane.getGame();
                env.setGame((Game)temp);
            }
        }
        try {
            if (p1Fight.getAllSelections().size() < 1) System.out.println("player one fight selection is not set");
            if (p2Fight.getAllSelections().size() < 1) System.out.println("player two fight selection is not set");
            if (p1Repro.getAllSelections().size() < 1) System.out.println("player one fight selection is not set");
            if (p2Repro.getAllSelections().size() < 1) System.out.println("player two fight selection is not set");
        } catch (Exception e) { } //System.out.println("Could not start, error in selections : " + e.toString()); return; }
            
        if (temp == null) System.out.println("The game is not defined");
        else if (!((Game)temp).ready()) System.out.println("The game is not ready");
        else canRun = true;
        
    }


    public void run() {
        if (canRun) System.out.println("Started");
        while (canRun) {
            try {
                if (fightNo++ < fightsBeforeCrossover) {
                    doFight();
                    runner.sleep(speed);
                } else {
                    System.out.println("Mating");
                    // set fightno to 0 when all crossovers are complete
                    if (!doCrossOver()) { 
                        fightNo = 0;
                        System.out.println("End of season " + ++seasonNo);
                        if (seasonNo % stopAfter == 0) stopEvo();
                    }
                    runner.sleep(speed);
                }
            } catch (Exception e) { System.out.println(e.toString()); }
        }
        System.out.println("Stopped");
    }
    
    /**
     * Selects two strings and they will make babies.
     * returns true when more crossovers are available, false
     * when all crossovers are complete and more fighting is to take place.
     * See crossOver in com.danmidwood.danimals.Environment for implementation details
     * @return boolean More crossovers to come
     */
    private boolean doCrossOver() {
        try {
            Coord p1 = p1Repro.select(pop);
            Coord p2 = p2Repro.select(pop);
            // Will return true when more are to come. False when
            // all crossovers have been completed
            return (!env.crossOver(p1, p2));
        } catch (Exception e) { 
            // exit the crossovers by returning true.
            // If every crossover errors it is
            // best not to go into an infinite loop.
            return true;
        }
    }
    
    private void doFight() {        
        try {
            Coord p1 = p1Fight.select(pop);
            Coord p2 = p2Fight.select(pop);
            env.doBattle(p1, p2);
        } catch (Exception e) { } // Ignore
    }
    
    public void startEvo() { 
        isReadyToRun();
        if (!canRun) { return; }
        start.setEnabled(false);
        stop.setEnabled(true);
        runner = new Thread(this);
        runner.start();
    }
    public void stopEvo() {
        canRun = false;
        start.setEnabled(true);
        stop.setEnabled(false);
    }



    static public void main(String[] args) {
        Danimals evolvo = new Danimals();
    }
}



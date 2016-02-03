package danmidwood.danimals;

import javax.swing.*;
/**
 * Write a description of class LogPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LogPanel extends JScrollPane
{
    Log logger;
    JList logDisplay;
    
    public LogPanel(Log logger) {
        this.logger = logger;
        logDisplay = new JList(logger);
        java.awt.Dimension size = new java.awt.Dimension(100,200);
        setViewportView(logDisplay);
        getViewport().setPreferredSize(size);
        setPreferredSize(size);
        setMaximumSize(new java.awt.Dimension(100,200));
    }
    


    
    
    public void dispose() {
        logger.dispose();
    }
}

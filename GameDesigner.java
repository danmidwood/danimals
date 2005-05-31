import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class GameDesigner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameDesigner extends JFrame
{
    ActionListener listen;
    Game game;
    java.util.TreeMap resRefs = new java.util.TreeMap();
    static public String READY = "The game is now ready";
    
    public GameDesigner(Game g) {
        super("Editing " +g.getName());
        setSize(100,400);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.game = g;
        java.util.Set keys = game.keySet();
        java.util.Iterator it = keys.iterator();
        while (it.hasNext()) {
            addPanel(it.next());
        }
        JPanel bottom = new JPanel();
        final JButton confirm = new JButton("Confirm");
        bottom.add(confirm);
        getContentPane().add(bottom);
        confirm.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent ae) {
                    java.util.Iterator allRes = resRefs.keySet().iterator();
                    while (allRes.hasNext()) {
                        Object thisKey = allRes.next();
                        ResultPanel thisResPan = (ResultPanel)resRefs.get(thisKey);
                        Result thisRes = thisResPan.getResult();
                        try {
                            game.addResult(thisKey, thisRes);
                        } catch (Exception e) {System.out.println(e.toString()); }
                    }
                    if (listen != null) listen.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, READY));
                    
                }
            });
    }
    
    public void addActionListener(ActionListener al) { listen = al; }
    public void removeActionListener() { listen = null; }
    
    
    public void addPanel(Object key) {
        String title = game.getChoiceNames(key.toString()).toString();
        Result newRes = (Result)game.get(key);
        if (newRes == null) newRes = new Result();
        ResultPanel newPane = new ResultPanel(newRes, title);
        resRefs.put(key,newPane);
        getContentPane().add(newPane);
        getContentPane().add(Box.createVerticalStrut(10));
        
    }
}

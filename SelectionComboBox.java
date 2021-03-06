import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class SelectionComboPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionComboBox extends ClassComboBox
{
    protected int id;
    protected int playerNo;
    private SelectionComboBox child;
    boolean childExists = false;
    private JComponent parent;
    private Selection curSelection;
    
    public SelectionComboBox(ComboBoxModel mod, int playerNo) {
        this(mod, playerNo, null, 0);
        parent = (JComponent)getParent();
    }
    
    private SelectionComboBox(ComboBoxModel mod, int playerNo, JComponent parent, int id) {
        super(mod, Selection.class);
        this.id = id;
        this.playerNo = playerNo;
        this.parent = parent;
        int maxWid = new Double(getMaximumSize().getWidth()).intValue();
        setMaximumSize(new Dimension(maxWid,20));
    }
    
    public Selection getSelection() { return curSelection; }
    public int getId() { return id; }
    public SelectionComboBox getChild() { return child; }
    public void setModel(ComboBoxModel mod) {
        super.setModel(mod);
    
        // Create an initial selection. It is imperitive that this does
        // not require a child to prevent an endless loop of many children
        // being added.
        for (int selecIndex=0; selecIndex<getItemCount(); selecIndex++) {
            try {
                Class selecClass = (Class)getItemAt(selecIndex);                
                Selection selec = (Selection)selecClass.newInstance();
                if (!selec.needsChild()) {
                    setSelectedIndex(selecIndex);
                    ItemEvent ie = new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, getSelectedItem(), ItemEvent.SELECTED);
                    fireItemStateChanged(ie);

                    break;
                }
            } catch (Exception e) { System.out.println("Selection error :" + e.toString()); }

         }
    }
        
    
    protected void addClassToModel(ComboBoxModel mod, Class value) {
        try {
            Selection sel = (Selection)value.newInstance();
            // If the selection needs a preselected strings do not let
            // the player 1 selection box allow it
            if (sel.needsPreselectedString() && playerNo == 1) return;
            super.addClassToModel(mod, value);
        } catch (Exception e) { System.out.println(e.toString()); }
    }
    
    public void fireItemStateChanged(java.awt.event.ItemEvent ie) {
        super.fireItemStateChanged(ie);
        if (ie.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            try {
                Class curClass = (Class)getSelectedItem();
                curSelection = (Selection)curClass.newInstance();
            } catch (Exception e) {} // Ignore the exception, don't pass it on
        }
    }
    

    public void addAllListeners(Object c) {
        if (!(c instanceof JComboBox) && !(c instanceof JButton)) return;
        JComboBox c2 = (JComboBox)c;
        java.awt.event.ActionListener[] actionListeners = getActionListeners();
        for (int listenerIndex = 0; listenerIndex < actionListeners.length; listenerIndex++) {
            c2.addActionListener(actionListeners[listenerIndex]);
        }
    }
    
//     public Container getParent() { return parent; }
    
    
    protected void addChild() throws Exception {
        // Can only have one child
        if (hasChild()) throw new Exception("This already has a child");
        if (parent == null) parent = (JPanel)getParent();
        SelectionComboBox newCombo = new SelectionComboBox(getModel(), playerNo, parent, id+1);
        addAllListeners(newCombo);
        //if (id == 1) parent.add(but);
        childExists = true;
        child = newCombo;
        parent.add(child);
        parent.revalidate();
        parent.repaint();
    }
    
    protected boolean needParams() {
        if (curSelection.hasParams()) return true;
        else if (hasChild()) return child.needParams();
        else return false;
    }
    
    protected void removeChild() {
        // Remove the children of the child to be removed
        if (child.hasChild()) {
            child.removeChild();
        }
        //if (id ==1) parent.remove(but);
        parent.remove(child);
        parent.revalidate();
        parent.repaint();
        child = null;
        childExists = false;
    }
    
    protected boolean hasChild() {
        return child != null;
    }
    
    
}

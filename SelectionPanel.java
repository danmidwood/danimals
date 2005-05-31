import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class SelectionPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionPanel extends JPanel implements ActionListener
{
    Box getDetails = Box.createVerticalBox();
    JButton editParam = new JButton("Edit");
    SelectionComboBox scb;
    JViewport vp;
    static final boolean ADD_AT_BOTTOM = true;
    static final boolean ADD_AT_TOP = false;
    int playerNo;
    
    public SelectionPanel(int playerNo) {
        this(playerNo, BoxLayout.Y_AXIS, BorderLayout.SOUTH);
    }
    
    public SelectionPanel(int playerNo, int axis, String buttonPlacement) {
        this.playerNo = playerNo;
        setBorder(new javax.swing.border.TitledBorder("Player " + playerNo));
        if (buttonPlacement == BorderLayout.CENTER) buttonPlacement = BorderLayout.NORTH;
        setLayout(new BorderLayout());
        JPanel selectionContainer = new JPanel();
        selectionContainer.setLayout(new BoxLayout(selectionContainer, axis));
        scb = new SelectionComboBox(new javax.swing.DefaultComboBoxModel(),playerNo);
        scb.addActionListener(this);
        selectionContainer.add(scb);
        showButton(false);
        add(editParam, buttonPlacement);
        editParam.addActionListener(this);
        JScrollPane selecScroll = new JScrollPane(selectionContainer);
        selecScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vp = selecScroll.getViewport();
        add(selecScroll, BorderLayout.CENTER);
    }
    
    public void setModel(javax.swing.DefaultComboBoxModel moddy) {
        scb.setModel(moddy);
    }
    public void setModel(Object[] obs) {
        scb.setModel(new javax.swing.DefaultComboBoxModel(obs));
    }
    
    public java.util.ArrayList getAllSelections() {
        SelectionComboBox curScb = scb;
        java.util.ArrayList rtn = new java.util.ArrayList();
        rtn.add(scb.getSelection());
        while (scb.hasChild()) {
            scb = scb.getChild();
            rtn.add(scb.getId(), scb.getSelection());
        }
        rtn.trimToSize();
        return rtn;
    }
    
    public Coord select(Population pop) throws Exception {
        java.util.Iterator it = getAllSelections().iterator();
        try {
            Population workingPop = pop;
            while (it.hasNext()) {
                Object next = it.next();
                Selection nextSel = (Selection)next;
                Object afterSelection = nextSel.select(workingPop);
                if (afterSelection instanceof Coord) return (Coord)afterSelection;
                else if (afterSelection instanceof Population) workingPop = (Population)afterSelection;
                else { System.out.println("Unexpected " + afterSelection.getClass().getName() + " found during selection"); }
            }
        } catch (Exception e) { System.out.println("Error in selection : " + e.getMessage().toString()); }
        throw new Exception("Player " + playerNo + " : Nothing found");
    }
    

        

    
//     private void setModel(javax.swing.DefaultComboBoxModel moddy, Container cont) {
//         Component[] comps = cont.getComponents();
//         for (int compIndex = 0; compIndex < comps.length; compIndex++) {
//             Component thisComp = comps[compIndex];
//             if (thisComp instanceof Container) { setModel(moddy, (Container)thisComp); }
//             if (thisComp instanceof SelectionComboBox) {
//                 SelectionComboBox scb = (SelectionComboBox)comps[compIndex];
//                 scb.setModel(moddy);
//             }
//         }
//     }
    
    public void showButton(boolean on) { 
        editParam.setVisible(on);
    }

    public void actionPerformed(java.awt.event.ActionEvent ae) {
        if (ae.getSource().getClass() == JButton.class) actionOnButton(ae);
        else if (ae.getSource().getClass() == SelectionComboBox.class) actionOnCombo(ae);
//         else System.out.println(ae.getSource().getClass().getName());
    }
    
    public void actionOnCombo(java.awt.event.ActionEvent ae) {
        try {
            SelectionComboBox thisCombo = (SelectionComboBox)ae.getSource();
            //if (thisCombo.isPopupVisible()) return;
            Selection thisSelection = (Selection) thisCombo.getSelection();
            if (thisSelection.needsChild()) {
                thisCombo.addChild();
                int newHeight = (new Double( vp.getViewPosition().getY() + thisCombo.getSize().getHeight())).intValue();
                vp.setViewPosition(new Point(0,newHeight));                
            } else {
                if (thisCombo.hasChild()) {
                    thisCombo.removeChild();
                    vp.setViewPosition(new Point(0,0));
                }
            }
            showButton(scb.needParams());
        } catch (Exception e) { } // Do nothing
    }
    
    public void actionOnButton(java.awt.event.ActionEvent ae) {
        getDetails.removeAll();
        int newHeight = 0;
        SelectionComboBox thisCombo = scb;
        try {
            getDetails.add(new SelectionParamPanel(thisCombo.getSelection()));
            while (thisCombo.hasChild()) {
                SelectionComboBox child = thisCombo.getChild();
                SelectionParamPanel spp = new SelectionParamPanel(child.getSelection());
                newHeight =+ (new Double(spp.getPreferredSize().getHeight())).intValue();
                getDetails.add(spp);
                thisCombo = child;
            }
        } catch (Exception e) { System.out.println(e.toString()); }
        JFrame f = new JFrame();
        f.getContentPane().add(getDetails);
        f.setSize(100,100);
        f.show();
    }
    
//     public boolean containsComponent( Component c) {
//         Component[] allComps = getComponents();
//         for (int cIndex=0; cIndex < allComps.length; cIndex++) {
//             if (allComps[cIndex] == c) return true;
//         }
//         return false;
//     }
    



}

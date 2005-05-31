import javax.swing.event.*;
/**
 * Write a description of class Log here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Log extends java.io.PrintStream implements javax.swing.ListModel
{
    java.io.PrintStream out = System.out; // Save old printstream
    java.io.PrintStream err = System.err; // Save old printstream
    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    java.util.LinkedList logged = new java.util.LinkedList();

    public Log() {
        super(System.out, true);
        System.setOut(this);
    }
    
    public void print(String x) {
//         out.print(x);
        add(x);
        fireNewItem(logged.size()-1);
    }
    
    public void println(String x) {
        add(x);
        fireNewItem(logged.size()-1);
//         out.println(x);
    }
    
    public void add(String x) {
        if (logged.size() > 50) logged.removeFirst();
//         if (x.length() < 20) logged.add(x);
//         else {
//             logged.add(x.substring(0,20));
//             add(x.substring(20,x.length()));
//         }
        logged.add(x);
    }
    
    public void fireNewItem(int elementIndex) {
        ListDataEvent lde = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, elementIndex, elementIndex);
        java.util.EventListener[] ldl = listeners.getListeners(ListDataListener.class);
        for (int listIndex=0; listIndex< ldl.length; listIndex++) {
            ListDataListener dataListen = (ListDataListener)ldl[listIndex];
            dataListen.intervalAdded(lde);
        }
    }
    /**
     * Adds a listener to the list that's
     * notified each time a change to the data model occurs. 
     */
    public void addListDataListener(ListDataListener l) { listeners.add(ListDataListener.class, l); }    
    /**
     * Returns the value at the specified index. 
     */
    public Object getElementAt(int index) { return logged.get(index); }
    /**
     * Returns the length of the list. 
     */
    public int getSize() { return logged.size(); }
    /**
     * Removes a listener from the list that's notified 
     * each time a change to the data model occurs
     */
    public void removeListDataListener(ListDataListener l)  { listeners.remove(ListDataListener.class, l); }
    public void dispose() { System.setOut(out); }

}

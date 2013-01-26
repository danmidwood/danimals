package com.danmidwood.danimals.view;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.EventListener;
import java.util.LinkedList;

public class Log extends java.io.PrintStream implements javax.swing.ListModel {
    java.io.PrintStream out = System.out;
    javax.swing.event.EventListenerList listeners = new javax.swing.event.EventListenerList();
    LinkedList<String> logged = new LinkedList<String>();

    public Log() {
        super(System.out, true);
        System.setOut(this);
    }

    public void print(String x) {
        add(x);
        fireNewItem(logged.size() - 1);
    }

    public void println(String x) {
        add(x);
        fireNewItem(logged.size() - 1);
    }

    public void add(String x) {
        if (logged.size() > 50) logged.removeFirst();
        logged.add(x);
    }

    public void fireNewItem(int elementIndex) {
        ListDataEvent lde = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, elementIndex, elementIndex);
        java.util.EventListener[] ldl = listeners.getListeners(ListDataListener.class);
        for (EventListener aLdl : ldl) {
            ListDataListener dataListen = (ListDataListener) aLdl;
            dataListen.intervalAdded(lde);
        }
    }

    public void addListDataListener(ListDataListener l) {
        listeners.add(ListDataListener.class, l);
    }

    public Object getElementAt(int index) {
        return logged.get(index);
    }

    public int getSize() {
        return logged.size();
    }

    public void removeListDataListener(ListDataListener l) {
        listeners.remove(ListDataListener.class, l);
    }

    public void dispose() {
        System.setOut(out);
    }

}

package com.danmidwood.danimals;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class com.danmidwood.danimals.EnvironmentPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnvironmentPanel extends Box implements ActionListener, ChangeListener {
    Environment env;
    JSpinner stringSize;
    JSpinner mutateRate;


    public EnvironmentPanel(Environment env) {
        super(BoxLayout.Y_AXIS);
        this.env = env;
        env.addActionListener(this);
        createInputs();
    }

    private void createInputs() {
        JPanel stringSizeHolder = new JPanel();
        stringSizeHolder.setBorder(new javax.swing.border.TitledBorder("Bit string size"));
        SpinnerNumberModel stringSizeSpinner = new SpinnerNumberModel(env.getStringSize(), 0, 6400, 64);
        stringSize = new JSpinner(stringSizeSpinner);
        stringSizeHolder.add(stringSize);

        java.awt.Dimension pref = stringSize.getEditor().getPreferredSize();

        JPanel mutateRateHolder = new JPanel();
        mutateRateHolder.setBorder(new javax.swing.border.TitledBorder("Mutate Rate"));
        SpinnerNumberModel mutateRateSpinner = new SpinnerNumberModel(env.getMutateRate(), 0, 1, 0.01);
        mutateRate = new JSpinner(mutateRateSpinner);
        mutateRate.addChangeListener(this);
        mutateRateHolder.add(mutateRate);
        mutateRate.getEditor().setPreferredSize(pref);


//         JPanel stringSizeHolder = new JPanel();
//         stringSizeHolder.setBorder(new javax.swing.border.TitledBorder("Bit string size"));
//         SpinnerNumberModel stringSizeSpinner = new SpinnerNumberModel(0,0,6400,64);
//         stringSize = new JSpinner(sss);
//         stringSizeHolder.add(stringSize);
        add(stringSizeHolder);
        add(mutateRateHolder);
    }

    public void addActionListener(ActionListener al) {
        ;
    }

    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
    }

    public void stateChanged(ChangeEvent ce) {
        Object src = ce.getSource();
        if (src == mutateRate) {
            Double newMutate = (Double) mutateRate.getValue();
            env.setMutateRate(newMutate.doubleValue());
        }
    }

}

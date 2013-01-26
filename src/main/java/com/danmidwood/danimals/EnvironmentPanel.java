package com.danmidwood.danimals;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EnvironmentPanel extends Box implements ChangeListener {
    Environment env;
    JSpinner stringSize;
    JSpinner mutateRate;


    public EnvironmentPanel(Environment env) {
        super(BoxLayout.Y_AXIS);
        this.env = env;
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

        add(stringSizeHolder);
        add(mutateRateHolder);
    }

    public void stateChanged(ChangeEvent ce) {
        Object src = ce.getSource();
        if (src == mutateRate) {
            Double newMutate = (Double) mutateRate.getValue();
            env.setMutateRate(newMutate);
        }
    }

}

package edu.paulssonlab.macsplugin.cleaner.timing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;

public class BasicCleanMacsTimingView implements CleanMacsTimingView {
	
	private JSpinner spinnerSecFillBleach;
	private JSpinner spinnerSecFillEthanol;
	private JSpinner spinnerSecFillWater;
	private JSpinner spinnerSecFlowPassively;
	private JSpinner spinnerSecEmpty;
	private JPanel panelMacsWashTiming;
	private JLabel lblSecFillBleach;
	private JLabel lblSecFillEthanol;
	private JLabel lblSecFillWater;
	private JLabel lblSecFlowPassively;
	private JLabel lblSecEmpty;

	public BasicCleanMacsTimingView() {
		
		Float dfltSecFillBleach = 0.0f;
		Float dfltSecFillEthanol = 0.0f;
		Float dfltSecFillWater = 0.0f;
		
		Float dfltSecFillPassively = 0.0f;
		Float dfltSecEmptyPT = 0.0f;
		
		panelMacsWashTiming = new JPanel();
		panelMacsWashTiming.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fill/Empty Pressure Tube Timing", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblSecFillBleach = new JLabel("Actively Fill Bleach (sec)");
		lblSecFillBleach.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecFillBleach = new JSpinner();
		spinnerSecFillBleach.setModel(new SpinnerNumberModel(dfltSecFillBleach, new Float(0), null, new Float(0.1)));
		spinnerSecFillBleach.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecFillEthanol = new JLabel("Actively Fill Ethanol (sec)");
		lblSecFillEthanol.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecFillEthanol = new JSpinner();
		spinnerSecFillEthanol.setModel(new SpinnerNumberModel(dfltSecFillEthanol, new Float(0), null, new Float(0.1)));
		spinnerSecFillEthanol.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecFillWater = new JLabel("Actively Fill Water (sec)");
		lblSecFillWater.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecFillWater = new JSpinner();
		spinnerSecFillWater.setModel(new SpinnerNumberModel(dfltSecFillWater, new Float(0), null, new Float(0.1)));
		spinnerSecFillWater.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecFlowPassively = new JLabel("Passively Fill (sec)");
		lblSecFlowPassively.setFont(new Font("Dialog", Font.PLAIN, 16));
		String flowPassivelyToolTip = "Delay to allow cleaning fluid to fill the pressure tube while the cleaning fluid bottles depressurize";
		lblSecFlowPassively.setToolTipText(flowPassivelyToolTip);
		
		spinnerSecFlowPassively = new JSpinner();
		spinnerSecFlowPassively.setModel(new SpinnerNumberModel(dfltSecFillPassively, new Float(0), null, new Float(0.1)));
		spinnerSecFlowPassively.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecEmpty = new JLabel("Empty To Waste (sec)");
		lblSecEmpty.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecEmpty = new JSpinner();
		spinnerSecEmpty.setModel(new SpinnerNumberModel(dfltSecEmptyPT, new Float(0), null, new Float(0.1)));
		spinnerSecEmpty.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		defineGridBagLayout();
		panelMacsWashTiming.setMinimumSize(new Dimension(275,250));
		panelMacsWashTiming.setPreferredSize(new Dimension(350,250));
		panelMacsWashTiming.setMaximumSize(new Dimension(375,250));
		
	}
	
	
	private void defineGridBagLayout() {
		GridBagLayout gbl_panelMacswashTiming = new GridBagLayout();
		panelMacsWashTiming.setLayout(gbl_panelMacswashTiming);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		
		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.insets = new Insets(7,15,0,0);
		panelMacsWashTiming.add(lblSecFillBleach, gbc);
		gbc.insets = new Insets(0,15,0,0);
		gbc.gridy = 1;
		panelMacsWashTiming.add(lblSecFillEthanol, gbc);
		gbc.gridy = 2;
		panelMacsWashTiming.add(lblSecFillWater, gbc);
		gbc.gridy = 3;
		panelMacsWashTiming.add(lblSecFlowPassively, gbc);
		gbc.gridy = 4;
		gbc.insets = new Insets(0,15,7,0);
		panelMacsWashTiming.add(lblSecEmpty, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 35;
		gbc.ipady = 12;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(7,0,0,35);
		panelMacsWashTiming.add(spinnerSecFillBleach, gbc);
		gbc.insets = new Insets(0,0,0,35);
		gbc.gridy = 1;
		panelMacsWashTiming.add(spinnerSecFillEthanol, gbc);
		gbc.gridy = 2;
		panelMacsWashTiming.add(spinnerSecFillWater, gbc);
		gbc.gridy = 3;
		panelMacsWashTiming.add(spinnerSecFlowPassively, gbc);
		gbc.gridy = 4;
		gbc.insets = new Insets(0,0,7,35);
		panelMacsWashTiming.add(spinnerSecEmpty, gbc);
	}

	@Override
	public JComponent getMainComponent() {
		return panelMacsWashTiming;
	}

	@Override
	public void setVisible(boolean visible) {
		panelMacsWashTiming.setVisible(visible);
	}

	@Override
	public int getMillisFillPT(BasicCleaningFluid fluid) {
		Float secFillPT = 0.0f;
		switch (fluid) {
			case BLEACH:
				secFillPT = (Float) spinnerSecFillBleach.getValue();
				break;
			case ETHANOL:
				secFillPT = (Float) spinnerSecFillEthanol.getValue();
				break;
			case WATER:
				secFillPT = (Float) spinnerSecFillWater.getValue();
				break;
			default:
				System.out.println("unknown cleaning liquid");
		}
		
		return secToMillis(secFillPT);
	}

	@Override
	public int getMillisEmptyPT() {
		System.out.println(String.valueOf((Float) spinnerSecEmpty.getValue()));
		return secToMillis((Float) spinnerSecEmpty.getValue());
	}

	@Override
	public int getMillisPassivelyFlow() {
		return secToMillis((Float) spinnerSecFlowPassively.getValue());
	}
	
	private Float millisToSec(int millis) {
		return (float) (millis/1000.0);
	}
	
	private int secToMillis(float sec) {
		return Math.round(sec * 1000);
	}


	@Override
	public void setMillisFillPT(BasicCleaningFluid fluid, int millis) {
		switch (fluid) {
			case BLEACH:
				spinnerSecFillBleach.setValue(millisToSec(millis));
				break;
			case ETHANOL:
				spinnerSecFillEthanol.setValue(millisToSec(millis));
				break;
			case WATER:
				spinnerSecFillWater.setValue(millisToSec(millis));
				break;
			default:
				System.out.println("unknown cleaning liquid");
		}
	}

	@Override
	public void setMillisEmptyPT(int millis) {
		spinnerSecEmpty.setValue(millisToSec(millis));
	}

	@Override
	public void setMillisPassivelyFlow(int millis) {
		spinnerSecFlowPassively.setValue(millisToSec(millis));
	}
}

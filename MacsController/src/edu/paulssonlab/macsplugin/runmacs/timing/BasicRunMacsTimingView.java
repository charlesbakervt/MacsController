package edu.paulssonlab.macsplugin.runmacs.timing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class BasicRunMacsTimingView implements RunMacsTimingsView {

	private JPanel panelMacsTiming;
	private JSpinner spinnerSecOpen;
	private JSpinner spinnerSecHalfOpen;
	private JSpinner spinnerSecClosed;
	private JLabel lblSecOpenState;
	private JLabel lblSecHalfOpenState;
	private JLabel lblSecClosedState;
	private JLabel lblSecInjectCells;
	private JSpinner spinnerSecInject;
	
	
	public BasicRunMacsTimingView() {
		
		Float dfltSecOpen = 0.0f;
		Float dfltSecHalfOpen = 0.0f;
		Float dfltSecClosed = 0.0f;
		Float dfltSecInjectCells = 0.0f;

		panelMacsTiming = new JPanel();
		panelMacsTiming.setBorder(new TitledBorder(null, "Macs/Injection Timing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblSecOpenState = new JLabel("Open State Duration (sec)");
		lblSecOpenState.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecHalfOpenState = new JLabel("Half-Open State Duration (sec)");
		lblSecHalfOpenState.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecClosedState = new JLabel("Closed State Duration (sec)");
		lblSecClosedState.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblSecInjectCells = new JLabel("Cell Injection Duration (sec)");
		lblSecInjectCells.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecOpen = new JSpinner();
		spinnerSecOpen.setModel(new SpinnerNumberModel(dfltSecOpen, new Float(0), null, new Float(0.1)));
		spinnerSecOpen.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecHalfOpen = new JSpinner();
		spinnerSecHalfOpen.setModel(new SpinnerNumberModel(dfltSecHalfOpen, new Float(0), null, new Float(0.1)));
		spinnerSecHalfOpen.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecClosed = new JSpinner();
		spinnerSecClosed.setModel(new SpinnerNumberModel(dfltSecClosed, new Float(0), null, new Float(0.1)));
		spinnerSecClosed.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerSecInject = new JSpinner();
		spinnerSecInject.setModel(new SpinnerNumberModel(dfltSecInjectCells, new Float(0), null, new Float(0.1)));
		spinnerSecInject.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		//GroupLayout gl_panelMacsTiming = defineMacsTimingLayout();
		defineGridBagLayout();
		panelMacsTiming.setMinimumSize(new Dimension(275,180));
		panelMacsTiming.setPreferredSize(new Dimension(350,195));
		panelMacsTiming.setMaximumSize(new Dimension(375,206));
		//panelMacsTiming.setLayout(gl_panelMacsTiming);
	}

	@Override
	public JPanel getMainComponent() {
		return panelMacsTiming;
	}

	@Override
	public void setVisible(boolean visible) {
		panelMacsTiming.setVisible(visible);
		
	}

	@Override
	public int getMillisFlowCells() {
		return secToMillis((Float) spinnerSecOpen.getValue());
	}

	@Override
	public int getMillisAccumulateCells() {
		return secToMillis((Float) spinnerSecHalfOpen.getValue());
	}

	@Override
	public int getMillisTrapCells() {
		return secToMillis((Float) spinnerSecClosed.getValue());
	}
	
	@Override
	public int getMillisInjectCells() {
		return secToMillis((Float) spinnerSecInject.getValue());
	}
	
	@Override
	public void setMillisFlowCells(int millis) {
		spinnerSecOpen.setValue(millisToSec(millis));
	}

	@Override
	public void setMillisAccumulateCells(int millis) {
		spinnerSecHalfOpen.setValue(millisToSec(millis));
	}

	@Override
	public void setMillisTrapCells(int millis) {
		spinnerSecClosed.setValue(millisToSec(millis));
	}
	
	@Override
	public void setMillisInjectCells(int millis) {
		spinnerSecInject.setValue(millisToSec(millis));
	}
	
	private void defineGridBagLayout() {
		GridBagLayout gbl_panelMacsTiming = new GridBagLayout();
		panelMacsTiming.setLayout(gbl_panelMacsTiming);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		
		
		gbc.ipadx = 10;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridy = 0;
		gbc.insets = new Insets(3,15,0,0);
		panelMacsTiming.add(lblSecOpenState, gbc);

		gbc.gridy = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelMacsTiming.add(lblSecHalfOpenState, gbc);
		
		gbc.gridy = 2;
		gbc.insets = new Insets(0,15,0,0);
		panelMacsTiming.add(lblSecClosedState, gbc);

		gbc.gridy = 3;
		gbc.insets = new Insets(0,15,3,0);
		panelMacsTiming.add(lblSecInjectCells, gbc);
		
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.ipadx = 35;
		gbc.ipady = 12;

		gbc.gridy = 0;
		gbc.insets = new Insets(3,0,0,35);
		panelMacsTiming.add(spinnerSecOpen, gbc);
		
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,0,35);
		panelMacsTiming.add(spinnerSecHalfOpen, gbc);
		
		gbc.gridy = 2;
		gbc.insets = new Insets(0,0,0,35);
		panelMacsTiming.add(spinnerSecClosed, gbc);
		
		gbc.gridy = 3;
		gbc.insets = new Insets(0,0,3,35);
		panelMacsTiming.add(spinnerSecInject, gbc);
		
	}
	
	
	private float millisToSec(int millis) {
		return (float) (millis/1000.0);
	}
	
	private int secToMillis(float sec) {
		return Math.round(sec * 1000);
	}

}

package edu.paulssonlab.macsplugin.runmacs.pressure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class BasicRunMacsPressureView implements RunMacsPressureView {
	
	private JPanel panelMacsPressure;
	
	private JSpinner spinnerFlowPressure;
	private JSpinner spinnerValvePressure;
	private JSpinner spinnerImagePressure;
	private JLabel lblMacsFlowPressure;
	private JLabel lblMacsValvePressure;
	private JLabel lblMacsImagePressure;

	public BasicRunMacsPressureView() {
		
		Float dfltFlowPressure = 0.0f;
		Float dfltValvePressure = 0.0f;
		Float dfltImagingPressure = 0.0f;
		
		panelMacsPressure = new JPanel();
		panelMacsPressure.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "MACS Pressure", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblMacsFlowPressure = new JLabel("Flow Layer Pressure (psi)");
		lblMacsFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblMacsValvePressure = new JLabel("Control Layer Pressure (psi)");
		lblMacsValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblMacsImagePressure = new JLabel("Imaging Pressure (psi)");
		lblMacsImagePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerFlowPressure = new JSpinner();
		spinnerFlowPressure.setModel(new SpinnerNumberModel(dfltFlowPressure, new Float(0), new Float(38), new Float(0.1)));
		spinnerFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerValvePressure = new JSpinner();
		spinnerValvePressure.setModel(new SpinnerNumberModel(dfltValvePressure, new Float(0), new Float(38), new Float(0.1)));
		spinnerValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerImagePressure = new JSpinner();
		spinnerImagePressure.setModel(new SpinnerNumberModel(dfltImagingPressure, new Float(0), new Float(38), new Float(0.1)));
		spinnerImagePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		defineGridBagLayout();
		panelMacsPressure.setMinimumSize(new Dimension(275,140));
		panelMacsPressure.setPreferredSize(new Dimension(350,150));
		panelMacsPressure.setMaximumSize(new Dimension(375,160));
	}
	
	private void defineGridBagLayout() {
		GridBagLayout gbl_panelMacsPressure = new GridBagLayout();
		panelMacsPressure.setLayout(gbl_panelMacsPressure);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		
		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.insets = new Insets(3,15,0,0);
		panelMacsPressure.add(lblMacsFlowPressure, gbc);
		gbc.insets = new Insets(0,15,0,0);
		gbc.gridy = 1;
		panelMacsPressure.add(lblMacsValvePressure, gbc);
		gbc.gridy = 2;
		gbc.insets = new Insets(0,15,3,0);
		panelMacsPressure.add(lblMacsImagePressure, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 20;
		gbc.ipady = 12;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(3,0,0,35);
		panelMacsPressure.add(spinnerFlowPressure, gbc);
		gbc.insets = new Insets(0,0,0,35);
		gbc.gridy = 1;
		panelMacsPressure.add(spinnerValvePressure, gbc);
		gbc.insets = new Insets(0,0,3,35);
		gbc.gridy = 2;
		panelMacsPressure.add(spinnerImagePressure, gbc);
		
	}
	


	@Override
	public JPanel getMainComponent() {
		return panelMacsPressure;
	}

	@Override
	public void setVisible(boolean visible) {
		panelMacsPressure.setVisible(true);
		
	}

	@Override
	public float getFlowPressure() {
		return (Float) spinnerFlowPressure.getValue();
	}

	@Override
	public float getValvePressure() {
		return (Float) spinnerValvePressure.getValue();
	}

	@Override
	public float getImagingPressure() {
		return (Float) spinnerImagePressure.getValue();
	}

	@Override
	public void setFlowPressure(float pressure) {
		spinnerFlowPressure.setValue(Float.valueOf(pressure));
	}

	@Override
	public void setValvePressure(float pressure) {
		spinnerValvePressure.setValue(Float.valueOf(pressure));
	}

	@Override
	public void setImagingPressure(float pressure) {
		spinnerImagePressure.setValue(Float.valueOf(pressure));
	}

}

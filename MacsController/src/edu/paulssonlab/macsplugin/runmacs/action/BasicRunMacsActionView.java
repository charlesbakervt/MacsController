package edu.paulssonlab.macsplugin.runmacs.action;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BasicRunMacsActionView implements RunMacsActionView {
	
	private JPanel panelRunMacsManually;
	private JButton btnInjectSample;
	private JButton btnRunMacs;

	public BasicRunMacsActionView() {
		panelRunMacsManually = new JPanel();
		panelRunMacsManually.setBorder(new TitledBorder(null, "Run Manually", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnInjectSample = new JButton("Inject Sample");
		btnInjectSample.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnInjectSample.setEnabled(true);
		String injectionToolTip = "Inject sample from pressure tube into macs chip using flow pressure and injeciton timing above.";
		btnInjectSample.setToolTipText(injectionToolTip);
		
		btnRunMacs = new JButton("Run MACS");
		btnRunMacs.setEnabled(true);
		btnRunMacs.setFont(new Font("Dialog", Font.PLAIN, 16));
		String runMacsToolTip = "Run Macs protocol one time using the settings defined above.";
		btnRunMacs.setToolTipText(runMacsToolTip);
		
		panelRunMacsManually.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridy = 0;
		
		gbc.ipadx = 0;
		gbc.ipady = 10;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.insets = new Insets(3,3,3,0);
		gbc.gridx = 0;
		panelRunMacsManually.add(btnRunMacs, gbc);
		gbc.insets = new Insets(3,0,3,3);
		gbc.gridx = 1;
		panelRunMacsManually.add(btnInjectSample, gbc);
		
		panelRunMacsManually.setMinimumSize(new Dimension(375,60));
		panelRunMacsManually.setPreferredSize(new Dimension(375,75));
		panelRunMacsManually.setMaximumSize(new Dimension(375,80));
		
	}
	
	@Override
	public JPanel getMainComponent() {
		return panelRunMacsManually;
	}
	
	@Override
	public void setVisible(boolean visible) {
		panelRunMacsManually.setVisible(visible);
	}	


	@Override
	public void setRunMacsAction(Action runMacsAction) {
		btnRunMacs.addActionListener(runMacsAction);
	}
	
	@Override
	public void setInjectCellsAction(Action injectCellsAction) {		
		btnInjectSample.addActionListener(injectCellsAction);
	}
	
	

	

}

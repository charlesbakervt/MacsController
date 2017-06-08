package edu.paulssonlab.macsplugin.cleaner.cleanaction;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;

public class BasicCleanActionView implements CleanActionView {
	
	private JButton btnFullClean;
	private JButton btnCleanWithBleach;
	private JButton btnCleanWithEthanol;
	private JButton btnCleanWithWater;
	private JPanel panelRunCleaning;

	public BasicCleanActionView() {
		
		panelRunCleaning = new JPanel();
		panelRunCleaning.setBorder(new TitledBorder(null, "Run Cleaning Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnCleanWithBleach = new JButton("Bleach");
		btnCleanWithBleach.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnCleanWithEthanol = new JButton("Ethanol");
		btnCleanWithEthanol.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnCleanWithWater = new JButton("Water");
		btnCleanWithWater.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnFullClean = new JButton("Full");
		btnFullClean.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		panelRunCleaning.setLayout(new GridBagLayout());
		
		panelRunCleaning.setMinimumSize(new Dimension(375,80));
		panelRunCleaning.setPreferredSize(new Dimension(375,80));
		panelRunCleaning.setMaximumSize(new Dimension(375,80));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		gbc.gridy = 0;
		
		gbc.ipadx = 0;
		gbc.ipady = 10;
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.insets = new Insets(5,5,5,0);
		gbc.gridx = 0;
		panelRunCleaning.add(btnCleanWithBleach,gbc);
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		panelRunCleaning.add(btnCleanWithEthanol,gbc);
		gbc.gridx = 2;
		panelRunCleaning.add(btnCleanWithWater,gbc);
		gbc.gridx = 3;
		gbc.insets = new Insets(0,0,0,5);
		panelRunCleaning.add(btnFullClean,gbc);
	}

	@Override
	public JPanel getMainComponent() {
		return panelRunCleaning;
	}

	@Override
	public void setVisible(boolean visible) {
		panelRunCleaning.setVisible(visible);
	}

	@Override
	public void setCleanAction(BasicCleaningFluid fluid, Action cleanAction) {
		
		switch (fluid) {
			case BLEACH:
				btnCleanWithBleach.addActionListener(cleanAction);
				break;
			case ETHANOL:
				btnCleanWithEthanol.addActionListener(cleanAction);
				break;
			case WATER:
				btnCleanWithWater.addActionListener(cleanAction);
				break;
			default:
				System.out.println("unknown cleaning liquid");
		}
	}

	@Override
	public void setFullCleanAction(Action cleanAction) {
		btnFullClean.addActionListener(cleanAction);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

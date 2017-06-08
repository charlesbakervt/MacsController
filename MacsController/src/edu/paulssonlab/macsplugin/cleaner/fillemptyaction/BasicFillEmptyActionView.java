package edu.paulssonlab.macsplugin.cleaner.fillemptyaction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;

public class BasicFillEmptyActionView implements FillEmptyActionView {

	private JButton btnFillWithEthanol;
	private JButton btnFillWithWater;
	private JButton btnFillWithBleach;
	private JButton btnEmptyTube;
	private JPanel panelFillPressuretube;
	
	
	
	public BasicFillEmptyActionView() {
		
		panelFillPressuretube = new JPanel();
		panelFillPressuretube.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fill/Empty Pressure Tube", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnFillWithBleach = new JButton("Bleach");
		btnFillWithBleach.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnFillWithEthanol = new JButton("Ethanol");
		btnFillWithEthanol.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnFillWithWater = new JButton("Water");
		btnFillWithWater.setFont(new Font("Dialog", Font.PLAIN, 16));

		btnEmptyTube = new JButton("Empty");
		btnEmptyTube.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		panelFillPressuretube.setLayout(new GridBagLayout());
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
		panelFillPressuretube.add(btnFillWithBleach, gbc);
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		panelFillPressuretube.add(btnFillWithEthanol, gbc);
		gbc.gridx = 2;
		panelFillPressuretube.add(btnFillWithWater, gbc);
		gbc.gridx = 3;
		gbc.insets = new Insets(0,0,0,5);
		panelFillPressuretube.add(btnEmptyTube, gbc);
		
		panelFillPressuretube.setPreferredSize(new Dimension(375,80));
		panelFillPressuretube.setMaximumSize(new Dimension(375,80));
		
	}

	@Override
	public JPanel getMainComponent() {
		return panelFillPressuretube;
	}

	@Override
	public void setVisible(boolean visible) {
		panelFillPressuretube.setVisible(visible);

	}

	@Override
	public void setEmptyPTAction(BasicCleaningFluid fluid, Action emptyAction) {
		// don't need fluid for empty currently but added for future proofing
		btnEmptyTube.addActionListener(emptyAction);		
	}

	@Override
	public void setFillPTAction(BasicCleaningFluid fluid, Action fillAction) {
		switch (fluid) {
		case BLEACH:
			btnFillWithBleach.addActionListener(fillAction);
			break;
		case ETHANOL:
			btnFillWithEthanol.addActionListener(fillAction);
			break;
		case WATER:
			btnFillWithWater.addActionListener(fillAction);
			break;
		default:
			System.out.println("unknown cleaning liquid");
	}
		
	}

}

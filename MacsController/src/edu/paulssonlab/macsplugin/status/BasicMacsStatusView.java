package edu.paulssonlab.macsplugin.status;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.paulssonlab.macsplugin.api.MacsDeviceState;

public class BasicMacsStatusView implements MacsStatusView {

	private JPanel panelPressure;
	private JLabel lblFlow;
	private JLabel lblFlowPressure;
	private JLabel lblValve;
	private JLabel lblValvePressure;
	private JLabel lblFill;
	private JLabel lblFillPressure;
	private JLabel lblMacs;
	private JLabel lblMacsState;
	private JLabel lblClean;
	private JLabel lblCleaningState;
	private JLabel lblSystem;
	private JLabel lblSystemStatus;
	private JPanel panelStates;
	private JPanel panelStatus;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicMacsStatusView macsView = new BasicMacsStatusView();
					JPanel mPanel = macsView.getMainComponent();
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 350, 95);
					frame.setContentPane(mPanel);
					
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	
	public BasicMacsStatusView() {
		panelStatus = new JPanel();
		panelStatus.setLayout(new BoxLayout(panelStatus, BoxLayout.PAGE_AXIS));
		panelStatus.setPreferredSize(new Dimension(350, 100));
		panelStatus.setMaximumSize(new Dimension(390, 120));
		
		panelPressure = new JPanel();
		panelPressure.setPreferredSize(new Dimension(350, 15));
		panelPressure.setMaximumSize(new Dimension(390, 70));
		panelPressure.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panelStates = new JPanel();
		panelStates.setPreferredSize(new Dimension(350, 70));
		panelStates.setMaximumSize(new Dimension(390, 70));
		panelStates.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		lblFlow = new JLabel("Flow");
		lblValve = new JLabel("Valve:");
		lblFill = new JLabel("Fill:");
		
		lblFlowPressure = new JLabel("-");
		lblFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lblValvePressure = new JLabel("-");
		lblValvePressure.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lblFillPressure = new JLabel("-");
		lblFillPressure.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lblMacs = new JLabel("MACS Device State:");
		lblClean = new JLabel("Cleaning State:");
		lblSystem = new JLabel("System Status:");
		
		lblMacsState = new JLabel("-");
		lblMacsState.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMacsState.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblCleaningState = new JLabel("-");
		lblCleaningState.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lblSystemStatus = new JLabel("-");
		lblSystemStatus.setFont(new Font("Dialog", Font.PLAIN, 12));

		GridBagLayout gbl_panelPressure = new GridBagLayout();
		panelPressure.setLayout(gbl_panelPressure);

		GridBagConstraints gbc_lblFlow = new GridBagConstraints();
		gbc_lblFlow.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlow.gridx = 0;
		gbc_lblFlow.gridy = 0;
		panelPressure.add(lblFlow, gbc_lblFlow);
		
		GridBagConstraints gbc_lblFlowPressure = new GridBagConstraints();
		gbc_lblFlowPressure.weightx = 1.0;
		gbc_lblFlowPressure.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlowPressure.anchor = GridBagConstraints.LINE_START;
		gbc_lblFlowPressure.gridx = 1;
		gbc_lblFlowPressure.gridy = 0;
		panelPressure.add(lblFlowPressure, gbc_lblFlowPressure);
		
		GridBagConstraints gbc_lblValve = new GridBagConstraints();
		gbc_lblValve.insets = new Insets(0, 0, 5, 5);
		gbc_lblValve.gridx = 2;
		gbc_lblValve.gridy = 0;
		panelPressure.add(lblValve, gbc_lblValve);
		
		GridBagConstraints gbc_lblValvePressure = new GridBagConstraints();
		gbc_lblValvePressure.weightx = 1.0;
		gbc_lblValvePressure.insets = new Insets(0, 0, 5, 5);
		gbc_lblValvePressure.anchor = GridBagConstraints.LINE_START;
		gbc_lblValvePressure.gridx = 3;
		gbc_lblValvePressure.gridy = 0;
		panelPressure.add(lblValvePressure, gbc_lblValvePressure);
		
		GridBagConstraints gbc_lblFill = new GridBagConstraints();
		gbc_lblFill.insets = new Insets(0, 0, 5, 5);
		gbc_lblFill.gridx = 4;
		gbc_lblFill.gridy = 0;
		panelPressure.add(lblFill, gbc_lblFill);
		
		GridBagConstraints gbc_lblFillPressure = new GridBagConstraints();
		gbc_lblFillPressure.weightx = 1.0;
		gbc_lblFillPressure.insets = new Insets(0, 0, 5, 0);
		gbc_lblFillPressure.anchor = GridBagConstraints.LINE_START;
		gbc_lblFillPressure.gridx = 5;
		gbc_lblFillPressure.gridy = 0;
		panelPressure.add(lblFillPressure, gbc_lblFillPressure);
		
		
		
		GridBagLayout gbl_panelStatus = new GridBagLayout();
		panelStates.setLayout(gbl_panelStatus);
		
		GridBagConstraints gbc_lblMacs = new GridBagConstraints();
		gbc_lblMacs.gridx = 0;
		gbc_lblMacs.gridy = 0;
		gbc_lblMacs.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMacs.insets = new Insets(0, 0, 5, 5);
		gbc_lblMacs.anchor = GridBagConstraints.LINE_START;
		gbc_lblMacs.gridwidth = 3;
		
		panelStates.add(lblMacs, gbc_lblMacs);
		
		GridBagConstraints gbc_lblMacsState = new GridBagConstraints();
		gbc_lblMacsState.gridy = 0;
		gbc_lblMacsState.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMacsState.gridwidth = GridBagConstraints.REMAINDER;
		gbc_lblMacsState.anchor = GridBagConstraints.LINE_START;
		gbc_lblMacsState.insets = new Insets(0, 0, 5, 0);
		panelStates.add(lblMacsState, gbc_lblMacsState);
		
		GridBagConstraints gbc_lblClean = new GridBagConstraints();
		gbc_lblClean.gridx = 0;
		gbc_lblClean.gridy = 1;
		gbc_lblClean.gridwidth = 2;
		gbc_lblClean.anchor = GridBagConstraints.LINE_START;
		gbc_lblClean.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClean.insets = new Insets(0, 0, 5, 5);
		
		panelStates.add(lblClean, gbc_lblClean);
		
		
		GridBagConstraints gbc_lblCleaningState = new GridBagConstraints();
		gbc_lblCleaningState.gridy = 1;
		gbc_lblCleaningState.gridwidth = GridBagConstraints.REMAINDER;
		gbc_lblCleaningState.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCleaningState.anchor = GridBagConstraints.LINE_START;
		gbc_lblCleaningState.insets = new Insets(0, 0, 5, 0);

		panelStates.add(lblCleaningState, gbc_lblCleaningState);
		
		
		GridBagConstraints gbc_lblSystem = new GridBagConstraints();
		gbc_lblSystem.gridx = 0;
		gbc_lblSystem.gridy = 2;
		gbc_lblSystem.gridwidth = 2;
		gbc_lblSystem.anchor = GridBagConstraints.LINE_START;
		gbc_lblSystem.insets = new Insets(0, 0, 0, 5);
		panelStates.add(lblSystem, gbc_lblSystem);
		
		
		GridBagConstraints gbc_lblSystemStatus = new GridBagConstraints();
		gbc_lblSystemStatus.gridy = 2;
		gbc_lblSystemStatus.gridwidth = GridBagConstraints.REMAINDER;
		gbc_lblSystemStatus.weightx = 1.0;
		gbc_lblSystemStatus.anchor = GridBagConstraints.LINE_START;
		
		panelStates.add(lblSystemStatus, gbc_lblSystemStatus);
		
		panelStatus.add(Box.createRigidArea(new Dimension(0,5)));
		panelStatus.add(panelPressure);
		panelStatus.add(panelStates);
		panelStatus.add(Box.createRigidArea(new Dimension(0,5)));
	}
	
	

	
	@Override
	public JPanel getMainComponent() {
		return panelStatus;
	}
	
	@Override
	public void setVisible(boolean visible) {
		panelStatus.setVisible(visible);
	}

	@Override
	public void setCurrentFlowPressure(Float psiFlowPressure) {
		lblFlowPressure.setText(String.format("%.1f psi", psiFlowPressure));
	}

	@Override
	public void setCurrentValvePressure(Float psiValvePressure) {
		lblValvePressure.setText(String.format("%.1f psi", psiValvePressure));
	}
	

	@Override
	public void setCurrentFillPressure(Float psiFillPressure) {
		lblFillPressure.setText(String.format("%.1f psi", psiFillPressure));
	}

	@Override
	public void setCurrentMacsState(MacsDeviceState mMacsState) {
		lblMacsState.setText(mMacsState.toString());
		
	}

	@Override
	public void setCurrentCleaningState(String mCleaningState) {
		lblCleaningState.setText(mCleaningState);
	}

	@Override
	public void setCurrentMacsSystemStatus(String statusMessage) {
		lblSystemStatus.setText(statusMessage);
	}
	
	

}

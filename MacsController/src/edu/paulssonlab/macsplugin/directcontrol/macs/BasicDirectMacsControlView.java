package edu.paulssonlab.macsplugin.directcontrol.macs;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import edu.paulssonlab.macsplugin.api.MacsDeviceState;


public class BasicDirectMacsControlView implements DirectMacsControlView {
	
	private final ButtonGroup macsButtonGroup = new ButtonGroup();
	private JLabel lblFlowPressure;
	private JLabel lblValvePressure;
	private JSpinner spinnerFlowPressure;
	private JSpinner spinnerValvePressure;
	private JPanel panelMacsConfig;
	private JButton btnApply;
	private JButton btnCancel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectMacsControlView macsView = new BasicDirectMacsControlView();
					JPanel mPanel = macsView.getMainComponent();
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 423, 774);
					frame.setContentPane(mPanel);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BasicDirectMacsControlView() {
		
		panelMacsConfig = new JPanel();
		panelMacsConfig.setLayout(new BoxLayout(panelMacsConfig, BoxLayout.PAGE_AXIS));
		panelMacsConfig.setBorder(new TitledBorder(null, "Macs Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelMacsPressure = new JPanel();
		panelMacsPressure.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panelMacsRadioBtns = new JPanel();
		panelMacsRadioBtns.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelMacsRadioBtns.setLayout(new GridBagLayout());
		panelMacsRadioBtns.setPreferredSize(new Dimension(375,150));
		panelMacsRadioBtns.setMaximumSize(new Dimension(375,150));
		
		JPanel panelApplyReset = new JPanel();
		panelApplyReset.setLayout(new BoxLayout(panelApplyReset, BoxLayout.LINE_AXIS));
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.ipady = 10;
		//cons.gridx = 0;
		
		lblFlowPressure = new JLabel("Flow Pressure (psi)");
		lblFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblValvePressure = new JLabel("Valve Pressure (psi)");
		lblValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerFlowPressure = new JSpinner();
		spinnerFlowPressure.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(32), new Float(0.1)));
		spinnerFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerValvePressure = new JSpinner();
		spinnerValvePressure.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(32), new Float(0.1)));
		spinnerValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
	
		btnApply = new JButton("Apply");
		btnApply.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		MacsDeviceState[] coreStates = MacsDeviceState.values();
		int nCoreStates = coreStates.length;
		
		JToggleButton[] rdbtnMacsStates = new JToggleButton[nCoreStates + 1];
		
		for (int i = 0; i < nCoreStates; i++) {
			rdbtnMacsStates[i] = new JToggleButton(coreStates[i].toString());
			macsButtonGroup.add(rdbtnMacsStates[i]);
			cons.gridy = i/3;
			panelMacsRadioBtns.add(rdbtnMacsStates[i],cons);
		}
		
		GroupLayout gl_panel = new GroupLayout(panelMacsPressure);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				//.addGap(0, 300, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFlowPressure, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblValvePressure, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(spinnerFlowPressure, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerValvePressure, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFlowPressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValvePressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(spinnerFlowPressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerValvePressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(5))
		);
		
		panelMacsPressure.setLayout(gl_panel);
		panelMacsPressure.setMaximumSize(new Dimension(300,95));
		panelMacsPressure.setPreferredSize(new Dimension(300,85));
		
		panelApplyReset.add(btnApply);
		panelApplyReset.add(btnCancel);
		
		panelMacsConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelMacsConfig.add(panelMacsPressure);
		panelMacsConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelMacsConfig.add(panelMacsRadioBtns);
		panelMacsConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelMacsConfig.add(panelApplyReset);
		panelMacsConfig.setMaximumSize(new Dimension(350,350));
		panelMacsConfig.setPreferredSize(new Dimension(350,350));
		
	}

	
	@Override 
	public JPanel getMainComponent() {
		return panelMacsConfig; 
	}
	
	@Override
	public void setVisible(boolean visible) {
		panelMacsConfig.setVisible(visible);
	}
	
	

	@Override
	public float getRequestedValvePressure() {
		return (Float) spinnerValvePressure.getValue();
	}
	
	@Override
	public float getRequestedFlowPressure() {
		return (Float) spinnerFlowPressure.getValue();
	}
	
	@Override
	public MacsDeviceState getRequestedMacsState() {
		Enumeration<AbstractButton> mbtns = macsButtonGroup.getElements();
		boolean foundBtn = false;
		String btnText = "";
		while (!foundBtn) {
			AbstractButton btn = mbtns.nextElement();
			btnText = btn.getActionCommand();
			if (btn.isSelected()) {
				foundBtn = true;
			}
		}
		return MacsDeviceState.valueOf(btnText);
	}
	
	
	
	@Override
	public void setCurrentFlowPressure(Float psiFlowPressure) {
		spinnerFlowPressure.setValue(psiFlowPressure);
	}

	@Override
	public void setCurrentValvePressure(Float psiValvePressure) {
		spinnerValvePressure.setValue(psiValvePressure);
	}

	@Override
	public void setCurrentMacsState(MacsDeviceState mMacsState) {
		Enumeration<AbstractButton> mbtns = macsButtonGroup.getElements();
		boolean foundBtn = false;
		while (!foundBtn) {
			AbstractButton btn = mbtns.nextElement();
			String btnText = btn.getActionCommand();
			if (btnText.equals(mMacsState.toString())) {
				btn.setSelected(true);
				foundBtn = true;
			}
		}
	}

	@Override
	public void setApplyFlowPressureAction(Action changeFlowPressureAction) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setApplyValvePressureAction(Action changeValvePressureAction) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setApplyCoreMacsStateAction(Action changeMacsStateAction) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setApplyMacsAction(Action action) {
		btnApply.addActionListener(action);
	}
	
	@Override
	public void setCancelMacsAction(Action action) {
		btnCancel.addActionListener(action);
	}

	

}

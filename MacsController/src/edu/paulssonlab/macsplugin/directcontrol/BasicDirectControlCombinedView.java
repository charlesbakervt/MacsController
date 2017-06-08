package edu.paulssonlab.macsplugin.directcontrol;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.directcontrol.cleaner.DirectCleaningControlView;
import edu.paulssonlab.macsplugin.directcontrol.macs.DirectMacsControlView;

public class BasicDirectControlCombinedView implements DirectCleaningControlView, DirectMacsControlView {

	private final ButtonGroup cleaningButtonGroup = new ButtonGroup();
	private final ButtonGroup macsButtonGroup = new ButtonGroup();

	private JPanel panelSystemConfig;
	private JLabel lblFillPressure;
	private JSpinner spinnerFillPressure;
	
	private JLabel lblFlowPressure;
	private JLabel lblValvePressure;
	private JSpinner spinnerFlowPressure;
	private JSpinner spinnerValvePressure;
	private JButton btnApplyPressure;
	private JButton btnCancelPressure;
	private JButton btnApplyMacs;
	private JButton btnCancelMacs;
	private JButton btnApplyClean;
	private JButton btnCancelClean;
	private JButton btnApplyMaster;
	private JButton btnCancelMaster;
	private JPanel panelPressureSettings;
	private JPanel panelTglBtns;
	private JPanel panelMacsTglBtns;
	private JPanel panelCleaningTglBtns;
	private float flowPressure = 0.0f;
	private float fillPressure = 0.0f;
	private boolean applyImmediately = false;
	private boolean useMasterControl = true;
	private JToggleButton[] tglBtnMacsStates;
	private JToggleButton[] tglBtnCleaningStates;
	private JPanel panelPressureApplyReset;
	private JPanel panelApplyReset;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectControlCombinedView macsView = new BasicDirectControlCombinedView();
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
	
	public BasicDirectControlCombinedView() {
		
		Dimension togglePrefDimensionLeft = new Dimension(150,310);
		Dimension togglePrefDimensionRight = new Dimension(220,310);
		Dimension toggleMaxDimension = new Dimension(290,310);
		
		JPanel panelSystemPressure = new JPanel();
		panelSystemPressure.setLayout(new BoxLayout(panelSystemPressure, BoxLayout.PAGE_AXIS));
		panelSystemPressure.setBorder(new TitledBorder(null, "Pressure Regulator Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panelSystemConfig = new JPanel();
		panelSystemConfig.setLayout(new BoxLayout(panelSystemConfig, BoxLayout.PAGE_AXIS));
		//panelSystemConfig.setBorder(new TitledBorder(null, "System Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		flowPressure = 0;
		fillPressure = 0;
		
		panelPressureSettings = new JPanel();
		
		
		panelTglBtns = new JPanel();
		panelTglBtns.setLayout(new BoxLayout(panelTglBtns, BoxLayout.LINE_AXIS));
		
		
		panelMacsTglBtns = new JPanel();
		panelMacsTglBtns.setLayout(new GridBagLayout());
		panelMacsTglBtns.setBorder(new TitledBorder(null, "MACS Device State", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMacsTglBtns.setPreferredSize(togglePrefDimensionLeft);
		panelMacsTglBtns.setMaximumSize(toggleMaxDimension);
		
		panelCleaningTglBtns = new JPanel();
		panelCleaningTglBtns.setLayout(new GridBagLayout());
		panelCleaningTglBtns.setBorder(new TitledBorder(null, "Cleaning Device State", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCleaningTglBtns.setPreferredSize(togglePrefDimensionRight);
		panelCleaningTglBtns.setMaximumSize(toggleMaxDimension);
		
		panelPressureApplyReset = new JPanel();
		panelPressureApplyReset.setLayout(new BoxLayout(panelPressureApplyReset, BoxLayout.LINE_AXIS));
		
		panelApplyReset = new JPanel();
//		panelApplyReset.setLayout(new BoxLayout(panelApplyReset, BoxLayout.LINE_AXIS));
		
		lblFlowPressure = new JLabel("Flow Layer Pressure (psi)");
		String flowPressureToolTip = "Pressure setting for regulator responsible for flowing liquid from the pressure tube.";
		lblFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblFlowPressure.setToolTipText(flowPressureToolTip);
		
		lblValvePressure = new JLabel("Control Layer Pressure (psi)");
		String valvePressureToolTip = "Pressure setting for regulator responsible for actuating the Control Layer valve.";
		lblValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblValvePressure.setToolTipText(valvePressureToolTip);
		
		lblFillPressure = new JLabel("Fill Pressure (psi)");
		lblFillPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		String fillPressureToolTip = "<html> Pressure setting for regulator responsible for filling the pressure tube, usually with cleaning liquid. <br>" + 
				 "In standard setup, this pressure regulator is the same as the flow pressure regulator. </html>";
		lblFillPressure.setToolTipText(fillPressureToolTip);
		
		spinnerFlowPressure = new JSpinner();
		spinnerFlowPressure.setModel(new SpinnerNumberModel(Float.valueOf(flowPressure), new Float(0), new Float(32), new Float(0.1)));
		spinnerFlowPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerValvePressure = new JSpinner();
		spinnerValvePressure.setModel(new SpinnerNumberModel(Float.valueOf(fillPressure), new Float(0), new Float(32), new Float(0.1)));
		spinnerValvePressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerFillPressure = new JSpinner();
		spinnerFillPressure.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(32), new Float(0.1)));
		spinnerFillPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		
		btnApplyPressure = new JButton("Apply");
		btnApplyPressure.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btnCancelPressure = new JButton("Cancel");
		btnCancelPressure.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btnApplyMacs = new JButton("Apply");
		btnApplyMacs.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnCancelMacs = new JButton("Cancel");
		btnCancelMacs.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnApplyClean = new JButton("Apply");
		btnApplyClean.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnCancelClean = new JButton("Cancel");
		btnCancelClean.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnApplyMaster = new JButton("Apply");
		btnApplyMaster.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnCancelMaster = new JButton("Cancel");
		btnCancelMaster.setFont(new Font("Dialog", Font.BOLD, 14));
		
		layoutSystemPressurePanel();
		panelPressureSettings.setMaximumSize(new Dimension(375,130));
		panelPressureSettings.setPreferredSize(new Dimension(375,115));
		
		layoutToggleBtnPanel();
		layoutApplyResetPanel();

			
//		panelApplyReset.add(btnApplyMaster);
//		panelApplyReset.add(btnCancelMaster);
		
		panelPressureApplyReset.add(btnApplyPressure);
		panelPressureApplyReset.add(btnCancelPressure);
		
		panelSystemPressure.add(panelPressureSettings);
		
		if (!useMasterControl) {
		panelSystemPressure.add(Box.createRigidArea(new Dimension(0,10)));
		panelSystemPressure.add(panelPressureApplyReset);
		panelSystemPressure.add(Box.createRigidArea(new Dimension(0,10)));
		}
		
		coupleFlowFillSpinners(!applyImmediately);
		coupleMacsCleanStates(!applyImmediately);
		viewMasterApplyCancel(useMasterControl);
		viewSeperateApplyCancel(!useMasterControl);
		
		panelSystemConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelSystemConfig.add(panelSystemPressure);
		panelSystemConfig.add(Box.createRigidArea(new Dimension(0,1)));
		panelSystemConfig.add(panelTglBtns);
		panelSystemConfig.add(Box.createRigidArea(new Dimension(0,1)));
		panelSystemConfig.add(panelApplyReset);
		panelSystemConfig.setMaximumSize(new Dimension(375,700));
		panelSystemConfig.setPreferredSize(new Dimension(375,600));
	}
	
	private void viewMasterApplyCancel(boolean visible) {
		
		btnApplyMaster.setVisible(visible);
		btnCancelMaster.setVisible(visible);
		panelApplyReset.setVisible(visible);
			
	}
	
	private void viewSeperateApplyCancel(boolean visible) {
		btnApplyClean.setVisible(visible);
		btnCancelClean.setVisible(visible);
		
		btnApplyMacs.setVisible(visible);
		btnCancelMacs.setVisible(visible);
		
		btnApplyPressure.setVisible(visible);
		btnCancelPressure.setVisible(visible);
		panelPressureApplyReset.setVisible(visible);
	}
	
	
	
	protected void coupleMacsCleanStates(boolean couple) {
		AbstractAction macsBtnAction = new AbstractAction() {
			private static final long serialVersionUID = -3842500324321860484L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getRequestedMacsState() != MacsDeviceState.ALLOW_FILLING) {
					setCurrentCleaningState(CleaningDeviceState.ALL_OFF);
				}
			}
			
		};
		
		AbstractAction cleaningBtnAction = new AbstractAction() {
			private static final long serialVersionUID = -1617621447590374965L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getRequestedCleaningState() != CleaningDeviceState.ALL_OFF) {
					setCurrentMacsState(MacsDeviceState.ALLOW_FILLING);
				}
			}
			
		};
		
		if (couple) {
			addCoreMacsStateAction(macsBtnAction);
			addCleaningStateAction(cleaningBtnAction);
		}
		
	}
	
	protected void coupleFlowFillSpinners(boolean couple) {

		if (couple) {
			
			spinnerFlowPressure.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					float mFlowPressure = getRequestedFlowPressure();
					if (flowPressure != mFlowPressure) {
						flowPressure = mFlowPressure;
						setCurrentFillPressure(flowPressure);
					}
				}
			});

			spinnerFillPressure.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					float mFillPressure = getRequestedFillPressure();
					if (fillPressure != mFillPressure) {
						fillPressure = mFillPressure;
						setCurrentFlowPressure(fillPressure);
					}
				}
			});
		}
		
	}
	
	private void layoutApplyResetPanel() {
		panelApplyReset.setLayout(new GridBagLayout());
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
		panelApplyReset.add(btnApplyMaster, gbc);
		gbc.insets = new Insets(3,5,3,3);
		gbc.gridx = 1;
		panelApplyReset.add(btnCancelMaster, gbc);
		
		panelApplyReset.setMinimumSize(new Dimension(250,37));
		panelApplyReset.setPreferredSize(new Dimension(275,70));
		panelApplyReset.setMaximumSize(new Dimension(295,75));
	}
	
	private void layoutToggleBtnPanel() {
		GridBagConstraints gbc_macs = new GridBagConstraints();
		gbc_macs.fill = GridBagConstraints.BOTH;
		gbc_macs.weightx = 1;
		gbc_macs.weighty = 1;
		gbc_macs.gridwidth = 2;
		gbc_macs.ipady = 10;
		gbc_macs.gridx = 0;
		int padLeftRight = 3;
		
		MacsDeviceState[] coreStates = MacsDeviceState.values();
		int nCoreStates = coreStates.length;
		
		tglBtnMacsStates = new JToggleButton[nCoreStates + 1];
		gbc_macs.insets = new Insets(7,padLeftRight,0,padLeftRight);
		for (int i = 0; i < nCoreStates; i++) {
			tglBtnMacsStates[i] = new JToggleButton(coreStates[i].toString());
			tglBtnMacsStates[i].setFont(new Font("Dialog", Font.PLAIN, 14));
			macsButtonGroup.add(tglBtnMacsStates[i]);
			gbc_macs.gridy = i;
			if (i > 0) {
				gbc_macs.insets = new Insets(0,padLeftRight,0,padLeftRight);
			}
			if (i == nCoreStates - 1) {
				gbc_macs.insets = new Insets(0,padLeftRight,7,padLeftRight);
			}
			panelMacsTglBtns.add(tglBtnMacsStates[i],gbc_macs);
		}
		gbc_macs.gridy = nCoreStates;
		gbc_macs.gridwidth = 1;
		gbc_macs.insets = new Insets(0, 7, 7, 1);
		
		panelMacsTglBtns.add(btnApplyMacs,gbc_macs);
		gbc_macs.insets = new Insets(0, 1, 7, 7);
		gbc_macs.gridx = 1;
		panelMacsTglBtns.add(btnCancelMacs,gbc_macs);
		
		
		GridBagConstraints gbc_clean = new GridBagConstraints();
		gbc_clean.fill = GridBagConstraints.BOTH;
		gbc_clean.weightx = 1;
		gbc_clean.weighty = 1;
		gbc_clean.gridwidth = 2;
		gbc_clean.ipady = 10;
		gbc_clean.gridx = 0;
		
		CleaningDeviceState[] cleaningStates = CleaningDeviceState.values();
		int nCleaningStates = cleaningStates.length;
	
		tglBtnCleaningStates = new JToggleButton[nCleaningStates + 1];
		
		gbc_clean.insets = new Insets(7,padLeftRight,0,padLeftRight);
		for (int i = 0; i < nCleaningStates; i++) {
			tglBtnCleaningStates[i] = new JToggleButton(cleaningStates[i].toString());
			tglBtnCleaningStates[i].setFont(new Font("Dialog", Font.PLAIN, 14));
			cleaningButtonGroup.add(tglBtnCleaningStates[i]);
			gbc_clean.gridy = i;
			if (i > 0) {
				gbc_clean.insets = new Insets(0,padLeftRight,0,padLeftRight);
			}
			if (i == nCleaningStates - 1) {
				gbc_clean.insets = new Insets(0,padLeftRight,7,padLeftRight);
			}
			
			panelCleaningTglBtns.add(tglBtnCleaningStates[i],gbc_clean);
		}
		
		gbc_clean.gridy = nCleaningStates;
		gbc_clean.gridwidth = 1;
		gbc_clean.insets = new Insets(0, 7, 7, 1);
		
		panelCleaningTglBtns.add(btnApplyClean,gbc_clean);
		gbc_clean.insets = new Insets(0, 1, 7, 7);
		gbc_clean.gridx = 1;
		panelCleaningTglBtns.add(btnCancelClean,gbc_clean);
		
		panelTglBtns.add(panelMacsTglBtns);
		panelTglBtns.add(panelCleaningTglBtns);
	
		
	}
	
	private void layoutSystemPressurePanel() {
		
		panelPressureSettings.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		
		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.insets = new Insets(3,15,0,0);
		panelPressureSettings.add(lblFlowPressure, gbc);
		gbc.insets = new Insets(0,15,0,0);
		gbc.gridy = 1;
		panelPressureSettings.add(lblValvePressure, gbc);
		gbc.gridy = 2;
		gbc.insets = new Insets(0,15,3,0);
		panelPressureSettings.add(lblFillPressure, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 20;
		gbc.ipady = 12;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(3,0,0,35);
		panelPressureSettings.add(spinnerFlowPressure, gbc);
		gbc.insets = new Insets(0,0,0,35);
		gbc.gridy = 1;
		panelPressureSettings.add(spinnerValvePressure, gbc);
		gbc.insets = new Insets(0,0,3,35);
		gbc.gridy = 2;
		panelPressureSettings.add(spinnerFillPressure, gbc);
	}
	
	//@Override 
	public JPanel getMainComponent() {
		return panelSystemConfig; 
	}
	
	//@Override
	public void setVisible(boolean visible) {
		panelSystemConfig.setVisible(visible);
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
//		return MacsDeviceState.valueOf(btnText);
		return MacsDeviceState.fromString(btnText);
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
	public void setApplyCoreMacsStateAction(Action action) {
		if (applyImmediately) {
			addCoreMacsStateAction(action);
		}
	}
	
	private void addCoreMacsStateAction(Action action) {
		Enumeration<AbstractButton> mbtns = macsButtonGroup.getElements();
		while (mbtns.hasMoreElements()) {
			JToggleButton btn = (JToggleButton) mbtns.nextElement();
			btn.addActionListener(action);
		}
	}
	
	@Override
	public float getRequestedFillPressure() {
		return (Float) spinnerFillPressure.getValue();
	}
	
	@Override
	public CleaningDeviceState getRequestedCleaningState() {
		Enumeration<AbstractButton> mbtns = cleaningButtonGroup.getElements();
		boolean foundBtn = false;
		String btnText = "";
		while (!foundBtn) {
			AbstractButton btn = mbtns.nextElement();
			btnText = btn.getActionCommand();
			if (btn.isSelected()) {
				foundBtn = true;
			}
		}
		return CleaningDeviceState.fromString(btnText);
	}

	@Override
	public void setCurrentFillPressure(Float psiFillPressure) {
		spinnerFillPressure.setValue(psiFillPressure);
		
	}

	@Override
	public void setCurrentCleaningState(CleaningDeviceState mDeviceState) {
		Enumeration<AbstractButton> mbtns = cleaningButtonGroup.getElements();
		boolean foundBtn = false;
		while (!foundBtn) {
			AbstractButton btn = mbtns.nextElement();
			String btnText = btn.getActionCommand();
			if (btnText.equals(mDeviceState.toString())) {
				btn.setSelected(true);
				foundBtn = true;
			}
		}
	}
	
	
	@Override
	public void setApplyFillPressureListener(ChangeListener listener) {
		if (applyImmediately) {
			spinnerFillPressure.addChangeListener(listener);
		}
	}

	@Override
	public void setApplyCleaningStateAction(Action action) {
		if (applyImmediately) {
			addCleaningStateAction(action);
		}
	}
	
	private void addCleaningStateAction(Action action) {
			Enumeration<AbstractButton> mbtns = cleaningButtonGroup.getElements();
			while (mbtns.hasMoreElements()) {
				JToggleButton btn = (JToggleButton) mbtns.nextElement();
				btn.addActionListener(action);
			}
	}
	
	@Override
	public void setApplyCleanAction(Action action) {
		if (useMasterControl) {
			btnApplyMaster.addActionListener(action);
		}
		else {
			btnApplyClean.addActionListener(action);
		}
		
	}
	
	@Override
	public void setCancelCleanAction(Action action) {
		if (useMasterControl) {
			btnCancelMaster.addActionListener(action);
		}
		else {
			btnCancelClean.addActionListener(action);
		}
	}
	
	@Override
	public void setApplyMacsAction(Action action) {
		if (useMasterControl) {
			btnApplyMaster.addActionListener(action);
		}
		else {
			btnApplyMacs.addActionListener(action);
		}
	}
	
	@Override
	public void setCancelMacsAction(Action action) {
		if (useMasterControl) {
			btnCancelMaster.addActionListener(action);
		}
		else {
			btnCancelMacs.addActionListener(action);
		}
	}


}

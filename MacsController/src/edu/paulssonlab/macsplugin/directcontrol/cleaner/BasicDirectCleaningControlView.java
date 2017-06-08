package edu.paulssonlab.macsplugin.directcontrol.cleaner;

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
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;

public class BasicDirectCleaningControlView implements DirectCleaningControlView {
	
	private JPanel panelCleaningConfig;
	private JLabel lblFillPressure;
	private JSpinner spinnerFillPressure;
	private final ButtonGroup cleaningButtonGroup = new ButtonGroup();
	private final ButtonGroup macsButtonGroup = new ButtonGroup();
	private JButton btnApply;
	private JButton btnCancel;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectCleaningControlView macsView = new BasicDirectCleaningControlView();
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
	
	public BasicDirectCleaningControlView() {
		
		panelCleaningConfig = new JPanel();
		panelCleaningConfig.setLayout(new BoxLayout(panelCleaningConfig, BoxLayout.PAGE_AXIS));
		panelCleaningConfig.setBorder(new TitledBorder(null, "Cleaning Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelCleaningPressure = new JPanel();
		panelCleaningPressure.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panelCleaningRadioBtns = new JPanel();
		panelCleaningRadioBtns.setLayout(new GridBagLayout());
		panelCleaningRadioBtns.setPreferredSize(new Dimension(100,150));
		panelCleaningRadioBtns.setMaximumSize(new Dimension(100,150));
		
		JPanel panelMacsRadioBtns = new JPanel();
		panelMacsRadioBtns.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelMacsRadioBtns.setLayout(new GridBagLayout());
		panelMacsRadioBtns.setPreferredSize(new Dimension(100,150));
		panelMacsRadioBtns.setMaximumSize(new Dimension(100,150));
		
		JPanel panelApplyReset = new JPanel();
		panelApplyReset.setLayout(new BoxLayout(panelApplyReset, BoxLayout.LINE_AXIS));
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.ipady = 10;
		
		lblFillPressure = new JLabel("Fill Pressure (psi)");
		lblFillPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		spinnerFillPressure = new JSpinner();
		spinnerFillPressure.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(32), new Float(0.1)));
		spinnerFillPressure.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		btnApply = new JButton("Apply");
		btnApply.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		MacsDeviceState[] coreStates = MacsDeviceState.values();
		int nCoreStates = coreStates.length;
		
		JToggleButton[] rdbtnMacsStates = new JToggleButton[nCoreStates + 1];
		
		CleaningDeviceState[] cleaningStates = CleaningDeviceState.values();
		int nCleaningStates = cleaningStates.length;
	
		JToggleButton[] rdbtnCleaningStates = new JToggleButton[nCleaningStates + 1];
		
		for (int i = 0; i < nCoreStates; i++) {
			rdbtnMacsStates[i] = new JToggleButton(coreStates[i].toString());
			macsButtonGroup.add(rdbtnMacsStates[i]);
			cons.gridy = i/3;
			panelMacsRadioBtns.add(rdbtnMacsStates[i],cons);
		}
		
		for (int i = 0; i < nCleaningStates; i++) {
			rdbtnCleaningStates[i] = new JToggleButton(cleaningStates[i].toString());
			cleaningButtonGroup.add(rdbtnCleaningStates[i]);
			cons.gridy = i/2;
			panelCleaningRadioBtns.add(rdbtnCleaningStates[i],cons);
		}
		
		GroupLayout gl_fillPanel = new GroupLayout(panelCleaningPressure);
		gl_fillPanel.setHorizontalGroup(
				gl_fillPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fillPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fillPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFillPressure, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_fillPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(spinnerFillPressure, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_fillPanel.setVerticalGroup(
				gl_fillPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fillPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_fillPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_fillPanel.createSequentialGroup()
							.addComponent(lblFillPressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_fillPanel.createSequentialGroup()
							.addComponent(spinnerFillPressure, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(5))
		);
		
		panelCleaningPressure.setLayout(gl_fillPanel);
		panelCleaningPressure.setMaximumSize(new Dimension(300,55));
		panelCleaningPressure.setPreferredSize(new Dimension(300,55));
		
		panelApplyReset.add(btnApply);
		panelApplyReset.add(btnCancel);
		
		panelCleaningConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelCleaningConfig.add(panelCleaningPressure);
		panelCleaningConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelCleaningConfig.add(panelCleaningRadioBtns);
		panelCleaningConfig.add(Box.createRigidArea(new Dimension(0,10)));
		panelCleaningConfig.add(panelApplyReset);
		panelCleaningConfig.setMaximumSize(new Dimension(350,300));
		panelCleaningConfig.setPreferredSize(new Dimension(350,300));
		
	}

	
	@Override 
	public JPanel getMainComponent() {
		return panelCleaningConfig; 
	}
	
	@Override
	public void setVisible(boolean visible) {
		panelCleaningConfig.setVisible(visible);
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
		return CleaningDeviceState.valueOf(btnText);
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
		// TODO Auto-generated method stub
	}

	@Override
	public void setApplyCleaningStateAction(Action action) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setApplyCleanAction(Action action) {
		btnApply.addActionListener(action);
	}
	
	@Override
	public void setCancelCleanAction(Action action) {
		btnCancel.addActionListener(action);
	}


}

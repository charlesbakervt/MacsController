package edu.paulssonlab.macsplugin.directcontrol.macs;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;


public class DirectMacsControlController {
	private DirectMacsControlView view;
	
	public DirectMacsControlController(final DirectMacsControlView view, final CoreMacsDevice macsDevice) {
		this.view = view;
	
		view.setApplyMacsAction(new AbstractAction() {
			private static final long serialVersionUID = 4687615743074222676L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				MacsDeviceState mState = view.getRequestedMacsState();
				float flowPressure = view.getRequestedFlowPressure();
				float valvePressure = view.getRequestedValvePressure();
				macsDevice.setMacsDeviceState(mState);
				macsDevice.setFlowPressure(flowPressure);
				macsDevice.setValvePressure(valvePressure);
			}
		});
		
		view.setCancelMacsAction(new AbstractAction() {
			private static final long serialVersionUID = 2448129146982643913L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				view.setCurrentMacsState(macsDevice.getMacsDeviceState());
				view.setCurrentFlowPressure(macsDevice.getFlowPressure());
				view.setCurrentValvePressure(macsDevice.getValvePressure());
			}
		});
		
		view.setApplyCoreMacsStateAction(new AbstractAction() {
			private static final long serialVersionUID = -5905369531767107401L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				MacsDeviceState mState = view.getRequestedMacsState();
				macsDevice.setMacsDeviceState(mState);
			}
		});
		
		view.setApplyFlowPressureAction(new AbstractAction() {
			private static final long serialVersionUID = 845072700465474745L;

			@Override
			public void actionPerformed(ActionEvent e) {
				float flowPressure = view.getRequestedFlowPressure();
				macsDevice.setFlowPressure(flowPressure);
			}
		});
		
		view.setApplyValvePressureAction(new AbstractAction() {
			private static final long serialVersionUID = 8174170799600819623L;

			@Override
			public void actionPerformed(ActionEvent e) {
				float valvePressure = view.getRequestedValvePressure();
				macsDevice.setValvePressure(valvePressure);
			}
		});
		
		MacsListener mListener = new MacsListener();
		macsDevice.addCoreMacsStateListener(mListener);
		macsDevice.addFlowPressureListener(mListener);
		macsDevice.addValvePressureListener(mListener);
		
	}

	
	class MacsListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			
			if (evt.getPropertyName().equals(CoreMacsDevice.MACS_STATE)) {
				view.setCurrentMacsState((MacsDeviceState) evt.getNewValue());
			}
			else if (evt.getPropertyName().equals(CoreMacsDevice.FLOW_PRESSURE)) {
				view.setCurrentFlowPressure((Float) evt.getNewValue());
			}
			else if (evt.getPropertyName().equals(CoreMacsDevice.VALVE_PRESSURE)) {
				view.setCurrentValvePressure((Float) evt.getNewValue());
			}
		}
	}	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectMacsControlView macsView = new BasicDirectMacsControlView();
					JPanel mPanel = macsView.getMainComponent();
					BasicMacsCleanComboDevice mDevice = new BasicMacsCleanComboDevice();
					
					new DirectMacsControlController(macsView, mDevice);
					
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

}

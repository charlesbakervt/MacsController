package edu.paulssonlab.macsplugin.directcontrol.cleaner;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;



public class DirectCleaningControlController {
	private DirectCleaningControlView view;
	
	
	public DirectCleaningControlController(final DirectCleaningControlView view, final CoreCleaningDevice cleaningDevice) {
		this.view = view;
	
		view.setApplyCleanAction(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2359053348811247630L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				
				float fillPressure = view.getRequestedFillPressure();
				cleaningDevice.setFillPressure(fillPressure);
				
				CleaningDeviceState cState = view.getRequestedCleaningState();
				cleaningDevice.setCleaningDeviceState(cState);
			}
		});
		
		view.setCancelCleanAction(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3150230494606050590L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				view.setCurrentFillPressure(cleaningDevice.getFillPressure());
				view.setCurrentCleaningState(cleaningDevice.getCleaningDeviceState());
			}
		});
		
		view.setApplyCleaningStateAction(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5789568739756353753L;

			@Override
			public void actionPerformed(ActionEvent evt) {
				CleaningDeviceState cState = view.getRequestedCleaningState();
				cleaningDevice.setCleaningDeviceState(cState);
			}
		});
		
		view.setApplyFillPressureListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				float fillPressure = view.getRequestedFillPressure();
				cleaningDevice.setFillPressure(fillPressure);
			}
		});
		
		CleaningListener cListener = new CleaningListener();
		
		cleaningDevice.addCleaningDeviceStateListener(cListener);
		cleaningDevice.addFillPressureListener(cListener);
	}
	
	class CleaningListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			
			if (evt.getPropertyName().equals(CoreCleaningDevice.CLEANING_DEVICE_STATE)) {
				view.setCurrentCleaningState((CleaningDeviceState) evt.getNewValue());
			}
			else if (evt.getPropertyName().equals(CoreCleaningDevice.FILL_PRESSURE)) {
				view.setCurrentFillPressure((Float) evt.getNewValue());
			}
		}
	}	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectCleaningControlView cleanView = new BasicDirectCleaningControlView();
					JPanel mPanel = cleanView.getMainComponent();
					BasicMacsCleanComboDevice mDevice = new BasicMacsCleanComboDevice();
					
					new DirectCleaningControlController(cleanView, mDevice);
					
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

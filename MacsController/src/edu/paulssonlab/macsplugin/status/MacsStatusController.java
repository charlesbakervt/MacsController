package edu.paulssonlab.macsplugin.status;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;

public class MacsStatusController {

	private MacsStatusView view;
	private Boolean isCleaning = false; // suppress lower level messages during cleaning protocol
	
	public MacsStatusController(final MacsStatusView macsStatusView, final PressureTubeLiquid<?> culture, final List<? extends CleaningLiquid<?,?,?>> cleaningLiquids) {
		this.view = macsStatusView;
		
		MacsSystem macsSystem = cleaningLiquids.get(0).getMacsSystem();
		CoreMacsDevice macsDevice = macsSystem.getMacsDevice();
		CoreCleaningDevice cleaningDevice = macsSystem.getCleaningDevice();
		
		cleaningDevice.addCleaningDeviceStateListener(new CleaningDeviceListener());
		cleaningDevice.addFillPressureListener(new CleaningDeviceListener());
		macsDevice.addCoreMacsStateListener(new MacsDeviceListener());
		macsDevice.addFlowPressureListener(new MacsDeviceListener());
		macsDevice.addValvePressureListener(new MacsDeviceListener());
		culture.addPressureTubeLiquidListener(new PressureTubeStatusListener("culture"));
		
		macsSystem.addMacsSystemStatusListener(new MacsSystemListener());
		
		for (int i = 0; i < cleaningLiquids.size(); i++) {
			CleaningLiquid<?,?,?> liquid = cleaningLiquids.get(i);
			liquid.addPressureTubeCleanerListener(new CleaningStatusListener(liquid.getCleaningFluid().toString()));
			liquid.addPressureTubeLiquidListener(new PressureTubeStatusListener(liquid.getCleaningFluid().toString()));
		}
		
	}

	class MacsDeviceListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if (evt.getPropertyName().equals(CoreMacsDevice.FLOW_PRESSURE)) {
				view.setCurrentFlowPressure((Float) evt.getNewValue()); 
			}
			else if (evt.getPropertyName().equals(CoreMacsDevice.VALVE_PRESSURE)) {
				view.setCurrentValvePressure((Float) evt.getNewValue());
			}
			else if (evt.getPropertyName().equals(CoreMacsDevice.MACS_STATE)) {
				view.setCurrentMacsState((MacsDeviceState) evt.getNewValue());
			}
		}
	}
	
	class CleaningDeviceListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if (evt.getPropertyName().equals(CoreCleaningDevice.CLEANING_DEVICE_STATE)) {
				view.setCurrentCleaningState(evt.getNewValue().toString()); 
			}
			else if (evt.getPropertyName().equals(CoreCleaningDevice.FILL_PRESSURE)) {
				view.setCurrentFillPressure((Float) evt.getNewValue());
			}
		}
	}

	class PressureTubeStatusListener implements PropertyChangeListener {
		
		private String pressureTubeFluid;
		
		public PressureTubeStatusListener(String pressureTubeFluid) {
			this.pressureTubeFluid = pressureTubeFluid;
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (!isCleaning) {
			if (evt.getPropertyName().equals(PressureTubeLiquid.PRESSURE_TUBE_LIQUID_STATUS)) {
				String ptStatus = (String) evt.getNewValue();
				if (ptStatus.contains("Empty")) {
					view.setCurrentMacsSystemStatus(ptStatus );
				}
				else {
					view.setCurrentMacsSystemStatus(pressureTubeFluid + ": " + ptStatus );
				}
			}
		}
		}
	}
	
	class CleaningStatusListener implements PropertyChangeListener {
		
		private String cleaningFluid;
		
		public CleaningStatusListener(String cleaningFluid) {
			this.cleaningFluid = cleaningFluid;
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(CleaningLiquid.CLEANING_STATUS)) {
				String cleanStatus = (String) evt.getNewValue();
				if (cleanStatus.equals("Begin Cleaning")) {
					isCleaning = true;
				}
				
				if (cleanStatus.equals("Finished Cleaning")) {
					isCleaning = false;
				}
				//unless this is a fill message during cleaning, display the message
				//i.e. suppress fill messages during cleaning
				if (cleanStatus.contains("Fill") && isCleaning) {
					
				}
				else {
					view.setCurrentMacsSystemStatus(cleaningFluid + ": " + cleanStatus);	
				}
				
				 
			}
		}
	}
	
	class MacsSystemListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(MacsSystem.MACS_SYSTEM_STATUS)) {
				String macsStatus = (String) evt.getNewValue();
				if (macsStatus.equals(MacsSystem.INTERRUPTED)) {
					isCleaning = false;
				}
				view.setCurrentMacsSystemStatus(macsStatus); 
			}
		}
	}

	
	
	
}

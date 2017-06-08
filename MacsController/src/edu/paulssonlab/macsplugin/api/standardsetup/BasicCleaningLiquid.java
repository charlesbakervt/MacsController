package edu.paulssonlab.macsplugin.api.standardsetup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.BasicCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.BasicFillPressureTubeSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.BasicInjectionSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardFillPressureTubeSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;

public class BasicCleaningLiquid extends BasicPTLiquid implements CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid> {
	
	private CoreCleaningDevice cleaningDevice;
	private BasicCleaningFluid cleaningFluid;
	private StandardCleaningSettings cleaningSettings;
	private StandardFillPressureTubeSettings fillPressureTubeSettings;
	private StandardCleaningLiquidSettings cleaningLiquidSettings;
	private List<PropertyChangeListener> statusListeners = new ArrayList<PropertyChangeListener>();
	private String status = "";

	public BasicCleaningLiquid(MacsSystem macsSystem, BasicCleaningFluid cleaningFluid) {
		super(macsSystem);
		
		this.cleaningDevice = macsSystem.getCleaningDevice();
		this.cleaningFluid = cleaningFluid;
		cleaningLiquidSettings = new BasicCleaningLiquidSettings(cleaningFluid);
		cleaningSettings = cleaningLiquidSettings.getStandardCleaningSettings();
		fillPressureTubeSettings = cleaningLiquidSettings.getStandardFillPTSettings();
		StandardPTSettings ptSettings = getPressureTubeLiquidSettings();
		ptSettings.setInjectionSettings(new BasicInjectionSettings(cleaningFluid));
		super.setPressureTubeLiquidSettings(ptSettings);
	}

	@Override
	public void addPressureTubeCleanerListener(PropertyChangeListener listener) {
		statusListeners.add(listener);
	}

	private void setPressureTubeCleanerStatus(String status) {
		notifyStatusListeners(
				this,
				CLEANING_STATUS,
				this.status,
				this.status = status);
	}
	
	private synchronized void setCleaningState(CleaningDeviceState mState) {
		setCoreMacsState(MacsDeviceState.ALLOW_FILLING);
		cleaningDevice.setCleaningDeviceState(mState);
	}
	
	private synchronized void setFillPressure(float pressurePSI) {
		cleaningDevice.setFillPressure(pressurePSI);		
	}

	@Override
	public void fillPressureTube() throws InterruptedException {
		setPressureTubeCleanerStatus("Filling Pressure Tube");
		setCleaningState(cleaningFluid.getInjectState());
		setFillPressure(fillPressureTubeSettings.getFillPressure());
		
		macsSleep(fillPressureTubeSettings.getMillisActivelyFlow());
		
		setCleaningState(cleaningFluid.getFlowState());
		macsSleep(fillPressureTubeSettings.getMillisPassivelyFlow());
		
		setCleaningState(CleaningDeviceState.ALL_OFF);
		setCoreMacsState(MacsDeviceState.RESTING);
	}

	@Override
	public void cleanPresureTube() throws InterruptedException {
		
		int millisCleanPT = cleaningSettings.getMillisCleanPT();
		int nFillPT = cleaningSettings.getnFillPT();
		for (int i = 0; i < nFillPT; i++) {
			setPressureTubeCleanerStatus(String.format("Cleaning Pressure Tube %d of %d", i+1, cleaningSettings.getnFillPT()));
			super.empty();
			fillPressureTube();
			macsSleep(millisCleanPT);
			setCoreMacsState(MacsDeviceState.ALL_OFF);
		}
		setPressureTubeCleanerStatus("Finished Cleaning Pressure Tube");
	}

	@Override
	public void cleanMacsChip() throws InterruptedException {
		setPressureTubeCleanerStatus("Cleaning Macs Chip");
		super.empty();
		fillPressureTube();
		super.inject();
		super.empty();
	}

	@Override
	public void cleanMacsSystem() throws InterruptedException {
		setPressureTubeCleanerStatus("Begin Cleaning");
		cleanPresureTube();
		cleanMacsChip();
		setPressureTubeCleanerStatus("Finished Cleaning");
	}
	
	@Override
	public BasicCleaningFluid getCleaningFluid() {
		return this.cleaningFluid;
	}
	
	public StandardFillPressureTubeSettings getFillPressureTubeSettings() {
		return fillPressureTubeSettings;
	}

	public void setFillPressureTubeSettings(BasicFillPressureTubeSettings fillPressureTubeSettings) {
		this.fillPressureTubeSettings = fillPressureTubeSettings;
	}

	public StandardCleaningSettings getLiquidCleaningSettings() {
		return cleaningSettings;
	}

	public void setLiquidCleaningSettings(StandardCleaningSettings liquidCleaningSettings) {
		this.cleaningSettings = liquidCleaningSettings;
	}
	
	private void notifyStatusListeners(Object object, String property, String oldState, String newState) {
        for (PropertyChangeListener name : statusListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
        }
	}

	private void macsSleep(int millisSleep) throws InterruptedException {
		try {
			
			Thread.sleep(millisSleep);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			// make sure the device goes into resting state upon interrupt
			// hopefully prevent flooding or other bad situation if left in previous state
			
			setCleaningState(CleaningDeviceState.ALL_OFF);
			super.setCoreMacsState(MacsDeviceState.RESTING);
			
			throw(e);
		}	
	}

	@Override
	public StandardCleaningLiquidSettings getCleaningLiquidSettings() {
		return cleaningLiquidSettings;
	}

	@Override
	public void setCleaningLiquidSettings(StandardCleaningLiquidSettings cleaningLiquidSettings) {
		this.cleaningLiquidSettings = cleaningLiquidSettings;
	}
	

}

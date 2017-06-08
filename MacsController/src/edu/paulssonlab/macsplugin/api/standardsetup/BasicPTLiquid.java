package edu.paulssonlab.macsplugin.api.standardsetup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.BasicPTSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardEmptyPressureTubeSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardInjectionSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardRunMacsSettings;

public class BasicPTLiquid implements PressureTubeLiquid<StandardPTSettings> {
	private CoreMacsDevice coreMacsDevice;
	private StandardPTSettings ptSettings = new BasicPTSettings();
	private StandardInjectionSettings injectionSettings;
	private StandardRunMacsSettings runMacsSettings;
	private StandardEmptyPressureTubeSettings emptyPressureTubeSettings;
	private List<PropertyChangeListener> statusListeners = new ArrayList<PropertyChangeListener>();
	private String status = "";
	private MacsSystem macsSystem;
	
	public BasicPTLiquid(MacsSystem macsSystem) {
		this.coreMacsDevice = macsSystem.getMacsDevice();
		initializeBasicConfiguration();
		this.macsSystem = macsSystem;
	}
	
	public void initializeBasicConfiguration() {
		ptSettings = new BasicPTSettings();
		injectionSettings = ptSettings.getInjectionSettings();
		runMacsSettings = ptSettings.getRunMacsSettings();
		emptyPressureTubeSettings = ptSettings.getEmptyPressureTubeSettings();
	}

	private synchronized void setFlowPressure(float pressurePSI) {
		coreMacsDevice.setFlowPressure(pressurePSI);		
	}

	private synchronized void setValvePressure(float pressurePSI) {
		coreMacsDevice.setValvePressure(pressurePSI);
	}
	
	protected synchronized void setCoreMacsState(MacsDeviceState macsState) {
		System.out.println(macsState.toString());
		coreMacsDevice.setMacsDeviceState(macsState);
	}

	@Override
	public void inject() throws InterruptedException {
		setPressureTubeLiquidStatus("Begin Injection");
		float initialFlowPressure = coreMacsDevice.getFlowPressure();
		coreMacsDevice.setFlowPressure(injectionSettings.getFlowToWastePressure());
		
		setPressureTubeLiquidStatus("Injecting: Fill Path");
		coreMacsDevice.setMacsDeviceState(MacsDeviceState.PT_TO_WASTE);
		macsSleep(injectionSettings.getMillisFlowToWaste());
		
		setCoreMacsState(MacsDeviceState.RESTING);
		setFlowPressure(injectionSettings.getFlowToChipPressure());
		
		setPressureTubeLiquidStatus("Injecting: Flow to Chip");
		setCoreMacsState(MacsDeviceState.OPEN_STATE);
		macsSleep(injectionSettings.getMillisInjectLiquid());
		
		setPressureTubeLiquidStatus("Finished Injecting");
		setCoreMacsState(MacsDeviceState.RESTING);
		setFlowPressure(initialFlowPressure);
		
	}

	@Override
	public void empty() throws InterruptedException {
		setPressureTubeLiquidStatus("Emptying Pressure Tube");
		setFlowPressure(emptyPressureTubeSettings.getFlowToWastePressure());
		setCoreMacsState(MacsDeviceState.PT_TO_WASTE);
		macsSleep(emptyPressureTubeSettings.getMillisEmpty());
		setCoreMacsState(MacsDeviceState.RESTING);
		setPressureTubeLiquidStatus("Finished Emptying");
	}

	@Override
	public void runMacs() throws InterruptedException {
		setPressureTubeLiquidStatus("Begin Macsing");
		
		setFlowPressure(runMacsSettings.getFlowPressure());
		setValvePressure(runMacsSettings.getValvePressure());
		
		setPressureTubeLiquidStatus("Macsing: Open State");
		setCoreMacsState(MacsDeviceState.OPEN_STATE);
		macsSleep(runMacsSettings.getMillisFlowCells());
		
		setPressureTubeLiquidStatus("Macsing: Half Open State");
		setCoreMacsState(MacsDeviceState.HALF_OPEN_STATE);
		macsSleep(runMacsSettings.getMillisAccumulateCells());
		
		setPressureTubeLiquidStatus("Macsing: Closed State");
		setCoreMacsState(MacsDeviceState.CLOSED_STATE);
		macsSleep(runMacsSettings.getMillisTrapCells());
		
		setPressureTubeLiquidStatus("Macsing: Prepare for Imaging");
		setValvePressure(runMacsSettings.getImagingPressure());
		macsSleep(runMacsSettings.getMillisAfterTrap());
		
		setPressureTubeLiquidStatus("Finished Macsing");
	}

	@Override
	public void addPressureTubeLiquidListener(PropertyChangeListener listener) {
		statusListeners.add(listener);
		
	}

//	@Override
	private void setPressureTubeLiquidStatus(String status) {
		notifyStatusListeners(
				this,
				PRESSURE_TUBE_LIQUID_STATUS,
				this.status,
				this.status = status);
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
			setCoreMacsState(MacsDeviceState.RESTING);
			
			throw(e);
		}	
	}

	@Override
	public MacsSystem getMacsSystem() {
		return macsSystem;
	}

	@Override
	public void setMacsSystem(MacsSystem macsSystem) {
		this.macsSystem = macsSystem;
	}

	@Override
	public StandardPTSettings getPressureTubeLiquidSettings() {
		return ptSettings;
	}

	@Override
	public void setPressureTubeLiquidSettings(StandardPTSettings settings) {
		ptSettings = settings;
		injectionSettings = ptSettings.getInjectionSettings();
		runMacsSettings = ptSettings.getRunMacsSettings();
		emptyPressureTubeSettings = ptSettings.getEmptyPressureTubeSettings();
	}


}

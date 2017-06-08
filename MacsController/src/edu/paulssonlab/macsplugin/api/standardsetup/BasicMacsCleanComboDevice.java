package edu.paulssonlab.macsplugin.api.standardsetup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import mmcorej.CMMCore;

public class BasicMacsCleanComboDevice implements CoreMacsDevice, CoreCleaningDevice {

	
	private String arduinoSwitch = "Arduino-Switch";
	private String arduinoFlowLayerDAC = "Arduino-DAC1";
	private String arduinoControlLayerDAC = "Arduino-DAC2";
	private String arduinoShutter = "Arduino-Shutter";

	private MacsDeviceState macsDeviceState;
	private CleaningDeviceState cleaningDeviceState;
	private float psiValveRegulator;
	private float psiFlowRegulator;
	private float psiFillRegulator;
	
	private float maxSystemPsi = 38.0f; 
	private float maxDacVolts = 5.0f;
	private float maxRegulatorPsi = 72.5f;
	private CMMCore mmc;
	private boolean debug = false;
	
	private List<PropertyChangeListener> flowPressureListeners = new ArrayList<PropertyChangeListener>();
	private List<PropertyChangeListener> valvePressureListeners = new ArrayList<PropertyChangeListener>();
	private List<PropertyChangeListener> fillPressureListeners = new ArrayList<PropertyChangeListener>();
	private List<PropertyChangeListener> coreStateListeners = new ArrayList<PropertyChangeListener>();
	private List<PropertyChangeListener> cleanStateListeners = new ArrayList<PropertyChangeListener>();
	
	private void setArduinoConfiguration() {
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		arduinoSwitch = configuration.getStringProperty("arduinoSwitch", arduinoSwitch);
		arduinoFlowLayerDAC = configuration.getStringProperty("arduinoFlowLayerDAC", arduinoFlowLayerDAC);
		arduinoControlLayerDAC = configuration.getStringProperty("arduinoControlLayerDAC", arduinoControlLayerDAC);
		arduinoShutter = configuration.getStringProperty("arduinoShutter", arduinoShutter);
		
		maxSystemPsi = configuration.getFloatProperty("maxSystemPsi", maxSystemPsi);
		maxDacVolts = configuration.getFloatProperty("maxDacVolts", maxDacVolts);
		maxRegulatorPsi = configuration.getFloatProperty("maxRegulatorPsi", maxRegulatorPsi);
		
		configuration.closePropertiesFile();
	}
	
	private void initializeDevice() {
		setFlowPressure(0.0f);
		setValvePressure(0.0f);
		setFillPressure(0.0f);
		setMacsDeviceState(MacsDeviceState.ALL_OFF);
		setCleaningDeviceState(CleaningDeviceState.ALL_OFF);
	}
	
	public BasicMacsCleanComboDevice() {
		this.debug = true;
		setArduinoConfiguration();
		initializeDevice();
	}
	
	public BasicMacsCleanComboDevice(CMMCore mmc) {
		this.debug = false;
		this.mmc = mmc;
		setArduinoConfiguration();
		initializeDevice();
		
		try {
			mmc.setProperty(arduinoShutter,"OnOff","1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addFlowPressureListener(PropertyChangeListener listener) {
		flowPressureListeners.add(listener);
	}

	@Override
	public void addValvePressureListener(PropertyChangeListener listener) {
		valvePressureListeners.add(listener);
	}
	
	@Override
	public void addFillPressureListener(PropertyChangeListener listener) {
		fillPressureListeners.add(listener);		
	}

	@Override
	public void addCoreMacsStateListener(PropertyChangeListener listener) {
		coreStateListeners.add(listener);
	}
	
	@Override
	public void addCleaningDeviceStateListener(PropertyChangeListener listener) {
		cleanStateListeners.add(listener);
	}

	@Override
	public void setFlowPressure(float psiPressure) {
		setFillFlowPressure(psiPressure);
	}
	
	@Override
	public void setFillPressure(float fillPressure) {
		setFillFlowPressure(fillPressure);
	}
	
	private void setFillFlowPressure(float psiPressure) {
		notifyFlowPressureListeners(
				this,
				FLOW_PRESSURE,
				this.psiFlowRegulator,
				this.psiFlowRegulator = psiPressure);
		notifyFillPressureListeners(
				this,
				FILL_PRESSURE,
				this.psiFillRegulator,
				this.psiFillRegulator = psiPressure);
		
		setPressure(arduinoFlowLayerDAC, psiPressure);
	}

	@Override
	public void setValvePressure(float psiPressure) {
		notifyValvePressureListeners(
				this,
				VALVE_PRESSURE,
				this.psiValveRegulator,
				this.psiValveRegulator = psiPressure);
		
		setPressure(arduinoControlLayerDAC, psiValveRegulator);
	}

	@Override
	public void setMacsDeviceState(MacsDeviceState coreState) {
		
		if (coreState != MacsDeviceState.ALLOW_FILLING) {
			setCleaningState(CleaningDeviceState.ALL_OFF);
		}
		
		notifyCoreStateListeners(
				this,
				MACS_STATE,
				this.macsDeviceState,
				this.macsDeviceState = coreState);
		
			setCoreState(arduinoSwitch, coreState);
	}
	
	@Override
	public void setCleaningDeviceState(CleaningDeviceState mCleaningDeviceState) {
		if (mCleaningDeviceState != CleaningDeviceState.ALL_OFF) {
			setMacsDeviceState(MacsDeviceState.ALLOW_FILLING);
		}
		setCleaningState(mCleaningDeviceState);
		
	}
	
	
	private void setCleaningState(CleaningDeviceState mCleaningDeviceState) {
		
		notifyCleanStateListeners(
				this,
				CLEANING_DEVICE_STATE,
				this.cleaningDeviceState,
				this.cleaningDeviceState = mCleaningDeviceState);
		
		setCleanState(arduinoSwitch, mCleaningDeviceState);
	}
	
	private void setCoreState(String arduinoSwitch, MacsDeviceState coreState) {
		System.out.println("standardArduino " + coreState.toString());
		if (!debug) {
			
			// since same arduino controls cleaning and macs there is no specific
			// clean macs state
			if (coreState != MacsDeviceState.ALLOW_FILLING) {
				try {
					mmc.setProperty(arduinoSwitch,"State",coreState.getStateNum());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	private void setCleanState(String arduinoSwitch, CleaningDeviceState cleanState) {
		System.out.println("standardArduino "+cleanState.toString());
		if (cleanState != CleaningDeviceState.ALL_OFF) {
			if (!debug) {

				// since the same arduino controls cleaning and macs there is no specific
				// clean macs state device

				try {
					mmc.setProperty(arduinoSwitch,"State",cleanState.getStateNum());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	private void setPressure(String arduinoDAC, float psiRegulator) {
		//assumes a maxDacVolts volt dac and a max pressure of maxRegulatorPsi
		float pressureVolts = psiRegulator * (maxDacVolts/maxRegulatorPsi);
		
		
		//TODO handle over pressure
		if (psiRegulator > maxSystemPsi) {};
		
		if (!debug) {
			try {
				mmc.setProperty(arduinoDAC,"Volts",String.valueOf(pressureVolts));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public float getFlowPressure() {
		return this.psiFlowRegulator;
	}
	
	@Override
	public float getFillPressure() {
		return this.psiFillRegulator;
	}

	@Override
	public float getValvePressure() {
		return this.psiValveRegulator;
	}

	@Override
	public MacsDeviceState getMacsDeviceState() {
		return this.macsDeviceState;
	}
	
	
	@Override
	public CleaningDeviceState getCleaningDeviceState() {
		return this.cleaningDeviceState;
	}
	
	private void notifyValvePressureListeners(Object object, String property, Float oldPressure, Float newPressure) {
        for (PropertyChangeListener name : valvePressureListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldPressure, newPressure));
        }
	}
	
	private void notifyFlowPressureListeners(Object object, String property, Float oldPressure, Float newPressure) {
        for (PropertyChangeListener name : flowPressureListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldPressure, newPressure));
        }
	}
	
	private void notifyFillPressureListeners(Object object, String property, Float oldPressure, Float newPressure) {
        for (PropertyChangeListener name : fillPressureListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldPressure, newPressure));
        }
	}
        
     private void notifyCoreStateListeners(Object object, String property, MacsDeviceState oldState, MacsDeviceState newState) {
            for (PropertyChangeListener name : coreStateListeners) {
                    name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
            }
     }
     
     private void notifyCleanStateListeners(Object object, String property, CleaningDeviceState oldState, CleaningDeviceState newState) {
         for (PropertyChangeListener name : cleanStateListeners) {
                 name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
         }
  }

	@Override
	public void putInRestingState() {
		setCleaningDeviceState(CleaningDeviceState.ALL_OFF);
	}


}

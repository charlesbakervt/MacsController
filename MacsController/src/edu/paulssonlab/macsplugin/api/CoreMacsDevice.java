package edu.paulssonlab.macsplugin.api;

import java.beans.PropertyChangeListener;

public interface CoreMacsDevice {
	
	public static final String FLOW_PRESSURE = "FlowPressure";
	public static final String VALVE_PRESSURE = "ValvePressure";
	public static final String MACS_STATE = "MacsState";
	
	void addFlowPressureListener(PropertyChangeListener listener);
	void addValvePressureListener(PropertyChangeListener listener);
	void addCoreMacsStateListener(PropertyChangeListener listener);
	
	void setFlowPressure(float psiPressure);
	void setValvePressure(float psiPressure);
	void setMacsDeviceState(MacsDeviceState coreState);
	
	float getFlowPressure();
	float getValvePressure();
	MacsDeviceState getMacsDeviceState();

}

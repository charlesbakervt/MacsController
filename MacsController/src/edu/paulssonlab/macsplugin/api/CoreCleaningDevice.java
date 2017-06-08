package edu.paulssonlab.macsplugin.api;

import java.beans.PropertyChangeListener;

public interface CoreCleaningDevice {
	
	public static final String FILL_PRESSURE = "FillPressure";
	public static final String CLEANING_DEVICE_STATE = "CleaningDeviceState";
	void addFillPressureListener(PropertyChangeListener listener);
	void addCleaningDeviceStateListener(PropertyChangeListener listener);
	void putInRestingState();
	void setCleaningDeviceState(CleaningDeviceState cleaningDevice);
	void setFillPressure(float fillPressure);
	
	float getFillPressure();
	CleaningDeviceState getCleaningDeviceState();
	
}

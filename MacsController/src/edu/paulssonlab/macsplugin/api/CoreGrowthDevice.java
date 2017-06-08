package edu.paulssonlab.macsplugin.api;

import java.beans.PropertyChangeListener;

public interface CoreGrowthDevice {
	public static final String GROWTH_PRESSURE = "GrowthPressure";
	public static final String GROWTH_STATE = "GrowthState";
	
	void addGrowthPressureListener(PropertyChangeListener listener);
	void addCoreGrowthStateListener(PropertyChangeListener listener);
	
	void setGrowthPressure(float psiPressure);
	void setGrowthDeviceState(MacsDeviceState coreState);
	
	float getGrowthPressure();
	GrowthDeviceState getGrowthDeviceState();
}

package edu.paulssonlab.macsplugin.api;

import java.beans.PropertyChangeListener;

public interface PressureTubeLiquid<T1> {
	public static final String PRESSURE_TUBE_LIQUID_STATUS = "PressureTubeLiquidStatus";
	void addPressureTubeLiquidListener(PropertyChangeListener listener);
	
	public void inject() throws InterruptedException;
	public void runMacs() throws InterruptedException;
	public void empty() throws InterruptedException;
	public MacsSystem getMacsSystem();
	public void setMacsSystem(MacsSystem macsSystem);
	
	public T1 getPressureTubeLiquidSettings();
	public void setPressureTubeLiquidSettings(T1 settings);
	
	
}

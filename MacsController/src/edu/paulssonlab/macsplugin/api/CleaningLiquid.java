package edu.paulssonlab.macsplugin.api;

import java.beans.PropertyChangeListener;

public interface CleaningLiquid<T1, T2, T3 extends Enum<T3>> extends PressureTubeLiquid<T1> {
	public static final String CLEANING_STATUS = "CleaningStatus";
	void addPressureTubeCleanerListener(PropertyChangeListener listener);

	public T3 getCleaningFluid();
	void fillPressureTube() throws InterruptedException;
	void cleanPresureTube() throws InterruptedException;
	void cleanMacsChip() throws InterruptedException;
	void cleanMacsSystem() throws InterruptedException;
	
	public T2 getCleaningLiquidSettings();
	public void setCleaningLiquidSettings(T2 cleaningLiquidSettings);
}
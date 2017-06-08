package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class BasicRunMacsSettings implements StandardRunMacsSettings {

	private float flowPressure;
	private float valvePressure;
	private float imagingPressure;	
	private int millisFlowCells;
	private int millisAccumulateCells;
	private int millisTrapCells;
	private int millisAfterTrap;


	private void setDefaultMacsValues() {
		flowPressure = 20;
		valvePressure = 20;
		imagingPressure = 20;	
		millisFlowCells = 1000;
		millisAccumulateCells = 1000;
		millisTrapCells = 1000;
		millisAfterTrap = 3000;

		
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		millisFlowCells = configuration.getIntegerProperty("dfltMillisFlowCells", millisFlowCells);
		millisAccumulateCells = configuration.getIntegerProperty("dfltMillisAccumulateCells", millisAccumulateCells);
		millisTrapCells = configuration.getIntegerProperty("dfltMillisTrapCells", millisTrapCells);
		millisAfterTrap = configuration.getIntegerProperty("dfltMillisAfterTrap", millisAfterTrap);
		flowPressure = configuration.getFloatProperty("dfltPsiFlowLayerPressure", flowPressure);
		valvePressure = configuration.getFloatProperty("dfltPsiControlLayerPressure", valvePressure);
		imagingPressure = configuration.getFloatProperty("dfltPsiImagingPressure", imagingPressure);

		configuration.closePropertiesFile();	
	}
	
	public BasicRunMacsSettings() {
		setDefaultMacsValues();
	}
	
	public BasicRunMacsSettings(int millisFlowCells, int millisAccumulateCells, int millisTrapCells) {
		this();
		this.millisFlowCells = millisFlowCells;
		this.millisAccumulateCells = millisAccumulateCells;
		this.millisTrapCells = millisTrapCells;
	}
		
	public BasicRunMacsSettings(int millisFlowCells, int millisAccumulateCells, int millisTrapCells, int millisAfterTrap,
			float flowPressure, float valvePressure, float imagingPressure) {
		
		this(millisFlowCells,millisAccumulateCells,millisTrapCells);
		this.flowPressure = flowPressure;
		this.valvePressure = valvePressure;
		this.imagingPressure = imagingPressure;
		this.millisAfterTrap =  millisAfterTrap;
	}

	public float getFlowPressure() {
		return flowPressure;
	}

	public void setFlowPressure(float flowPressure) {
		this.flowPressure = flowPressure;
	}

	public float getValvePressure() {
		return valvePressure;
	}

	public void setValvePressure(float valvePressure) {
		this.valvePressure = valvePressure;
	}

	public float getImagingPressure() {
		return imagingPressure;
	}

	public void setImagingPressure(float imagingPressure) {
		this.imagingPressure = imagingPressure;
	}

	public int getMillisFlowCells() {
		return millisFlowCells;
	}

	public void setMillisFlowCells(int millisFlowCells) {
		this.millisFlowCells = millisFlowCells;
	}

	public int getMillisAccumulateCells() {
		return millisAccumulateCells;
	}

	public void setMillisAccumulateCells(int millisAccumulateCells) {
		this.millisAccumulateCells = millisAccumulateCells;
	}

	public int getMillisTrapCells() {
		return millisTrapCells;
	}

	public void setMillisTrapCells(int millisTrapCells) {
		this.millisTrapCells = millisTrapCells;
	}

	public int getMillisAfterTrap() {
		return millisAfterTrap;
	}

	public void setMillisAfterTrap(int millisAfterTrap) {
		this.millisAfterTrap = millisAfterTrap;
	}
}

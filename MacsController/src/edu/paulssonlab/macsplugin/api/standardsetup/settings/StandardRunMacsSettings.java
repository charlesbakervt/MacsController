package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardRunMacsSettings {

	float getFlowPressure();

	void setFlowPressure(float flowPressure);

	float getValvePressure();

	void setValvePressure(float valvePressure);

	float getImagingPressure();

	void setImagingPressure(float imagingPressure);

	int getMillisFlowCells();

	void setMillisFlowCells(int millisFlowCells);

	int getMillisAccumulateCells();

	void setMillisAccumulateCells(int millisAccumulateCells);

	int getMillisTrapCells();

	void setMillisTrapCells(int millisTrapCells);

	int getMillisAfterTrap();

	void setMillisAfterTrap(int millisAfterTrap);

}
package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardInjectionSettings {

	float getFlowToWastePressure();
	float getFlowToChipPressure();
	int getMillisFlowToWaste();
	int getMillisInjectLiquid();
	
	void setFlowToWastePressure(float flowToWastePressure);
	void setFlowToChipPressure(float flowToChipPressure);
	void setMillisFlowToWaste(int millisFlowToWaste);
	void setMillisInjectLiquid(int millisInjectLiquid);
	
	
	

}
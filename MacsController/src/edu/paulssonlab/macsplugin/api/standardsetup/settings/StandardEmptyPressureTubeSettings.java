package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardEmptyPressureTubeSettings {

	int getMillisEmpty();

	void setMillisEmpty(int millisEmpty);

	float getFlowToWastePressure();

	void setFlowToWastePressure(float psiEmptyPT);

}
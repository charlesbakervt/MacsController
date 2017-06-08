package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardFillPressureTubeSettings {

	int getMillisActivelyFlow();

	void setMillisActivelyFlow(int millisActivelyFlow);

	int getMillisPassivelyFlow();

	void setMillisPassivelyFlow(int millisPassivelyFlow);

	float getFillPressure();

	void setFillPressure(float psiFillPressure);

}
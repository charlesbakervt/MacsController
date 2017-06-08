package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardCleaningLiquidSettings {
	
	public StandardFillPressureTubeSettings getStandardFillPTSettings();
	public StandardCleaningSettings getStandardCleaningSettings();
	
	public void setStandardFillPTSettings(StandardFillPressureTubeSettings fillPressureTubeSettings);
	public void setStandardCleaningSettings(StandardCleaningSettings cleaningSettings);
	

}

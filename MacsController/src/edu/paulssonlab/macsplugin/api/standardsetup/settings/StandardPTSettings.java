package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public interface StandardPTSettings {

	public StandardRunMacsSettings getRunMacsSettings();
	public StandardInjectionSettings getInjectionSettings();
	public StandardEmptyPressureTubeSettings getEmptyPressureTubeSettings();
	
	public void setRunMacsSettings(StandardRunMacsSettings runMacsSettings);
	public void setInjectionSettings(StandardInjectionSettings injectionSettings);
	public void setEmptyPressureTubeSettings(StandardEmptyPressureTubeSettings emptyPressureTubeSettings );
}

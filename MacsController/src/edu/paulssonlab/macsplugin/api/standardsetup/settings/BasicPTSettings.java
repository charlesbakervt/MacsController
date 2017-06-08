package edu.paulssonlab.macsplugin.api.standardsetup.settings;

public class BasicPTSettings implements StandardPTSettings {

	private StandardInjectionSettings injectionSettings = new BasicInjectionSettings();
	private StandardRunMacsSettings runMacsSettings = new BasicRunMacsSettings();
	private StandardEmptyPressureTubeSettings emptyPressureTubeSettings = new BasicEmptyPressureTubeSettings();
	
	public BasicPTSettings() {
	}

	@Override
	public StandardRunMacsSettings getRunMacsSettings() {
		return runMacsSettings;
	}

	@Override
	public StandardInjectionSettings getInjectionSettings() {
		return injectionSettings;
	}

	@Override
	public StandardEmptyPressureTubeSettings getEmptyPressureTubeSettings() {
		return emptyPressureTubeSettings;
	}

	@Override
	public void setRunMacsSettings(StandardRunMacsSettings runMacsSettings) {
		this.runMacsSettings = runMacsSettings;
		
	}

	@Override
	public void setInjectionSettings(StandardInjectionSettings injectionSettings) {
		this.injectionSettings = injectionSettings;
	}

	@Override
	public void setEmptyPressureTubeSettings(StandardEmptyPressureTubeSettings emptyPressureTubeSettings) {
		this.emptyPressureTubeSettings = emptyPressureTubeSettings;
	}

	
}

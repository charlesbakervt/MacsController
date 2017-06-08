package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class BasicEmptyPressureTubeSettings implements StandardEmptyPressureTubeSettings {

	private int millisEmpty = 12000;
	private float psiEmptyPT = 20.0f;
	
	private void setDefaultEmptyValues() {
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		millisEmpty = configuration.getIntegerProperty("dfltMillisEmptyPT", millisEmpty);
		psiEmptyPT = configuration.getFloatProperty("dfltPsiEmptyPT", psiEmptyPT);
			
		configuration.closePropertiesFile();
	}
	
	
	public BasicEmptyPressureTubeSettings() {
		setDefaultEmptyValues();
	}
	
	public BasicEmptyPressureTubeSettings(int millisEmpty) {
		setDefaultEmptyValues();
		this.millisEmpty = millisEmpty;
	}
	
	public BasicEmptyPressureTubeSettings(int millisEmpty, float psiEmptyPT) {
		setDefaultEmptyValues();
		this.millisEmpty = millisEmpty;
		this.psiEmptyPT = psiEmptyPT;
	}
	

	
	public int getMillisEmpty() {
		return millisEmpty;
	}

	public void setMillisEmpty(int millisEmpty) {
		System.out.println("bing");
		this.millisEmpty = millisEmpty;
	}
	
	
	public float getFlowToWastePressure() {
		return psiEmptyPT;
	}


	public void setFlowToWastePressure(float psiEmptyPT) {
		this.psiEmptyPT = psiEmptyPT;
	}



	
	
}

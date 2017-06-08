package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class BasicCleaningSettings implements StandardCleaningSettings {

	private int millisCleanPT = 5000;
	private int nFillPT = 2;
	
	public BasicCleaningSettings(BasicCleaningFluid fluid) {
		setDefaultAdvancedSettingsValues(fluid);		
	}

	
	private void setDefaultAdvancedSettingsValues(BasicCleaningFluid liquid) {
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		String prefix = liquid.toString() + ".";
		millisCleanPT = configuration.getIntegerProperty(prefix + "millisCleanPT", millisCleanPT);
		nFillPT = configuration.getIntegerProperty(prefix + "nFillPT", nFillPT);
	
		configuration.closePropertiesFile();
	}


	public int getMillisCleanPT() {
		return millisCleanPT;
	}


	public void setMillisCleanPT(int millisCleanPT) {
		this.millisCleanPT = millisCleanPT;
	}


	public int getnFillPT() {
		return nFillPT;
	}


	public void setnFillPT(int nFillPT) {
		this.nFillPT = nFillPT;
	}
	
	
}

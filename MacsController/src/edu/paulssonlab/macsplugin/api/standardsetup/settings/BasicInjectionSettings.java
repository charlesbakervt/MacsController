package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class BasicInjectionSettings implements StandardInjectionSettings {

	private int millisFlowToWaste = 400;
	private int millisFlowToChip = 30000;
	private float psiFlowToWaste = 5;
	private float psiFlowToChip = 20;
	
	private void setDefaultInjectionValues(String prefixForDefaults) {
		MacsConfigurationProperties mConfiguration = new MacsConfigurationProperties();
		mConfiguration.openPropertiesFile();
		
		String prefix = prefixForDefaults + ".injectionSettings.";
		
		millisFlowToWaste = mConfiguration.getIntegerProperty(prefix + "millisFlowToWaste", millisFlowToWaste);
		millisFlowToChip = mConfiguration.getIntegerProperty(prefix + "millisFlowToChip", millisFlowToChip);
		psiFlowToWaste = mConfiguration.getFloatProperty(prefix + "psiFlowToWaste", psiFlowToWaste);
		psiFlowToChip = mConfiguration.getFloatProperty(prefix + "psiFlowToChip", psiFlowToChip);
		
		mConfiguration.closePropertiesFile();
	}
	
	public BasicInjectionSettings() {
		String prefixForDefaults = "culture";
		setDefaultInjectionValues(prefixForDefaults);
	}
	
	public BasicInjectionSettings(BasicCleaningFluid liquid) {
		String prefixForDefaults = liquid.toString();
		setDefaultInjectionValues(prefixForDefaults);
	}
	
	public float getFlowToWastePressure() {
		return psiFlowToWaste;
	}

	public void setFlowToWastePressure(float flowToWastePressure) {
		this.psiFlowToWaste = flowToWastePressure;
	}

	public float getFlowToChipPressure() {
		return psiFlowToChip;
	}

	public void setFlowToChipPressure(float flowToChipPressure) {
		this.psiFlowToChip = flowToChipPressure;
	}

	public int getMillisFlowToWaste() {
		return millisFlowToWaste;
	}

	public void setMillisFlowToWaste(int millisFlowToWaste) {
		this.millisFlowToWaste = millisFlowToWaste;
	}

	public int getMillisInjectLiquid() {
		return millisFlowToChip;
	}

	public void setMillisInjectLiquid(int millisInjectLiquid) {
		this.millisFlowToChip = millisInjectLiquid;
	}
	

}

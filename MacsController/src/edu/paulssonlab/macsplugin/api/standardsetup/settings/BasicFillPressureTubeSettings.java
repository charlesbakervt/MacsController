package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class BasicFillPressureTubeSettings implements StandardFillPressureTubeSettings {

	private int millisActivelyFlow = 2000;
	private int millisPassivelyFlow = 4000;
	private float psiFillPressure = 20.0f;
	
	private void setDefaultFillValues(BasicCleaningFluid liquid) {
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		String activeFlow = liquid.toString() + ".dfltMillisActivelyFlow";
		String psiFill = liquid.toString() + ".dfltPsiFill";
		
		millisActivelyFlow = configuration.getIntegerProperty(activeFlow, millisActivelyFlow);
		millisPassivelyFlow = configuration.getIntegerProperty("dfltMillisPassivelyFlow", millisPassivelyFlow);
		psiFillPressure = configuration.getFloatProperty(psiFill, psiFillPressure);
		
		configuration.closePropertiesFile();
	}
	
	public BasicFillPressureTubeSettings(BasicCleaningFluid liquid) {
		setDefaultFillValues(liquid);
	}
	
	public BasicFillPressureTubeSettings(BasicCleaningFluid liquid, int millisActivelyFlow, int millisPassivelyFlow) {
		setDefaultFillValues(liquid);
		this.millisActivelyFlow = millisActivelyFlow;
		this.millisPassivelyFlow = millisPassivelyFlow;
	}
	
	public BasicFillPressureTubeSettings(BasicCleaningFluid liquid, int millisActivelyFlow, int millisPassivelyFlow, float psiFillPressure) {
		setDefaultFillValues(liquid);
		this.millisActivelyFlow = millisActivelyFlow;
		this.millisPassivelyFlow = millisPassivelyFlow;
		this.psiFillPressure = psiFillPressure;
	}
	
	
	public int getMillisActivelyFlow() {
		return millisActivelyFlow;
	}

	public void setMillisActivelyFlow(int millisActivelyFlow) {
		this.millisActivelyFlow = millisActivelyFlow;
	}

	public int getMillisPassivelyFlow() {
		return millisPassivelyFlow;
	}

	public void setMillisPassivelyFlow(int millisPassivelyFlow) {
		this.millisPassivelyFlow = millisPassivelyFlow;
	}

	public float getFillPressure() {
		return psiFillPressure;
	}

	public void setFillPressure(float psiFillPressure) {
		this.psiFillPressure = psiFillPressure;
	}


}

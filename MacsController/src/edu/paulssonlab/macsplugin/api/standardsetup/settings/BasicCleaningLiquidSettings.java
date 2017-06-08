package edu.paulssonlab.macsplugin.api.standardsetup.settings;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;

public class BasicCleaningLiquidSettings implements StandardCleaningLiquidSettings {

	private StandardFillPressureTubeSettings fillPressureTubeSettings;
	private StandardCleaningSettings cleaningSettings;
	
	public BasicCleaningLiquidSettings(BasicCleaningFluid mCleaningFluid) {
		fillPressureTubeSettings = new BasicFillPressureTubeSettings(mCleaningFluid);
		cleaningSettings = new BasicCleaningSettings(mCleaningFluid);
	}

	@Override
	public StandardCleaningSettings getStandardCleaningSettings() {
		return cleaningSettings;
	}


	@Override
	public void setStandardCleaningSettings(StandardCleaningSettings cleaningSettings) {
		this.cleaningSettings = cleaningSettings;
	}

	@Override
	public StandardFillPressureTubeSettings getStandardFillPTSettings() {
		return fillPressureTubeSettings;
	}

	@Override
	public void setStandardFillPTSettings(StandardFillPressureTubeSettings fillPressureTubeSettings) {
		this.fillPressureTubeSettings = fillPressureTubeSettings;
	}

}

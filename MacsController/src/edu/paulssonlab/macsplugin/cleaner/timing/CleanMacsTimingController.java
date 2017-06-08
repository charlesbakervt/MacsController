package edu.paulssonlab.macsplugin.cleaner.timing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardEmptyPressureTubeSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardFillPressureTubeSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewCommunicator;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewStatus;

public class CleanMacsTimingController {
	private CleanMacsTimingView view;
	private BasicCleaningFluid fluid;
	private StandardFillPressureTubeSettings fillPressureTubeSettings;
	private StandardEmptyPressureTubeSettings emptyPressureTubeSettings;
	
	public CleanMacsTimingController(CleanMacsTimingView view, 
			CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid> cleaningLiquid,
			CleanMacsViewCommunicator comm) {
	
		this.view = view;
		this.fluid = cleaningLiquid.getCleaningFluid();
		StandardPTSettings ptSettings = cleaningLiquid.getPressureTubeLiquidSettings();
		StandardCleaningLiquidSettings cleanSettings = cleaningLiquid.getCleaningLiquidSettings();
		this.emptyPressureTubeSettings = ptSettings.getEmptyPressureTubeSettings();
		this.fillPressureTubeSettings = cleanSettings.getStandardFillPTSettings();
		
		view.setMillisFillPT(fluid,fillPressureTubeSettings.getMillisActivelyFlow());
		view.setMillisPassivelyFlow(fillPressureTubeSettings.getMillisPassivelyFlow());
		view.setMillisEmptyPT(emptyPressureTubeSettings.getMillisEmpty());
		
		comm.addCleanMacsViewListener(new ModelListener());
	}
	
	class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if (evt.getNewValue().equals(CleanMacsViewStatus.INITIATE_EMPTYING)) {
				emptyPressureTubeSettings.setMillisEmpty(view.getMillisEmptyPT());
				
			}
			
			if (evt.getNewValue().equals(CleanMacsViewStatus.INITIATE_FILLING)) {
				fillPressureTubeSettings.setMillisActivelyFlow(view.getMillisFillPT(fluid));
				fillPressureTubeSettings.setMillisPassivelyFlow(view.getMillisPassivelyFlow());
			}
			
			if (evt.getNewValue().equals(CleanMacsViewStatus.INITIATE_CLEANING)) {
				emptyPressureTubeSettings.setMillisEmpty(view.getMillisEmptyPT());
				fillPressureTubeSettings.setMillisActivelyFlow(view.getMillisFillPT(fluid));
				fillPressureTubeSettings.setMillisPassivelyFlow(view.getMillisPassivelyFlow());
			}
		}
	}
}

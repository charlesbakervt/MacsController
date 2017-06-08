package edu.paulssonlab.macsplugin.runmacs.timing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardInjectionSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardRunMacsSettings;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewCommunicator;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewStatus;

public class RunMacsTimingsController {

	private RunMacsTimingsView view;
	PressureTubeLiquid<StandardPTSettings> PTLiquid;
	private StandardRunMacsSettings runMacsSettings;
	private StandardInjectionSettings injectionSettings;
	
	public RunMacsTimingsController(RunMacsTimingsView view, 
			PressureTubeLiquid<StandardPTSettings> PTLiquid, RunMacsViewCommunicator comm) {
		this.view = view;
		this.PTLiquid = PTLiquid;

		StandardPTSettings ptSettings = PTLiquid.getPressureTubeLiquidSettings();
		runMacsSettings = ptSettings.getRunMacsSettings();
		injectionSettings = ptSettings.getInjectionSettings();
		
		view.setMillisFlowCells(runMacsSettings.getMillisFlowCells());
		view.setMillisAccumulateCells(runMacsSettings.getMillisAccumulateCells());
		view.setMillisTrapCells(runMacsSettings.getMillisTrapCells());
		view.setMillisInjectCells(injectionSettings.getMillisInjectLiquid());
		
		comm.addRunMacsViewListener(new ModelListener());
		
	}
	
	class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if (evt.getNewValue().equals(RunMacsViewStatus.INITIATE_MACSING)) {
				runMacsSettings.setMillisFlowCells(view.getMillisFlowCells());
				runMacsSettings.setMillisAccumulateCells(view.getMillisAccumulateCells());
				runMacsSettings.setMillisTrapCells(view.getMillisTrapCells());
			}
			
			if (evt.getNewValue().equals(RunMacsViewStatus.INITIATE_INJECTION)) {
				injectionSettings.setMillisInjectLiquid(view.getMillisInjectCells());
			}
			
		}
	}



}

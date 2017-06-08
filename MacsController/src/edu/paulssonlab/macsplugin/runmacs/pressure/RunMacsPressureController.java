package edu.paulssonlab.macsplugin.runmacs.pressure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardInjectionSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardRunMacsSettings;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewCommunicator;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewStatus;


public class RunMacsPressureController {

	private RunMacsPressureView view;
	PressureTubeLiquid<StandardPTSettings> ptLiquid;
	private StandardRunMacsSettings runMacsSettings;
	private StandardInjectionSettings injectionSettings;
	
	public RunMacsPressureController(RunMacsPressureView view, 
			PressureTubeLiquid<StandardPTSettings> PTLiquid, RunMacsViewCommunicator comm) {
		this.view = view;
		this.ptLiquid = PTLiquid;
		
		StandardPTSettings ptSettings = PTLiquid.getPressureTubeLiquidSettings();
		runMacsSettings = ptSettings.getRunMacsSettings();
		injectionSettings = ptSettings.getInjectionSettings();
		

		view.setFlowPressure(runMacsSettings.getFlowPressure());
		view.setValvePressure(runMacsSettings.getValvePressure());
		view.setImagingPressure(runMacsSettings.getImagingPressure());
		
		comm.addRunMacsViewListener(new ModelListener());
		
	}
	
	class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if (evt.getNewValue().equals(RunMacsViewStatus.INITIATE_MACSING)) {
				runMacsSettings.setFlowPressure(view.getFlowPressure());
				runMacsSettings.setValvePressure(view.getValvePressure());
				runMacsSettings.setImagingPressure(view.getImagingPressure());
			}
			if (evt.getNewValue().equals(RunMacsViewStatus.INITIATE_INJECTION)) {
				injectionSettings.setFlowToChipPressure(view.getFlowPressure());
			}
		}
	}


}

package edu.paulssonlab.macsplugin.runmacs;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;
import edu.paulssonlab.macsplugin.runmacs.acquisition.AcquisitionView;
import edu.paulssonlab.macsplugin.runmacs.action.RunMacsActionView;
import edu.paulssonlab.macsplugin.runmacs.pressure.RunMacsPressureView;
import edu.paulssonlab.macsplugin.runmacs.timing.RunMacsTimingsView;

public interface RunMacsView extends HasComponent {
	
	RunMacsActionView getRunMacsActionView();
	RunMacsPressureView getRunMacsPressureView();
	RunMacsTimingsView getRunMacsTimingView();
	AcquisitionView getAcquisitionView();
	
	void attachMacsActionView(RunMacsActionView view);
	void attachMacsPressureView(RunMacsPressureView view);
	void attachMacsTimingView(RunMacsTimingsView view);
	void attachAcquisitionView(AcquisitionView view);

}

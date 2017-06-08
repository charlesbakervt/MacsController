package edu.paulssonlab.macsplugin.main;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsView;
import edu.paulssonlab.macsplugin.directcontrol.DirectControlView;
import edu.paulssonlab.macsplugin.halt.HaltMacsView;
import edu.paulssonlab.macsplugin.runmacs.RunMacsView;
import edu.paulssonlab.macsplugin.status.MacsStatusView;

public interface MainMacsView extends HasComponent {

	void attachRunMacsView(RunMacsView view);
	void attachCleanMacsView(CleanMacsView view);
	void attachDirectControlView(DirectControlView view);
	void attachMacsStatusView(MacsStatusView view);
	void attachHaltMacsView(HaltMacsView view);

	RunMacsView getRunMacsView();
	CleanMacsView getCleanMacsView();
	DirectControlView getDirectControlView();
	MacsStatusView getMacsStatusView();
	HaltMacsView getHaltMacsView();

}
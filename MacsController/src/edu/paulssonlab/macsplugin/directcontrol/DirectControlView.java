package edu.paulssonlab.macsplugin.directcontrol;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;
import edu.paulssonlab.macsplugin.directcontrol.cleaner.DirectCleaningControlView;
import edu.paulssonlab.macsplugin.directcontrol.macs.DirectMacsControlView;

public interface DirectControlView extends HasComponent {

	DirectCleaningControlView getDirectCleaningControlView();
	DirectMacsControlView getDirectMacsControlView();
	
	void attachDirectCleaningControlView(DirectCleaningControlView view);
	void attachDirectMacsControlView(DirectMacsControlView view);
	
	
}

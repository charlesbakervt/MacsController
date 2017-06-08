package edu.paulssonlab.macsplugin.halt;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface HaltMacsView extends HasComponent {
	
	void setStopAction(Action stopMacsAction);
	void setPressureOffAction(Action pressureOffAction);

}

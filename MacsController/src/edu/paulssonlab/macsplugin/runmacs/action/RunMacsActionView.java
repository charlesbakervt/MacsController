package edu.paulssonlab.macsplugin.runmacs.action;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface RunMacsActionView extends HasComponent{
	
//	public static final String RUN_MACS_ACTION_VIEW = "RunMacsActionView";
//	void addRunMacsActionViewListener(PropertyChangeListener listener);

	void setInjectCellsAction(Action injectCellsAction);
	void setRunMacsAction(Action runMacsAction);

}

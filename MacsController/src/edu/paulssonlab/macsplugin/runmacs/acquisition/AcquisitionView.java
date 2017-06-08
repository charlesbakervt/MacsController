package edu.paulssonlab.macsplugin.runmacs.acquisition;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface AcquisitionView extends HasComponent {
	String getAcquisitionFile();
	void setRunAcquisitionAction(Action runAcquisitionAction);
}

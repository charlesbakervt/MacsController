package edu.paulssonlab.macsplugin.cleaner.cleanaction;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface CleanActionView extends HasComponent {
	public void setCleanAction(BasicCleaningFluid fluid, Action cleanAction);
	public void setFullCleanAction(Action cleanAction);
}

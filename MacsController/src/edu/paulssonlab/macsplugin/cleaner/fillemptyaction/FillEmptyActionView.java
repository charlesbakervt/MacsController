package edu.paulssonlab.macsplugin.cleaner.fillemptyaction;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface FillEmptyActionView extends HasComponent {
	
	// don't need fluid for empty currently but added for future proofing
	public void setEmptyPTAction(BasicCleaningFluid fluid, Action emptyAction);
	public void setFillPTAction(BasicCleaningFluid fluid, Action fillAction);

}

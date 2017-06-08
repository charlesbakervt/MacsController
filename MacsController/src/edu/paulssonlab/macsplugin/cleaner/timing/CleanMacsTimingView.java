package edu.paulssonlab.macsplugin.cleaner.timing;

import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface CleanMacsTimingView extends HasComponent {

	public int getMillisFillPT(BasicCleaningFluid fluid);
	public int getMillisEmptyPT();
	public int getMillisPassivelyFlow();
	
	public void setMillisFillPT(BasicCleaningFluid fluid, int millis);
	public void setMillisEmptyPT(int millis);
	public void setMillisPassivelyFlow(int millis);
}

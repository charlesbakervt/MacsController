package edu.paulssonlab.macsplugin.runmacs.timing;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface RunMacsTimingsView extends HasComponent {
	public int getMillisFlowCells();
	public int getMillisAccumulateCells();
	public int getMillisTrapCells();
	public int getMillisInjectCells();
	
	public void setMillisFlowCells(int millis);
	public void setMillisAccumulateCells(int millis);
	public void setMillisTrapCells(int millis);
	public void setMillisInjectCells(int millis);
}

package edu.paulssonlab.macsplugin.runmacs.pressure;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface RunMacsPressureView extends HasComponent {
	
	public float getFlowPressure();
	public float getValvePressure();
	public float getImagingPressure();
	
	public void setFlowPressure(float pressure);
	public void setValvePressure(float pressure);
	public void setImagingPressure(float pressure);
	
}

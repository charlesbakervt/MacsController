package edu.paulssonlab.macsplugin.status;

import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface MacsStatusView extends HasComponent{
	void setCurrentFlowPressure(Float psiFlowPressure);
	void setCurrentValvePressure(Float psiValvePressure);
	void setCurrentFillPressure(Float psiFillPressure);
	void setCurrentMacsState(MacsDeviceState macsState);
	void setCurrentCleaningState(String cleaningState);
	void setCurrentMacsSystemStatus(String statusMessage);	
}
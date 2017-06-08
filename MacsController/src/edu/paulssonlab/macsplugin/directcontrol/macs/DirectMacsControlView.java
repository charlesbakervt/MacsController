package edu.paulssonlab.macsplugin.directcontrol.macs;

import javax.swing.Action;

import edu.paulssonlab.macsplugin.api.MacsDeviceState;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface DirectMacsControlView extends HasComponent {
			
	float getRequestedValvePressure();
	float getRequestedFlowPressure();
			
	MacsDeviceState getRequestedMacsState();
			
	void setCurrentFlowPressure(Float psiFlowPressure);
	void setCurrentValvePressure(Float psiValvePressure);
	void setCurrentMacsState(MacsDeviceState mMacsState);
		
	
			
	void setApplyFlowPressureAction(Action changeFlowPressureAction);
	void setApplyValvePressureAction(Action changeValvePressureAction);
	void setApplyCoreMacsStateAction(Action changeMacsStateAction);
	void setApplyMacsAction(Action action);
	void setCancelMacsAction(Action action);
}




package edu.paulssonlab.macsplugin.directcontrol.cleaner;

import javax.swing.Action;
import javax.swing.event.ChangeListener;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;
import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;

public interface DirectCleaningControlView extends HasComponent {
	
	
	float getRequestedFillPressure();
	
	CleaningDeviceState getRequestedCleaningState();
	
	void setCurrentFillPressure(Float psiFillPressure);
	void setCurrentCleaningState(CleaningDeviceState mDeviceState);
	
	
	void setApplyFillPressureListener(ChangeListener listener);
	void setApplyCleaningStateAction(Action action);
	void setApplyCleanAction(Action action);
	void setCancelCleanAction(Action action);
}

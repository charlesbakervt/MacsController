package edu.paulssonlab.macsplugin.runmacs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


public class RunMacsViewCommunicator {
	public static final String RUN_MACS_VIEW_STATUS = "RunMacsViewStatus";
	private RunMacsViewStatus mStatus = RunMacsViewStatus.READY;
	private List<PropertyChangeListener> statusListeners = new ArrayList<PropertyChangeListener>();
	
	public RunMacsViewCommunicator() {
	}
	
	public void addRunMacsViewListener(PropertyChangeListener listener) {
		statusListeners.add(listener);
	}

	public void setRunMacsViewStatus(RunMacsViewStatus status) {
		notifyStatusListeners(
				this,
				RUN_MACS_VIEW_STATUS,
				this.mStatus,
				this.mStatus = status);
	}
	
	private void notifyStatusListeners(Object object, String property, RunMacsViewStatus oldState, RunMacsViewStatus newState) {
        for (PropertyChangeListener name : statusListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
        }
	}
	

}

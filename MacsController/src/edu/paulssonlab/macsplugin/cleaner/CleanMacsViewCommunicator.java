package edu.paulssonlab.macsplugin.cleaner;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CleanMacsViewCommunicator {

	public static final String CLEAN_MACS_VIEW_STATUS = "CleanMacsViewStatus";
	private CleanMacsViewStatus mStatus = CleanMacsViewStatus.READY;
	private List<PropertyChangeListener> statusListeners = new ArrayList<PropertyChangeListener>();
	
	public CleanMacsViewCommunicator() {
	}
	

	public void addCleanMacsViewListener(PropertyChangeListener listener) {
		statusListeners.add(listener);
		
	}

	public void setCleanMacsViewStatus(CleanMacsViewStatus status) {
		notifyStatusListeners(
				this,
				CLEAN_MACS_VIEW_STATUS,
				this.mStatus,
				this.mStatus = status);
	}
	
	private void notifyStatusListeners(Object object, String property, CleanMacsViewStatus oldState, CleanMacsViewStatus newState) {
        for (PropertyChangeListener name : statusListeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
        }
	}

}

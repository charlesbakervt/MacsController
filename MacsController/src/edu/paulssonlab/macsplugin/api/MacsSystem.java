package edu.paulssonlab.macsplugin.api;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;

public class MacsSystem {
	
	
	public static final String MACS_SYSTEM_STATUS = "MacsSystemStatus";
	public static final String INTERRUPTED = "Interrupted/Ready";
	public static final String FINISHED = "Finished/Ready";
	public static final String READY = "Ready";
	
	private List<SwingWorker<Boolean, Void>> macsSystemWorkers = new ArrayList<SwingWorker<Boolean, Void>>();
	private List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();
	private String macsSystemState = "Ready";
	private boolean hasCleaningDevice = false;
	private CoreMacsDevice macsDevice;
	private CoreCleaningDevice cleaningDevice;
	
	public MacsSystem(CoreMacsDevice macsDevice) {
		this.hasCleaningDevice = false;
		this.setMacsDevice(macsDevice);
	}
	
	public MacsSystem(CoreMacsDevice macsDevice, CoreCleaningDevice cleaningDevice) {
		this.hasCleaningDevice = true;
		this.setMacsDevice(macsDevice);
		this.setCleaningDevice(cleaningDevice);
	}
	
	// currently have it designed so that only one pressuretube worker can exist and
	// execute, any additional calls will cancel the previous worker
	public void cancel() {
		if (!macsSystemWorkers.isEmpty()) {
			SwingWorker<Boolean, Void> runningWorker = macsSystemWorkers.remove(0);
			runningWorker.cancel(true);
		}
		macsDevice.setMacsDeviceState(MacsDeviceState.RESTING);
		cleaningDevice.putInRestingState();
		
	}
	
	protected void notifyInterrupted() {
		notifyStatusListeners(this, MACS_SYSTEM_STATUS, macsSystemState , macsSystemState = INTERRUPTED);
	}
	
	protected void notifyFinished() {
		notifyStatusListeners(this, MACS_SYSTEM_STATUS, macsSystemState , macsSystemState = FINISHED);
	}
	
	protected void executeWorker(SwingWorker<Boolean, Void> worker) {
		notifyStatusListeners(this, MACS_SYSTEM_STATUS, macsSystemState , macsSystemState = READY);
		cancel();
		macsSystemWorkers.add(worker);
		worker.execute();
	}

	public void addMacsSystemStatusListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	private void notifyStatusListeners(Object object, String property, String oldState, String newState) {
        for (PropertyChangeListener name : listeners) {
                name.propertyChange(new PropertyChangeEvent(this, property, oldState, newState));
        }
	}

	public CoreMacsDevice getMacsDevice() {
		return macsDevice;
	}

	public void setMacsDevice(CoreMacsDevice macsDevice) {
		this.macsDevice = macsDevice;
	}

	public CoreCleaningDevice getCleaningDevice() {
		return cleaningDevice;
	}

	public void setCleaningDevice(CoreCleaningDevice cleaningDevice) {
		this.cleaningDevice = cleaningDevice;
		this.hasCleaningDevice = true;
	}
	
	public boolean hasCleaningDevice() {
		return this.hasCleaningDevice;
	}

	public abstract class MacsSystemAction extends AbstractAction {
		
		private static final long serialVersionUID = 4225709232788605717L;
		
		
		//probably not the best way to do this
		//every pressuretubeliquid object has a MacsSystem
		//pressuretube handles requests to do things in the background
		
		public MacsSystemAction() {
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			SwingWorker<Boolean, Void> aWorker = new SwingWorker<Boolean, Void>() {
				   @Override
				   protected Boolean doInBackground() throws Exception {
					   try {
						   doBackgroundStuff();
					   }
				       catch (InterruptedException e)
					   {
				    	   notifyInterrupted();
				    	   handleInterrupted();
				    	   return true;
					   }
					   notifyFinished();
					   handleFinished();
					   return true;
				   }
				  };
		    executeWorker(aWorker);
		}
		
		protected abstract void doBackgroundStuff() throws InterruptedException;
		protected void handleInterrupted() {
		}
		
		protected void handleFinished() {
		}

	}
	
	
}

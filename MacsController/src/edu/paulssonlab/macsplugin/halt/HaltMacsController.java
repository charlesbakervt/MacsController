package edu.paulssonlab.macsplugin.halt;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsSystem;


public class HaltMacsController {

	
	public HaltMacsController(final HaltMacsView view, final MacsSystem macsSystem) {
		
		final CoreCleaningDevice cleaningDevice = macsSystem.getCleaningDevice();
		final CoreMacsDevice macsDevice = macsSystem.getMacsDevice();
		
		view.setStopAction(new AbstractAction() {
			private static final long serialVersionUID = -770903755654534749L;

			@Override
			public void actionPerformed(ActionEvent e) {
				macsSystem.cancel();
			}
		});
		
		view.setPressureOffAction(new AbstractAction() {
			private static final long serialVersionUID = 3568162143022760687L;

			@Override
			public void actionPerformed(ActionEvent e) {
				 macsDevice.setFlowPressure(0.0f);
				 macsDevice.setValvePressure(0.0f);
				 cleaningDevice.setFillPressure(0.0f);
			}
		});
		
	}

}

package edu.paulssonlab.macsplugin.runmacs.action;

import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewCommunicator;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewStatus;

public class RunMacsActionController {
	
	public RunMacsActionController(RunMacsActionView view, final PressureTubeLiquid<?> ptLiquid, final RunMacsViewCommunicator comm) {
		
		MacsSystem macsSystem = ptLiquid.getMacsSystem();
		MacsSystem.MacsSystemAction injectCellsAction = macsSystem.new MacsSystemAction() {
			private static final long serialVersionUID = -8467265724201885869L;

			protected void doBackgroundStuff() throws InterruptedException {
				comm.setRunMacsViewStatus(RunMacsViewStatus.INITIATE_INJECTION);
				ptLiquid.inject();
			}
		};
		
		MacsSystem.MacsSystemAction runMacsAction = macsSystem.new MacsSystemAction() {
			private static final long serialVersionUID = -1160663999865942101L;

			protected void doBackgroundStuff() throws InterruptedException {
				comm.setRunMacsViewStatus(RunMacsViewStatus.INITIATE_MACSING);
				ptLiquid.runMacs();
			}
		};
		
		view.setInjectCellsAction(injectCellsAction);
		view.setRunMacsAction(runMacsAction);
	}

}

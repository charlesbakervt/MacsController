package edu.paulssonlab.macsplugin.cleaner.cleanaction;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewCommunicator;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewStatus;

public class CleanActionController {
	
	public CleanActionController(CleanActionView view, 
			final CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid> cleaningLiquid,
			final CleanMacsViewCommunicator comm) {

		MacsSystem macsSystem = cleaningLiquid.getMacsSystem();
		
		MacsSystem.MacsSystemAction cleanAction = macsSystem.new MacsSystemAction() {

			private static final long serialVersionUID = -900350908060801468L;

			protected void doBackgroundStuff() throws InterruptedException {
//				cleaningLiquid.setPressureTubeCleanerStatus(CleaningLiquidStatus.INITIATE_CLEANING);
				comm.setCleanMacsViewStatus(CleanMacsViewStatus.INITIATE_CLEANING);
				cleaningLiquid.cleanMacsSystem(); //clean PT and macs chip with liquid
			}
		};
		
		view.setCleanAction(cleaningLiquid.getCleaningFluid(), cleanAction);
	}
}

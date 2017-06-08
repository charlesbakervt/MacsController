package edu.paulssonlab.macsplugin.cleaner.fillemptyaction;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewCommunicator;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewStatus;



public class FillEmptyActionController {
	
	public FillEmptyActionController(FillEmptyActionView view, 
			final CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid> cleaningLiquid,
			final CleanMacsViewCommunicator comm) {

		MacsSystem macsSystem = cleaningLiquid.getMacsSystem();
		
		MacsSystem.MacsSystemAction emptyPTAction = macsSystem. new MacsSystemAction() {
			private static final long serialVersionUID = 3466268958946854854L;

			protected void doBackgroundStuff() throws InterruptedException {
				comm.setCleanMacsViewStatus(CleanMacsViewStatus.INITIATE_EMPTYING);
				cleaningLiquid.empty();
			}
		};
		
		MacsSystem.MacsSystemAction fillPTAction = macsSystem. new MacsSystemAction() {
			private static final long serialVersionUID = -3522517998967737750L;

			protected void doBackgroundStuff() throws InterruptedException {
				comm.setCleanMacsViewStatus(CleanMacsViewStatus.INITIATE_FILLING);
				cleaningLiquid.fillPressureTube();
			}
		};
		
		view.setEmptyPTAction(cleaningLiquid.getCleaningFluid(), emptyPTAction);
		view.setFillPTAction(cleaningLiquid.getCleaningFluid(), fillPTAction);
		
	}

}

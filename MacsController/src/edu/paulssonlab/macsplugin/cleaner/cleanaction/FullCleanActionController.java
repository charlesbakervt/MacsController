package edu.paulssonlab.macsplugin.cleaner.cleanaction;


import java.util.List;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewCommunicator;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsViewStatus;


public class FullCleanActionController {
			
	public FullCleanActionController(CleanActionView view, 
			final List<? extends CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid>> cleaningLiquids,
					final CleanMacsViewCommunicator comm) {

		assert (!cleaningLiquids.isEmpty());
		
		MacsSystem macsSystem = cleaningLiquids.get(0).getMacsSystem();
		
		MacsSystem.MacsSystemAction cleanAction = macsSystem.new MacsSystemAction() {
			
			private static final long serialVersionUID = 500208392005728584L;

			protected void doBackgroundStuff() throws InterruptedException {
				comm.setCleanMacsViewStatus(CleanMacsViewStatus.INITIATE_CLEANING);
				for (CleaningLiquid<?,?,?> cleaner : cleaningLiquids) {
					cleaner.cleanMacsSystem();
				}
			}
		};
		
		view.setFullCleanAction(cleanAction);
	}
}

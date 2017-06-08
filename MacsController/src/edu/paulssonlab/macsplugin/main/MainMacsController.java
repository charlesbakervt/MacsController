package edu.paulssonlab.macsplugin.main;

import java.util.List;

import org.micromanager.MMStudio;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsController;
import edu.paulssonlab.macsplugin.directcontrol.DirectControlController;
import edu.paulssonlab.macsplugin.halt.HaltMacsController;
import edu.paulssonlab.macsplugin.runmacs.RunMacsController;
import edu.paulssonlab.macsplugin.status.MacsStatusController;

public class MainMacsController {

	public MainMacsController(MainMacsView view, MacsSystem macsSystem,
			PressureTubeLiquid<StandardPTSettings> culture, 
			List<? extends CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid>> cleaningLiquids) {
		
		this(view, macsSystem, culture, cleaningLiquids, null);
	}
	
	public MainMacsController(MainMacsView view, MacsSystem macsSystem,
			PressureTubeLiquid<StandardPTSettings> culture, 
			List<? extends CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid>> cleaningLiquids,
			MMStudio gui) {
		
		
		if (gui == null) {
			new RunMacsController(view.getRunMacsView(), culture);
		}
		else {
			new RunMacsController(view.getRunMacsView(), culture, gui);
		}
		new CleanMacsController(view.getCleanMacsView(), cleaningLiquids);
		new DirectControlController(view.getDirectControlView(), macsSystem);
		new MacsStatusController(view.getMacsStatusView(), culture, cleaningLiquids);
		new HaltMacsController(view.getHaltMacsView(), macsSystem);
	}


}

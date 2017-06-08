package edu.paulssonlab.macsplugin.main;

import org.micromanager.MMStudio;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class MacsPlugin implements MMPlugin {
	public static final String menuName = "Macs Controller";
	public static final String tooltipDescription = "Controller for a Macs Device";
	private MMStudio gui;
	private MacsSystem macsSystem;
	private MacsGUI macsGui;
	
	@Override
	public String getCopyright() {
		return null;
	}

	@Override
	public String getDescription() {
		return tooltipDescription;
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
		macsSystem.cancel();
	}

	@Override
	public void setApp(ScriptInterface app) {
		
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		int toDebug = configuration.getIntegerProperty("debugArduino", 0);
		
		boolean debug = (toDebug > 0);
		
		gui = (MMStudio) app;
		macsGui = new MacsGUI(gui, debug);
		macsGui.constructBasicSetup();
		macsSystem = macsGui.getMacsSystem();
	}

	@Override
	public void show() {
		
		//mainView.setVisible(true);
		macsGui.setVisible(true);
		
		
	}

}

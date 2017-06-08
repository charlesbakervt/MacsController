package edu.paulssonlab.macsplugin.main;

import org.micromanager.MMStudio;

import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class TestMacs {
	
	public static void main(String[] args) {
		MMStudio gui = new MMStudio(false);
		
		
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		int toDebug = configuration.getIntegerProperty("debugArduino", 1);
		boolean debug = (toDebug > 0);
		
		MacsGUI macsGui = new MacsGUI(gui, debug);
		macsGui.constructBasicSetup();
		macsGui.setVisible(true);
	}

}

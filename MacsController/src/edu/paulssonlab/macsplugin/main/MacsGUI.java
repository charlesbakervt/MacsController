package edu.paulssonlab.macsplugin.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.micromanager.MMStudio;

import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicPTLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.MacsConfigurationProperties;

public class MacsGUI {

	private JFrame mainViewFrame;
	private BasicMainMacsView mainView;
	private MMStudio gui;
	private boolean debug;
	private MacsSystem macsSystem;

	public MacsGUI(MMStudio gui, boolean debug) {
		this.gui = gui;
		this.debug = debug;
	}
	
	public void constructBasicSetup() {
		mainView = new BasicMainMacsView();
		mainView.initializeBasicView();
		JPanel panelMain = mainView.getMainComponent();
		
		debug = (gui == null || debug);
		System.out.println("debug " + String.valueOf(debug));
		
		final BasicMacsCleanComboDevice macsDevice;
		if (debug) {
			macsDevice = new BasicMacsCleanComboDevice();
		}
		else {
			macsDevice = new BasicMacsCleanComboDevice(gui.getCore());
		}
		
		macsSystem = new MacsSystem(macsDevice, macsDevice);
		BasicPTLiquid culture = new BasicPTLiquid(macsSystem);
		
		BasicCleaningLiquid bleach = new BasicCleaningLiquid(macsSystem, BasicCleaningFluid.BLEACH);
		BasicCleaningLiquid ethanol = new BasicCleaningLiquid(macsSystem, BasicCleaningFluid.ETHANOL);
		BasicCleaningLiquid water = new BasicCleaningLiquid(macsSystem,BasicCleaningFluid.WATER);
		
		List<BasicCleaningLiquid> cleaningLiquids = new ArrayList<BasicCleaningLiquid>();
		
		cleaningLiquids.add(bleach);
		cleaningLiquids.add(ethanol);
		cleaningLiquids.add(water);
		
		new MainMacsController(mainView, macsSystem, culture, cleaningLiquids, gui);
		
		mainViewFrame = new JFrame();					
		mainViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainViewFrame.setBounds(100, 100, 450, 730);
		mainViewFrame.setContentPane(panelMain);
		mainViewFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				macsSystem.cancel();
			}
		});
	}
	
	public void setVisible(boolean visible) {
		mainView.setVisible(visible);
		mainViewFrame.setVisible(visible);
	}

	public MacsSystem getMacsSystem() {
		return macsSystem;
	}

	public static void main(String[] args) {
		
		MacsConfigurationProperties configuration = new MacsConfigurationProperties();
		configuration.openPropertiesFile();
		
		int toDebug = configuration.getIntegerProperty("debugArduino", 0);
		boolean debug = (toDebug > 0);
		
		
		MMStudio gui = null;
		MacsGUI macsGui = new MacsGUI(gui, debug);
		macsGui.constructBasicSetup();
		macsGui.setVisible(true);
	}
}

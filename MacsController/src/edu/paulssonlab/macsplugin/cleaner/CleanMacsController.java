package edu.paulssonlab.macsplugin.cleaner;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.paulssonlab.macsplugin.api.CleaningLiquid;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningFluid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicCleaningLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardCleaningLiquidSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.cleaner.cleanaction.CleanActionController;
import edu.paulssonlab.macsplugin.cleaner.cleanaction.FullCleanActionController;
import edu.paulssonlab.macsplugin.cleaner.fillemptyaction.FillEmptyActionController;
import edu.paulssonlab.macsplugin.cleaner.timing.CleanMacsTimingController;
import edu.paulssonlab.macsplugin.halt.BasicHaltMacsView;
import edu.paulssonlab.macsplugin.halt.HaltMacsController;

public class CleanMacsController {
	
	public CleanMacsController(CleanMacsView mainView, 
			List<? extends CleaningLiquid<StandardPTSettings, StandardCleaningLiquidSettings, BasicCleaningFluid>> cleaningLiquids) {
		
		int nLiquids = cleaningLiquids.size();
		CleanMacsViewCommunicator comm = new CleanMacsViewCommunicator();
		
		for (int i = 0; i < nLiquids; i++) {
			new CleanMacsTimingController(mainView.getCleanMacsTimingView(), cleaningLiquids.get(i),comm);
			new FillEmptyActionController(mainView.getFillEmptyActionView(), cleaningLiquids.get(i),comm);
			new CleanActionController(mainView.getCleanActionView(), cleaningLiquids.get(i),comm);
		}
		
		new FullCleanActionController(mainView.getCleanActionView(), cleaningLiquids, comm);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 370, 550);
					
					JPanel contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					frame.setContentPane(contentPane);
					frame.setMinimumSize(new Dimension(300,500));
					contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
					
					BasicCleanMacsView mainView = new BasicCleanMacsView();
					BasicHaltMacsView haltView = new BasicHaltMacsView();
					mainView.initializeBasicView();
					BasicMacsCleanComboDevice macsDevice = new BasicMacsCleanComboDevice();
					MacsSystem macsSystem = new MacsSystem(macsDevice, macsDevice);
					
					BasicCleaningLiquid bleach = new BasicCleaningLiquid(macsSystem, BasicCleaningFluid.BLEACH);
					BasicCleaningLiquid ethanol = new BasicCleaningLiquid(macsSystem, BasicCleaningFluid.ETHANOL);
					BasicCleaningLiquid water = new BasicCleaningLiquid(macsSystem,BasicCleaningFluid.WATER);
					
					List<BasicCleaningLiquid> cleaningLiquids = new ArrayList<BasicCleaningLiquid>();
					
					cleaningLiquids.add(bleach);
					cleaningLiquids.add(ethanol);
					cleaningLiquids.add(water);
					
					new CleanMacsController(mainView, cleaningLiquids);
					new HaltMacsController(haltView, macsSystem);
					
					JPanel haltPanel = haltView.getMainComponent();
					JPanel mPanel = mainView.getMainComponent();
					
					contentPane.add(mPanel);
					contentPane.add(haltPanel);
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

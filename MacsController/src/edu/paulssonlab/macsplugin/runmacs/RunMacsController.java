package edu.paulssonlab.macsplugin.runmacs;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.micromanager.MMStudio;

import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicPTLiquid;
import edu.paulssonlab.macsplugin.api.standardsetup.settings.StandardPTSettings;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;
import edu.paulssonlab.macsplugin.halt.BasicHaltMacsView;
import edu.paulssonlab.macsplugin.halt.HaltMacsController;
import edu.paulssonlab.macsplugin.runmacs.acquisition.AcquisitionController;
import edu.paulssonlab.macsplugin.runmacs.action.RunMacsActionController;
import edu.paulssonlab.macsplugin.runmacs.pressure.RunMacsPressureController;
import edu.paulssonlab.macsplugin.runmacs.timing.RunMacsTimingsController;


public class RunMacsController {

	public RunMacsController(RunMacsView runMacsView, PressureTubeLiquid<StandardPTSettings> ptLiquid) {
		this(runMacsView, ptLiquid, null);
	}
	
	public RunMacsController(RunMacsView runMacsView, PressureTubeLiquid<StandardPTSettings> ptLiquid, MMStudio gui) {
		RunMacsViewCommunicator comm = new RunMacsViewCommunicator();
		
		if (gui == null) {
			new AcquisitionController(runMacsView.getAcquisitionView(), ptLiquid, comm);
		}
		else {
			new AcquisitionController(runMacsView.getAcquisitionView(), ptLiquid, comm, gui);
		}
		
		new RunMacsActionController(runMacsView.getRunMacsActionView(), ptLiquid, comm);
		new RunMacsPressureController(runMacsView.getRunMacsPressureView(), ptLiquid, comm);
		new RunMacsTimingsController(runMacsView.getRunMacsTimingView(), ptLiquid, comm);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 350, 550);
					
					JPanel contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					frame.setContentPane(contentPane);
					
					contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					
					BasicRunMacsView mainView = new BasicRunMacsView();
					BasicHaltMacsView haltView = new BasicHaltMacsView();
					mainView.initializeBasicView();
					//NullCoreMacsDevice mMacsDevice = new NullCoreMacsDevice();
					BasicMacsCleanComboDevice macsDevice = new BasicMacsCleanComboDevice();
					MacsSystem macsSystem = new MacsSystem(macsDevice);
					BasicPTLiquid culture = new BasicPTLiquid(macsSystem);
					
					new RunMacsController(mainView, culture);
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

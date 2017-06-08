package edu.paulssonlab.macsplugin.directcontrol;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.paulssonlab.macsplugin.api.CoreCleaningDevice;
import edu.paulssonlab.macsplugin.api.CoreMacsDevice;
import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.standardsetup.BasicMacsCleanComboDevice;
import edu.paulssonlab.macsplugin.directcontrol.cleaner.DirectCleaningControlController;
import edu.paulssonlab.macsplugin.directcontrol.macs.DirectMacsControlController;

public class DirectControlController {

	public DirectControlController(DirectControlView mainView, MacsSystem macsSystem) {
		CoreMacsDevice macsDevice = macsSystem.getMacsDevice();
		CoreCleaningDevice cleaningDevice = macsSystem.getCleaningDevice();
		new DirectMacsControlController(mainView.getDirectMacsControlView(), macsDevice);
		new DirectCleaningControlController(mainView.getDirectCleaningControlView(), cleaningDevice);	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 350, 750);
					
					BasicDirectControlView mainView = new BasicDirectControlView();
					mainView.initializeBasicView();
					
					BasicMacsCleanComboDevice macsDevice = new BasicMacsCleanComboDevice();
					MacsSystem macsSystem = new MacsSystem(macsDevice, macsDevice);
					new DirectControlController(mainView, macsSystem);
					
					JPanel mPanel = mainView.getMainComponent();
					frame.setContentPane(mPanel);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

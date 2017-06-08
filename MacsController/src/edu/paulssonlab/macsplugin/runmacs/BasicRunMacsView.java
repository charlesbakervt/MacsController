package edu.paulssonlab.macsplugin.runmacs;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.paulssonlab.macsplugin.runmacs.acquisition.AcquisitionView;
import edu.paulssonlab.macsplugin.runmacs.acquisition.BasicRunAcquisitionView;
import edu.paulssonlab.macsplugin.runmacs.action.BasicRunMacsActionView;
import edu.paulssonlab.macsplugin.runmacs.action.RunMacsActionView;
import edu.paulssonlab.macsplugin.runmacs.pressure.BasicRunMacsPressureView;
import edu.paulssonlab.macsplugin.runmacs.pressure.RunMacsPressureView;
import edu.paulssonlab.macsplugin.runmacs.timing.BasicRunMacsTimingView;
import edu.paulssonlab.macsplugin.runmacs.timing.RunMacsTimingsView;

public class BasicRunMacsView implements RunMacsView {
	private JPanel macsPanel;
	private RunMacsActionView runMacsActionView;
	private RunMacsPressureView runMacsPressureView;
	private RunMacsTimingsView runMacsTimingsView;
	private AcquisitionView acquisitionView;

	
	
	public BasicRunMacsView() {
		macsPanel = new JPanel();
		macsPanel.setLayout(new BoxLayout(macsPanel, BoxLayout.PAGE_AXIS));
	}
	
	public void initializeBasicView() {
		//simple helper function to initialize the standard view
		BasicRunAcquisitionView acquisitionView = new BasicRunAcquisitionView();
		BasicRunMacsActionView actionView = new BasicRunMacsActionView();
		BasicRunMacsPressureView pressureView = new BasicRunMacsPressureView();
		BasicRunMacsTimingView timingView = new BasicRunMacsTimingView();
		
		attachMacsPressureView(pressureView);
		attachMacsTimingView(timingView);
		attachMacsActionView(actionView);
		attachAcquisitionView(acquisitionView);
	}
	

	@Override
	public JPanel getMainComponent() {
		return macsPanel;
	}
	
	@Override
	public void setVisible(boolean visible) {
		macsPanel.setVisible(visible);
	}	
	
	@Override
	public RunMacsActionView getRunMacsActionView() {
		return runMacsActionView;
	}

	@Override
	public RunMacsPressureView getRunMacsPressureView() {
		return runMacsPressureView;
	}

	@Override
	public RunMacsTimingsView getRunMacsTimingView() {
		return runMacsTimingsView;
	}

	@Override
	public AcquisitionView getAcquisitionView() {
		return acquisitionView;
	}

	@Override
	public void attachMacsActionView(RunMacsActionView view) {
		runMacsActionView = view;
		macsPanel.add(Box.createRigidArea(new Dimension(0,5)));
		macsPanel.add(view.getMainComponent());
	}

	@Override
	public void attachMacsPressureView(RunMacsPressureView view) {
		runMacsPressureView = view;
		macsPanel.add(Box.createRigidArea(new Dimension(0,5)));
		macsPanel.add(view.getMainComponent());
	}

	
	@Override
	public void attachMacsTimingView(RunMacsTimingsView view) {
		runMacsTimingsView = view;
		macsPanel.add(Box.createRigidArea(new Dimension(0,5)));
		macsPanel.add(view.getMainComponent());
	}

	@Override
	public void attachAcquisitionView(AcquisitionView view) {
		acquisitionView = view;
		macsPanel.add(Box.createRigidArea(new Dimension(0,5)));
		macsPanel.add(view.getMainComponent());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicRunMacsView mainView = new BasicRunMacsView();
					
					mainView.initializeBasicView();
					JPanel mPanel = mainView.getMainComponent();
					
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 350, 550);
					frame.setContentPane(mPanel);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
}

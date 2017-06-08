package edu.paulssonlab.macsplugin.directcontrol;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.paulssonlab.macsplugin.directcontrol.cleaner.BasicDirectCleaningControlView;
import edu.paulssonlab.macsplugin.directcontrol.cleaner.DirectCleaningControlView;
import edu.paulssonlab.macsplugin.directcontrol.macs.BasicDirectMacsControlView;
import edu.paulssonlab.macsplugin.directcontrol.macs.DirectMacsControlView;

public class BasicDirectControlView implements DirectControlView {

	private JPanel panelDirectControl;
	private DirectCleaningControlView directCleaningControlView;
	private DirectMacsControlView directMacsControlView;
	
	
	public BasicDirectControlView() {
		panelDirectControl = new JPanel();
		panelDirectControl.setLayout(new BoxLayout(panelDirectControl, BoxLayout.PAGE_AXIS));
		
	}
	
	public void initializeBasicView(boolean includeCleaning) {
		
		BasicDirectMacsControlView macsView = new BasicDirectMacsControlView();
		attachDirectMacsControlView(macsView);
		
		if (includeCleaning) {
		BasicDirectCleaningControlView cleaningView = new BasicDirectCleaningControlView();
		attachDirectCleaningControlView(cleaningView);
		}
	}
	
	public void initializeBasicView() {
		//initializeBasicView(true);
		BasicDirectControlCombinedView altView = new BasicDirectControlCombinedView();
		directCleaningControlView = altView;
		directMacsControlView = altView;
		panelDirectControl.add(altView.getMainComponent());
		
	}
	
	@Override
	public JPanel getMainComponent() {
		return panelDirectControl;
	}
	@Override
	public void setVisible(boolean visible) {
		panelDirectControl.setVisible(visible);
	}
	@Override
	public DirectCleaningControlView getDirectCleaningControlView() {
		return directCleaningControlView;
	}
	@Override
	public DirectMacsControlView getDirectMacsControlView() {
		return directMacsControlView;
	}
	@Override
	public void attachDirectCleaningControlView(DirectCleaningControlView view) {
		directCleaningControlView = view;
		panelDirectControl.add(view.getMainComponent());
		
	}
	@Override
	public void attachDirectMacsControlView(DirectMacsControlView view) {
		directMacsControlView = view;
		panelDirectControl.add(view.getMainComponent());
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicDirectControlView mainView = new BasicDirectControlView();
					
					mainView.initializeBasicView();
					JPanel mPanel = mainView.getMainComponent();
					
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 375, 750);
					frame.setContentPane(mPanel);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

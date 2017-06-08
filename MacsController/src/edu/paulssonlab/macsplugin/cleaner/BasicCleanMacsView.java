package edu.paulssonlab.macsplugin.cleaner;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.paulssonlab.macsplugin.cleaner.cleanaction.BasicCleanActionView;
import edu.paulssonlab.macsplugin.cleaner.cleanaction.CleanActionView;
import edu.paulssonlab.macsplugin.cleaner.fillemptyaction.BasicFillEmptyActionView;
import edu.paulssonlab.macsplugin.cleaner.fillemptyaction.FillEmptyActionView;
import edu.paulssonlab.macsplugin.cleaner.timing.BasicCleanMacsTimingView;
import edu.paulssonlab.macsplugin.cleaner.timing.CleanMacsTimingView;

public class BasicCleanMacsView implements CleanMacsView {

	private JPanel cleanMacsPanel;
	private CleanActionView cleanActionView;
	private FillEmptyActionView fillEmptyActionView;
	private CleanMacsTimingView cleanMacsTimingView;
	
	public BasicCleanMacsView() {
		cleanMacsPanel = new JPanel();
		cleanMacsPanel.setLayout(new BoxLayout(cleanMacsPanel, BoxLayout.PAGE_AXIS));
	}
	
	public void initializeBasicView() {
		BasicCleanActionView cleanActionView = new BasicCleanActionView();
		BasicFillEmptyActionView fillEmptyActionView = new BasicFillEmptyActionView();
		BasicCleanMacsTimingView cleanMacsTimingView = new BasicCleanMacsTimingView();
		
		attachCleanMacsTimingView(cleanMacsTimingView);
		attachFillEmptyActionView(fillEmptyActionView);
		attachCleanActionView(cleanActionView);
	}

	@Override
	public JPanel getMainComponent() {
		return cleanMacsPanel;
	}

	@Override
	public void setVisible(boolean visible) {
		cleanMacsPanel.setVisible(visible);
	}

	@Override
	public CleanActionView getCleanActionView() {
		return cleanActionView;
	}

	@Override
	public FillEmptyActionView getFillEmptyActionView() {
		return fillEmptyActionView;
	}

	@Override
	public CleanMacsTimingView getCleanMacsTimingView() {
		return cleanMacsTimingView;
	}

	@Override
	public void attachCleanActionView(CleanActionView view) {
		cleanActionView = view;
		cleanMacsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		cleanMacsPanel.add(view.getMainComponent());
	}

	@Override
	public void attachFillEmptyActionView(FillEmptyActionView view) {
		fillEmptyActionView = view;
		cleanMacsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		cleanMacsPanel.add(view.getMainComponent());
	}

	@Override
	public void attachCleanMacsTimingView(CleanMacsTimingView view) {
		cleanMacsTimingView = view;
		cleanMacsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		cleanMacsPanel.add(view.getMainComponent());
	}

	public static void main(String[] args) {
		BasicCleanMacsView macsView = new BasicCleanMacsView();
		macsView.initializeBasicView();
		JPanel mPanel = macsView.getMainComponent();
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 375, 500);
		frame.setContentPane(mPanel);
		frame.setVisible(true);

	}

}

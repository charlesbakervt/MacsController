 package edu.paulssonlab.macsplugin.main;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import edu.paulssonlab.macsplugin.cleaner.BasicCleanMacsView;
import edu.paulssonlab.macsplugin.cleaner.CleanMacsView;
import edu.paulssonlab.macsplugin.directcontrol.BasicDirectControlView;
import edu.paulssonlab.macsplugin.directcontrol.DirectControlView;
import edu.paulssonlab.macsplugin.halt.BasicHaltMacsView;
import edu.paulssonlab.macsplugin.halt.HaltMacsView;
import edu.paulssonlab.macsplugin.runmacs.BasicRunMacsView;
import edu.paulssonlab.macsplugin.runmacs.RunMacsView;
import edu.paulssonlab.macsplugin.status.BasicMacsStatusView;
import edu.paulssonlab.macsplugin.status.MacsStatusView;


public class BasicMainMacsView implements MainMacsView {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private RunMacsView runMacsView;
	private CleanMacsView cleanMacsView;
	private DirectControlView directControlView;
	private MacsStatusView macsStatusView;
	private HaltMacsView haltMacsView;
	
	
	public BasicMainMacsView() {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setPreferredSize(new Dimension(350, 680));
		tabbedPane.setMaximumSize(new Dimension(390, 730));
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.add(tabbedPane);

	}
	
	public void initializeBasicView() {
		BasicRunMacsView runMacsView = new BasicRunMacsView();
		runMacsView.initializeBasicView();
		
		BasicCleanMacsView cleanMacsView = new BasicCleanMacsView();
		cleanMacsView.initializeBasicView();
		
		BasicDirectControlView directControlView = new BasicDirectControlView();
		directControlView.initializeBasicView();
		
		BasicMacsStatusView macsStatusView = new BasicMacsStatusView();
		
		BasicHaltMacsView haltMacsView = new BasicHaltMacsView();
		
		attachRunMacsView(runMacsView);
		attachCleanMacsView(cleanMacsView);
		attachDirectControlView(directControlView);
		attachMacsStatusView(macsStatusView);
		attachHaltMacsView(haltMacsView);
		
	}

	
	@Override
	public JPanel getMainComponent() {
		return contentPane;
	}

	@Override
	public void setVisible(boolean visible) {
		contentPane.setVisible(visible);
	}
	

	@Override
	public void attachRunMacsView(RunMacsView view) {
		runMacsView = view;
		tabbedPane.addTab("Run MACS", null, view.getMainComponent(), null);
	}

	@Override
	public void attachCleanMacsView(CleanMacsView view) {
		cleanMacsView = view;
		tabbedPane.addTab("Clean MACS", null, view.getMainComponent(), null);
	}
	
	@Override
	public void attachDirectControlView(DirectControlView view) {
		directControlView = view;
		tabbedPane.addTab("Direct Control", null, view.getMainComponent(), null);
	}
	
	@Override
	public void attachMacsStatusView(MacsStatusView view) {
		macsStatusView = view;
		contentPane.add(view.getMainComponent());
	}
	
	@Override
	public void attachHaltMacsView(HaltMacsView view) {
		haltMacsView = view;
		contentPane.add(view.getMainComponent());
	}
	
	@Override
	public RunMacsView getRunMacsView() {
		return runMacsView;
	}

	@Override
	public CleanMacsView getCleanMacsView() {
		return cleanMacsView;
	}

	@Override
	public DirectControlView getDirectControlView() {
		return directControlView;
	}

	@Override
	public MacsStatusView getMacsStatusView() {
		return macsStatusView;
	}

	@Override
	public HaltMacsView getHaltMacsView() {
		return haltMacsView;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					BasicMainMacsView mainView = new BasicMainMacsView();
					mainView.initializeBasicView();
					JPanel panelMain = mainView.getMainComponent();
					
//					panelMain.setPreferredSize(new Dimension(350, 750));
//					panelMain.setMaximumSize(new Dimension(390, 800));
					
					JFrame mainViewFrame = new JFrame();					
					mainViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					mainViewFrame.setBounds(100, 100, 375, 735);
					mainViewFrame.setContentPane(panelMain);
					mainView.setVisible(true);
					mainViewFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
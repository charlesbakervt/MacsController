package edu.paulssonlab.macsplugin.halt;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicHaltMacsView implements HaltMacsView {

	private JPanel haltMacsPanel;
	private JButton btnStop;
	private JButton btnPressureOff;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BasicHaltMacsView macsView = new BasicHaltMacsView();
					
					JPanel mPanel = macsView.getMainComponent();
					
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 423, 774);
					frame.setContentPane(mPanel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public JPanel getMainComponent() {
		return haltMacsPanel;
	}

	@Override
	public void setStopAction(Action stopMacsAction) {
		btnStop.addActionListener(stopMacsAction);
	}
	
	@Override
	public void setPressureOffAction(Action pressureOffAction) {
		btnPressureOff.addActionListener(pressureOffAction);
	}	
	
	@Override
	public void setVisible(boolean visible) {
		haltMacsPanel.setVisible(visible);
	}

	
	
	
	public BasicHaltMacsView() {
		haltMacsPanel = new JPanel();
		haltMacsPanel.setLayout(new BoxLayout(haltMacsPanel, BoxLayout.LINE_AXIS));
		
		btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Dialog", Font.BOLD, 20));
		btnStop.setToolTipText("Stop any currently running operations and set the macs system to resting state.");
		
		btnPressureOff = new JButton("Pressure Off");
		btnPressureOff.setFont(new Font("Dialog", Font.BOLD, 20));
		btnPressureOff.setToolTipText("Set Pressure Regulators to 0 psi. Note: any operations that are left running can override this command.");
		
		haltMacsPanel.add(btnStop);
		haltMacsPanel.add(btnPressureOff);
		
	}

}

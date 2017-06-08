package edu.paulssonlab.macsplugin.runmacs.acquisition;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BasicRunAcquisitionView implements AcquisitionView {
	
	private JPanel panelAcquisition;
	private JPanel panelFileName;
	private JPanel panelButtons;
	private String acquisitionFile = "C:/path/to/acquisition_settings.xml";
	private JButton btnLaunchFileExplorer;
	private JButton btnRunAcquisition;
	private JLabel lblAcquisitionFile;
	private JLabel lblFile;
	
	public BasicRunAcquisitionView() {
		
		panelAcquisition = new JPanel();
		panelAcquisition.setBorder(new TitledBorder(null, "Acquisition", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAcquisition.setLayout(new BoxLayout(panelAcquisition, BoxLayout.PAGE_AXIS));
		
		panelFileName = new JPanel();
		panelButtons = new JPanel();
		
		
		
		lblFile = new JLabel("File:");
		lblFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lblAcquisitionFile = new JLabel(acquisitionFile);
		lblAcquisitionFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcquisitionFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		btnRunAcquisition = new JButton("Run Acquisition");
		btnRunAcquisition.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRunAcquisition.setEnabled(false);
		btnRunAcquisition.setToolTipText("Run acquisition, defined in the xml file, with Macs protocol.");
		
		btnLaunchFileExplorer = new JButton("Choose File");
		btnLaunchFileExplorer.setFont(new Font("Dialog", Font.PLAIN, 16));
		String fileExplorerToolTip = "<html> Please select the acquisition setings " +
									"xml file you created <br> with the Multi-Dimensional " +
									"Acquisition Window in Micro-Manager. </html>";
		
		btnLaunchFileExplorer.setToolTipText(fileExplorerToolTip);
		
		btnLaunchFileExplorer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Acquisition file (xml)", "xml");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			
			int result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
			    acquisitionFile = selectedFile.getAbsolutePath();
			    lblAcquisitionFile.setText(acquisitionFile);
			    btnRunAcquisition.setEnabled(true);
			}
		}
	});
		
		
		configureAcquisitionPanel();
		panelButtons.setPreferredSize(new Dimension(375,50));
		panelButtons.setMaximumSize(new Dimension(375,50));
		panelFileName.setPreferredSize(new Dimension(375,20));
		panelFileName.setMaximumSize(new Dimension(375,20));
		panelAcquisition.add(panelButtons);
		panelAcquisition.add(panelFileName);
		panelAcquisition.setPreferredSize(new Dimension(375,100));
		panelAcquisition.setMaximumSize(new Dimension(375,100));
		
	}
	
	private void configureAcquisitionPanel() {
		panelButtons.setLayout(new GridBagLayout());
		panelFileName.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc_btnLaunchFileExplorer = new GridBagConstraints();
		gbc_btnLaunchFileExplorer.anchor = GridBagConstraints.WEST;
		gbc_btnLaunchFileExplorer.insets = new Insets(5,5,5,0);
		gbc_btnLaunchFileExplorer.weighty = 1.0;
		gbc_btnLaunchFileExplorer.weightx = 1.0;
		gbc_btnLaunchFileExplorer.fill = GridBagConstraints.BOTH;
		gbc_btnLaunchFileExplorer.gridx = 0;
		gbc_btnLaunchFileExplorer.gridy = 0;
		panelButtons.add(btnLaunchFileExplorer, gbc_btnLaunchFileExplorer);
		
		GridBagConstraints gbc_btnRunAcquisition = new GridBagConstraints();
		gbc_btnRunAcquisition.anchor = GridBagConstraints.WEST;
		gbc_btnRunAcquisition.insets = new Insets(5,5,5,5);
		gbc_btnRunAcquisition.weighty = 1.0;
		gbc_btnRunAcquisition.weightx = 1.0;
		gbc_btnRunAcquisition.fill = GridBagConstraints.BOTH;
		gbc_btnRunAcquisition.gridx = 1;
		gbc_btnRunAcquisition.gridy = 0;
		panelButtons.add(btnRunAcquisition, gbc_btnRunAcquisition);
		
		GridBagConstraints gbc_labelFile = new GridBagConstraints();
		gbc_labelFile.anchor = GridBagConstraints.WEST;
		gbc_labelFile.insets = new Insets(5, 5, 0, 0);
		//gbc_labelFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelFile.weighty = 1.0;
		gbc_labelFile.weightx = 0.2;
		gbc_labelFile.gridx = 0;
		gbc_labelFile.gridy = 0;
		panelFileName.add(lblFile, gbc_labelFile);
		
		GridBagConstraints gbc_labelAcquisitionFile = new GridBagConstraints();
		gbc_labelAcquisitionFile.anchor = GridBagConstraints.WEST;
		gbc_labelAcquisitionFile.insets = new Insets(5, 0, 0, 0);
		gbc_labelAcquisitionFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelAcquisitionFile.gridx = 1;
		gbc_labelAcquisitionFile.gridy = 0;
		gbc_labelAcquisitionFile.weighty = 1.0;
		gbc_labelAcquisitionFile.weightx = 5.0;
		panelFileName.add(lblAcquisitionFile, gbc_labelAcquisitionFile);
		
	}

	@Override
	public JPanel getMainComponent() {
		return panelAcquisition;
	}

	@Override
	public void setVisible(boolean visible) {
		panelAcquisition.setVisible(visible);
	}

	@Override
	public String getAcquisitionFile() {
		return acquisitionFile;
		
	}

	@Override
	public void setRunAcquisitionAction(Action runAcquisitionAction) {
		btnRunAcquisition.addActionListener(runAcquisitionAction);
		
	}

}

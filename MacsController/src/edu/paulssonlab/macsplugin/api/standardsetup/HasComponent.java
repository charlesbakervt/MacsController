package edu.paulssonlab.macsplugin.api.standardsetup;

import javax.swing.JComponent;

public interface HasComponent {
	JComponent getMainComponent();
	void setVisible(boolean visible);

}

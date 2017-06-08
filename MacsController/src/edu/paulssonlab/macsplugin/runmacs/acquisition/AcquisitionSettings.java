package edu.paulssonlab.macsplugin.runmacs.acquisition;

public class AcquisitionSettings {

	private String acqusitionFile = "";
	
	public AcquisitionSettings() {
		this("");
	}
	
	public AcquisitionSettings(String acqusitionFile) {
		this.setAcqusitionFile(acqusitionFile);
	}

	public String getAcqusitionFile() {
		return acqusitionFile;
	}

	public void setAcqusitionFile(String acqusitionFile) {
		this.acqusitionFile = acqusitionFile;
	}

}

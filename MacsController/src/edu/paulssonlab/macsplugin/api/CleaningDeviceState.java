package edu.paulssonlab.macsplugin.api;

public enum CleaningDeviceState {
	ACTIVELY_FLOW_BLEACH(9, "Actively Fill Bleach"),
	ACTIVELY_FLOW_ETHANOL(10, "Actively Fill Ethanol"),
	ACTIVELY_FLOW_WATER(11, "Actively Fill Water"),
	PASSIVELY_FLOW_BLEACH(6, "Passively Fill Bleach"),
	PASSIVELY_FLOW_ETHANOL(7, "Passively Fill Ethanol"),
	PASSIVELY_FLOW_WATER(8, "Passively Fill Water"),
	ALL_OFF(-1, "All Off");
	
	private int stateNum;
	private String niceName;
	
	private CleaningDeviceState(int stateNum, String name) {
		this.stateNum = stateNum;
		this.niceName = name;
	}
	
	@Override
	public String toString() {
		return this.niceName;
	}
	
	public int getStateNum() {
		return stateNum;
	}
	
	public static CleaningDeviceState fromString(String text) {
		for (CleaningDeviceState b: CleaningDeviceState.values()) {
			if (b.niceName.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	
}
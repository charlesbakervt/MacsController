package edu.paulssonlab.macsplugin.api;

public enum GrowthDeviceState {
	FROM_GROWTH_TO_PT(12, "Fill PT Growth"),
	FROM_GROWTH_TO_WASTE(13, "Flow Growth to Waste"),
	ACTIVELY_FLOW_BLEACH_GROWTH(17, "Actively Fill Bleach"),
	ACTIVELY_FLOW_ETHANOL_GROWTH(18, "Actively Fill Ethanol"),
	ACTIVELY_FLOW_WATER_GROWTH(19, "Actively Fill Water"),
	PASSIVELY_FLOW_BLEACH_GROWTH(14, "Passively Fill Bleach"),
	PASSIVELY_FLOW_ETHANOL_GROWTH(15, "Passively Fill Ethanol"),
	PASSIVELY_FLOW_WATER_GROWTH(16, "Passively Fill Water"),
	ALL_OFF(-1, "All Off");
	
	private int stateNum;
	private String niceName;
	
	private GrowthDeviceState(int stateNum, String name) {
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
	
	public static GrowthDeviceState fromString(String text) {
		for (GrowthDeviceState b: GrowthDeviceState.values()) {
			if (b.niceName.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
}

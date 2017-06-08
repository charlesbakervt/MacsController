package edu.paulssonlab.macsplugin.api;

public enum MacsDeviceState { 
	RESTING(1, "Resting"), 
	PT_TO_WASTE(2, "PT to Waste"), 
	OPEN_STATE(3, "Open"),
	HALF_OPEN_STATE(4, "Half-Open"),
	CLOSED_STATE(5, "Closed"),
	ALL_OFF(0, "All Off"),
	ALLOW_FILLING(-1, "Allow Filling"); // not normally needed unless an additional pinch valve is used for filling
	
	private int stateNum;
	private String niceName;
	
	private MacsDeviceState(int stateNum, String name) {
		this.stateNum = stateNum;
		this.niceName = name;
	}
	
	@Override
	public String toString() {
		return this.niceName;
//		switch(this) {
//		case RESTING: return "Resting";
//		case PT_TO_WASTE: return "PT to Waste";
//		case OPEN_STATE: return "Open";
//		case HALF_OPEN_STATE: return "Half-Open";
//		case CLOSED_STATE: return "Closed";
//		case ALL_OFF: return "All Off";
//		case ALLOW_FILLING: return "Allow Filling";
//		default: throw new IllegalArgumentException();
//		}
	}

	public int getStateNum() {
		return stateNum;
	}
	
	public static MacsDeviceState fromString(String text) {
		for (MacsDeviceState b: MacsDeviceState.values()) {
			if (b.niceName.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	
	
}

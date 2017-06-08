package edu.paulssonlab.macsplugin.api.standardsetup;

import edu.paulssonlab.macsplugin.api.CleaningDeviceState;

public enum BasicCleaningFluid {
	BLEACH(CleaningDeviceState.PASSIVELY_FLOW_BLEACH, CleaningDeviceState.ACTIVELY_FLOW_BLEACH),
	ETHANOL(CleaningDeviceState.PASSIVELY_FLOW_ETHANOL, CleaningDeviceState.ACTIVELY_FLOW_ETHANOL), 
	WATER(CleaningDeviceState.PASSIVELY_FLOW_WATER, CleaningDeviceState.ACTIVELY_FLOW_WATER);
	
	private CleaningDeviceState flowState;
	private CleaningDeviceState injectState;
	
	private BasicCleaningFluid(CleaningDeviceState mFlowState, CleaningDeviceState mInjectState) {
		this.flowState = mFlowState;
		this.injectState = mInjectState;
	}
	
	public CleaningDeviceState getFlowState() {
		return this.flowState;
	}
	
	public CleaningDeviceState getInjectState() {
		return this.injectState;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case BLEACH: return "Bleach";
		case ETHANOL: return "Ethanol";
		case WATER: return "Water";
		default: throw new IllegalArgumentException();
		}
	}
	
}

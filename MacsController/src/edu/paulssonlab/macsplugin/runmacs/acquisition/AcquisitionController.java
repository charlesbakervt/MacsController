package edu.paulssonlab.macsplugin.runmacs.acquisition;

import org.micromanager.MMStudio;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.utils.MMScriptException;

import edu.paulssonlab.macsplugin.api.MacsSystem;
import edu.paulssonlab.macsplugin.api.PressureTubeLiquid;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewCommunicator;
import edu.paulssonlab.macsplugin.runmacs.RunMacsViewStatus;

public class AcquisitionController {
	private Runnable macsRunnable = null;
	private boolean debug = false;
	private MMStudio gui = null;
	private AcquisitionView view;
	private PressureTubeLiquid<?> ptLiquid;
	private RunMacsViewCommunicator comm;

	public AcquisitionController(final AcquisitionView view, final PressureTubeLiquid<?> ptLiquid, final RunMacsViewCommunicator comm) {
		this(view,ptLiquid,comm,null);
	}
	
	public AcquisitionController(final AcquisitionView view, final PressureTubeLiquid<?> ptLiquid, final RunMacsViewCommunicator comm, MMStudio gui) {
		this.view = view;
		this.gui = gui;
		this.ptLiquid = ptLiquid;
		
		if (gui == null) {
			this.debug = true;
		}
		else {
			this.debug = false;
		}
		
		this.comm = comm;
		
		initialize();
	}
	
	private void initialize() {
		MacsSystem macsSystem = ptLiquid.getMacsSystem();
		
		
		
		  MacsSystem.MacsSystemAction runAcquisition = macsSystem.new MacsSystemAction() {
			
			private static final long serialVersionUID = 6080991355892793100L;

				protected void doBackgroundStuff() throws InterruptedException {
					
					macsRunnable = new Runnable() {

						@Override
						public void run() {
							try {
								ptLiquid.runMacs();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
					
					comm.setRunMacsViewStatus(RunMacsViewStatus.INITIATE_MACSING);
					if (!debug) {
						
						String acquisitionFile = view.getAcquisitionFile();
						try {
							gui.loadAcquisition(acquisitionFile);
						} catch (MMScriptException e) {
							e.printStackTrace();
						}
						AcquisitionEngine acq = gui.getAcquisitionEngine();
						
						// Run the runnable on all frames and positions,
						// but only channel 0 and slice 0.
						// -1 --> attach to all planes along given dimension
						acq.clearRunnables();
						acq.attachRunnable(-1,-1,0,0,macsRunnable); // f, p, c, s
						try {
							gui.runAcquisition();
						} catch (MMScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					
				}
			};
			view.setRunAcquisitionAction(runAcquisition);
	}
}

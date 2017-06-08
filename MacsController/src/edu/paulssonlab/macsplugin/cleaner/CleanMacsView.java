package edu.paulssonlab.macsplugin.cleaner;

import edu.paulssonlab.macsplugin.api.standardsetup.HasComponent;
import edu.paulssonlab.macsplugin.cleaner.cleanaction.CleanActionView;
import edu.paulssonlab.macsplugin.cleaner.fillemptyaction.FillEmptyActionView;
import edu.paulssonlab.macsplugin.cleaner.timing.CleanMacsTimingView;

public interface CleanMacsView extends HasComponent {

	CleanActionView getCleanActionView();
	FillEmptyActionView getFillEmptyActionView();
	CleanMacsTimingView getCleanMacsTimingView();
	
	 void attachCleanActionView(CleanActionView view);
	 void attachFillEmptyActionView(FillEmptyActionView view);
	 void attachCleanMacsTimingView(CleanMacsTimingView view);
	
}

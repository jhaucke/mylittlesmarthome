package com.github.jhaucke.mylittlesmarthome.gui;

import com.github.jhaucke.mylittlesmarthome.service.hue.HueService;
import com.google.inject.Inject;

import de.saxsys.mvvmfx.ViewModel;

/**
 * The {@link ViewModel} of {@link MainView}.
 */
public class MainViewModel implements ViewModel {

	@Inject
	private HueService hueService;

	public void switchLight() {
		hueService.switchLight(3);
	}
}

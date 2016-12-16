package com.github.jhaucke.mylittlesmarthome.service.raspiscreen;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

/**
 * The service class for the raspberry pi screen.
 */
@Singleton
public class RaspiScreenService {

	private final Logger logger;

	private boolean isScreenOn;

	private LocalDateTime lastScreenAction;

	public RaspiScreenService() {
		super();

		logger = LoggerFactory.getLogger(RaspiScreenService.class);
	}

	public void turnScreenOn() {
		isScreenOn = true;
		switchBackLight("0");
	}

	public void turnScreenOff() {
		isScreenOn = false;
		switchBackLight("1");
	}

	private void switchBackLight(String backLightValue) {
		String[] cmdSwitchBl = { "sh", "-c",
				"echo " + backLightValue + " > /sys/class/backlight/rpi_backlight/bl_power" };
		try {
			Runtime.getRuntime().exec(cmdSwitchBl);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public boolean isScreenOn() {
		return isScreenOn;
	}

	public LocalDateTime getLastScreenAction() {
		return lastScreenAction;
	}

	public void setLastScreenAction(LocalDateTime lastScreenAction) {
		this.lastScreenAction = lastScreenAction;
	}
}

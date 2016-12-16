package com.github.jhaucke.mylittlesmarthome.watchdog;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jhaucke.mylittlesmarthome.service.raspiscreen.RaspiScreenService;
import com.google.inject.Inject;

/**
 * {@link Runnable} to handle the screen time out.
 */
public class ScreenTimeOutWD implements Runnable {

	public static final String SCREEN_TIME_OUT_RESET = "ScreenTimeOutReset";

	private final Logger logger;

	private final RaspiScreenService raspiScreenService;

	/**
	 * Constructor for {@link ScreenTimeOutWD}.
	 */
	@Inject
	public ScreenTimeOutWD(RaspiScreenService raspiScreenService) {
		super();
		logger = LoggerFactory.getLogger(ScreenTimeOutWD.class);

		this.raspiScreenService = raspiScreenService;
		this.raspiScreenService.setLastScreenAction(LocalDateTime.now());

		try {
			Process exec = Runtime.getRuntime().exec("sudo chmod 777 /sys/class/backlight/rpi_backlight/bl_power");
			exec.waitFor();
		} catch (IOException | InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {

		while (true) {
			if (raspiScreenService.getLastScreenAction().isBefore(LocalDateTime.now().minusMinutes(1))) {
				raspiScreenService.turnScreenOff();
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ie) {
				logger.error(ie.getMessage());
			}
		}
	}
}

package com.github.jhaucke.mylittlesmarthome;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jhaucke.fritzboxconnector.FritzBoxConnector;
import com.github.jhaucke.fritzboxconnector.HttpInterface;
import com.github.jhaucke.mylittlesmarthome.database.constants.Actuator;
import com.github.jhaucke.mylittlesmarthome.datacollector.WashingMachineDC;
import com.github.jhaucke.mylittlesmarthome.watchdog.ScreenTimeOutWD;
import com.github.jhaucke.mylittlesmarthome.watchdog.WashingMachineWD;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * The engine of the smarthome backend functionality.
 */
public class Engine {

	private final Logger logger;

	private final ScreenTimeOutWD screenTimeOutWD;

	@Inject
	public Engine(ScreenTimeOutWD screenTimeOutWD) {

		super();
		logger = LoggerFactory.getLogger(Engine.class);

		this.screenTimeOutWD = screenTimeOutWD;
	}

	@Inject
	public void startBackendServices(@Named("fritzBoxUser") String fritzBoxUser,
			@Named("fritzBoxPassword") String fritzBoxPassword, @Named("fritzBoxHostName") String fritzBoxHostName)
					throws IOException {

		FritzBoxConnector fritzBoxConnector = new FritzBoxConnector(fritzBoxUser, fritzBoxPassword,
				fritzBoxHostName.isEmpty() ? null : fritzBoxHostName);

		HttpInterface fritzBoxInterface = fritzBoxConnector.getHttpInterface();

		Thread threadWashingMachineDC = new Thread(new WashingMachineDC(fritzBoxInterface, Actuator.WASHING_MACHINE));
		threadWashingMachineDC.start();
		Thread threadWashingMachineWD = new Thread(new WashingMachineWD(fritzBoxInterface, Actuator.WASHING_MACHINE));
		threadWashingMachineWD.start();

		Thread threadScreenTimeOutWD = new Thread(screenTimeOutWD);
		threadScreenTimeOutWD.start();
	}
}

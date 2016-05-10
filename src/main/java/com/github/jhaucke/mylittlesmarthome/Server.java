package com.github.jhaucke.mylittlesmarthome;

import java.io.IOException;

import com.github.jhaucke.fritzboxconnector.FritzBoxConnector;
import com.github.jhaucke.fritzboxconnector.HttpInterface;
import com.github.jhaucke.mylittlesmarthome.database.constants.Actuator;
import com.github.jhaucke.mylittlesmarthome.datacollector.WashingMachineDC;
import com.github.jhaucke.mylittlesmarthome.mqtt.MqttPublisher;
import com.github.jhaucke.mylittlesmarthome.watchdog.WashingMachineWD;

/**
 * The entry point of my little smarthome.
 */
public class Server {
	public static void main(String[] args) throws IOException {
		FritzBoxConnector fritzBoxConnector = null;

		if (args.length == 2) {
			fritzBoxConnector = new FritzBoxConnector(args[0], args[1], null);
		} else if (args.length == 3) {
			fritzBoxConnector = new FritzBoxConnector(args[0], args[1], args[2]);
		}

		HttpInterface httpInterface = fritzBoxConnector.getHttpInterface();

		Thread threadWashingMachineDC = new Thread(new WashingMachineDC(httpInterface, Actuator.WASHING_MACHINE));
		threadWashingMachineDC.start();
		Thread threadWashingMachineWD = new Thread(new WashingMachineWD(httpInterface, Actuator.WASHING_MACHINE));
		threadWashingMachineWD.start();

		MqttPublisher.sendMessage("smarthome/server/info", "Server started");
	}
}

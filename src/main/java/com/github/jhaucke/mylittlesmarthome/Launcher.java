package com.github.jhaucke.mylittlesmarthome;

import java.io.IOException;

import com.github.jhaucke.fritzboxconnector.FritzBoxConnector;
import com.github.jhaucke.fritzboxconnector.HttpInterface;
import com.github.jhaucke.mylittlesmarthome.database.constants.Actuator;
import com.github.jhaucke.mylittlesmarthome.datacollector.WashingMachineDC;
import com.github.jhaucke.mylittlesmarthome.gui.MainView;
import com.github.jhaucke.mylittlesmarthome.gui.MainViewModel;
import com.github.jhaucke.mylittlesmarthome.mqtt.MqttPublisher;
import com.github.jhaucke.mylittlesmarthome.watchdog.WashingMachineWD;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of my little smarthome.
 */
public class Launcher extends Application {

	public static void main(String[] args) throws IOException {

		if (args.length > 1) {
			FritzBoxConnector fritzBoxConnector = null;

			if (args.length == 2) {
				fritzBoxConnector = new FritzBoxConnector(args[0], args[1], null);
			} else if (args.length == 3) {
				fritzBoxConnector = new FritzBoxConnector(args[0], args[1], args[2]);
			}

			HttpInterface fritzBoxInterface = fritzBoxConnector.getHttpInterface();

			Thread threadWashingMachineDC = new Thread(
					new WashingMachineDC(fritzBoxInterface, Actuator.WASHING_MACHINE));
			threadWashingMachineDC.start();
			Thread threadWashingMachineWD = new Thread(
					new WashingMachineWD(fritzBoxInterface, Actuator.WASHING_MACHINE));
			threadWashingMachineWD.start();
		}

		MqttPublisher.sendMessage("smarthome/server/info", "my littlesmarthome started");

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("My little smarthome");

		ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}
}

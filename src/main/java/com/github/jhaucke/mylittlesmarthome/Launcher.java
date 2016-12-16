package com.github.jhaucke.mylittlesmarthome;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jhaucke.mylittlesmarthome.gui.MainView;
import com.github.jhaucke.mylittlesmarthome.gui.MainViewModel;
import com.github.jhaucke.mylittlesmarthome.module.PropertiesModule;
import com.github.jhaucke.mylittlesmarthome.mqtt.MqttPublisher;
import com.google.inject.Module;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of my little smarthome.
 */
public class Launcher extends MvvmfxGuiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

	public static void main(String[] args) throws IOException {

		MqttPublisher.sendMessage("smarthome/server/info", "my little smarthome started");

		launch(args);
	}

	@Override
	public void initGuiceModules(List<Module> modules) throws Exception {

		modules.add(new PropertiesModule());
	}

	@Override
	public void startMvvmfx(Stage stage) throws Exception {

		// start backend services
		getInjector().getInstance(Engine.class);

		stage.setTitle("My little smarthome");

		ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}
}

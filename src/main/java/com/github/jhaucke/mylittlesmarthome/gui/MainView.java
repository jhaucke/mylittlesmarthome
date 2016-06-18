package com.github.jhaucke.mylittlesmarthome.gui;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainView implements FxmlView<MainViewModel>, Initializable {

	@FXML
	private Label helloLabel;

	@InjectViewModel
	private MainViewModel viewModel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		helloLabel.textProperty().bind(viewModel.helloMessage());
	}

}
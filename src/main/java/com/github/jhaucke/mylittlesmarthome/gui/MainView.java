package com.github.jhaucke.mylittlesmarthome.gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.github.jhaucke.mylittlesmarthome.database.constants.ActuatorState;
import com.github.jhaucke.mylittlesmarthome.gui.wifi.WifiDialogView;
import com.github.jhaucke.mylittlesmarthome.gui.wifi.WifiDialogViewModel;
import com.github.jhaucke.mylittlesmarthome.service.raspiscreen.RaspiScreenService;
import com.github.jhaucke.mylittlesmarthome.watchdog.WashingMachineWD;
import com.google.inject.Inject;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * The dashboard of the smarthome app.
 */
public class MainView implements FxmlView<MainViewModel>, Initializable {

	private static final Effect parentEffect = new BoxBlur();

	@FXML
	private Pane mainPane;

	@FXML
	private Button lightSwitchBtn;

	@FXML
	private Button wifiBtn;

	@FXML
	private Button washerBtn;

	@FXML
	private Label timeLabel;

	@InjectViewModel
	private MainViewModel viewModel;

	@Inject
	private NotificationCenter notificationCenter;

	@Inject
	private RaspiScreenService raspiScreenService;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		notificationCenter.subscribe(WashingMachineWD.WASHER_STATE_CHANGED, (key, payload) -> {
			washerBtn.setGraphic(getWasherImageView((ActuatorState) payload[0]));
		});

		mainPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!raspiScreenService.isScreenOn()) {
					raspiScreenService.turnScreenOn();
					mouseEvent.consume();
				}
				raspiScreenService.setLastScreenAction(LocalDateTime.now());
			}
		});

		ImageView lightSwitchImageView = new ImageView(
				new Image(getClass().getResource("/images/hue/hue_bulb.png").toExternalForm()));
		lightSwitchImageView.setPreserveRatio(true);
		lightSwitchImageView.setFitHeight(140);
		lightSwitchBtn.setGraphic(lightSwitchImageView);

		ImageView wifiImageView = new ImageView(
				new Image(getClass().getResource("/images/wifi/wifi.png").toExternalForm()));
		wifiImageView.setPreserveRatio(true);
		wifiImageView.setFitWidth(150);
		wifiBtn.setGraphic(wifiImageView);

		washerBtn.setGraphic(getWasherImageView(ActuatorState.OFF));

		bindToTime();
	}

	private void initParentEffects(final Popup popup) {
		popup.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasShowing,
					Boolean isShowing) {
				wifiBtn.getScene().getRoot().setEffect(isShowing ? parentEffect : null);
			}
		});
	}

	private ImageView getWasherImageView(ActuatorState state) {

		Image image = null;

		switch (state) {
		case OFF:
			image = new Image(getClass().getResource("/images/washer/washing_machine_off.png").toExternalForm());
			break;
		case ON:
			image = new Image(getClass().getResource("/images/washer/washing_machine_on.png").toExternalForm());
			break;
		case ACTIVE:
			image = new Image(getClass().getResource("/images/washer/washing_machine_active.png").toExternalForm());
			break;
		case FINISHED:
			image = new Image(getClass().getResource("/images/washer/washing_machine_finished.png").toExternalForm());
			break;
		default:
			break;
		}

		ImageView washerImageView = new ImageView(image);
		washerImageView.setPreserveRatio(true);
		washerImageView.setFitWidth(140);
		return washerImageView;
	}

	@FXML
	void onLightSwitchBtnClicked(MouseEvent event) {

		viewModel.switchLight();
	}

	@FXML
	void onWiFiBtnClicked(MouseEvent event) {

		ViewTuple<WifiDialogView, WifiDialogViewModel> viewTuple = FluentViewLoader.fxmlView(WifiDialogView.class)
				.load();
		Popup popup = new Popup();
		Parent wifiDialog = viewTuple.getView();

		popup.getContent().add(wifiDialog);
		initParentEffects(popup);
		popup.show(wifiBtn.getScene().getWindow());
	}

	@FXML
	void onWasherBtnClicked(MouseEvent event) {
	}

	private void bindToTime() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						event -> timeLabel.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}

package com.github.jhaucke.mylittlesmarthome.gui.wifi;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.saxsys.mvvmfx.FxmlView;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * The dialog for guest wifi presentation.
 */
public class WifiDialogView implements FxmlView<WifiDialogViewModel>, Initializable {

	@FXML
	private Pane wifiDialog;

	@FXML
	private Button windowsBtn;

	@FXML
	private Button androidBtn;

	@FXML
	private Button iosBtn;

	@FXML
	private Label wifiKey;

	@FXML
	private Button popupCloseBtn;

	private final String ssid;
	private final String key;

	private ImageView closeImage;

	private ImageView windowsImage;
	private ImageView windowsQr;
	private RotateTransition windowsRotator;
	private boolean isWindowsRotated;

	private ImageView androidImage;
	private ImageView androidQr;
	private RotateTransition androidRotator;
	private boolean isAndroidRotated;

	private ImageView iosImage;
	private ImageView iosQr;
	private RotateTransition iosRotator;
	private boolean isIosRotated;

	@Inject
	public WifiDialogView(@Named("ssid") String ssid, @Named("key") String key) {
		super();

		this.ssid = ssid;
		this.key = key;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		closeImage = new ImageView(new Image(getClass().getResource("/images/wifi/close.png").toExternalForm()));
		closeImage.setPreserveRatio(true);
		closeImage.setFitHeight(30);
		popupCloseBtn.setGraphic(closeImage);

		windowsImage = new ImageView(new Image(getClass().getResource("/images/wifi/windows.png").toExternalForm()));
		windowsImage.setPreserveRatio(true);
		windowsImage.setFitHeight(130);
		windowsQr = new ImageView(new Image(getClass().getResource("/images/wifi/windowsQR.png").toExternalForm()));
		windowsQr.setPreserveRatio(true);
		windowsQr.setFitHeight(150);
		windowsRotator = createRotator(windowsBtn);
		windowsBtn.setGraphic(windowsImage);

		androidImage = new ImageView(new Image(getClass().getResource("/images/wifi/android.png").toExternalForm()));
		androidImage.setPreserveRatio(true);
		androidImage.setFitWidth(130);
		androidQr = new ImageView(new Image(getClass().getResource("/images/wifi/androidQR.png").toExternalForm()));
		androidQr.setPreserveRatio(true);
		androidQr.setFitHeight(150);
		androidRotator = createRotator(androidBtn);
		androidBtn.setGraphic(androidImage);

		iosImage = new ImageView(new Image(getClass().getResource("/images/wifi/ios.png").toExternalForm()));
		iosImage.setPreserveRatio(true);
		iosImage.setFitWidth(130);
		iosQr = new ImageView(new Image(getClass().getResource("/images/wifi/iosQR.png").toExternalForm()));
		iosQr.setPreserveRatio(true);
		iosQr.setFitHeight(150);
		iosRotator = createRotator(iosBtn);
		iosBtn.setGraphic(iosImage);

		wifiKey.setText("SSID: " + ssid + System.lineSeparator() + "Key: " + key);
	}

	@FXML
	void onWindowsBtnClicked(MouseEvent event) {

		if (!isWindowsRotated) {
			doBlink(windowsRotator, windowsQr, windowsBtn);
			isWindowsRotated = true;
		}
	}

	@FXML
	void onAndroidBtnClicked(MouseEvent event) {

		if (!isAndroidRotated) {
			doBlink(androidRotator, androidQr, androidBtn);
			isAndroidRotated = true;
		}
	}

	@FXML
	void onIosBtnClicked(MouseEvent event) {

		if (!isIosRotated) {
			doBlink(iosRotator, iosQr, iosBtn);
			isIosRotated = true;
		}
	}

	@FXML
	void onPopupCloseBtnClicked(MouseEvent event) {

		Popup popup = (Popup) popupCloseBtn.getScene().getWindow();
		popup.hide();
	}

	private RotateTransition createRotator(Node node) {

		RotateTransition rotator = new RotateTransition(Duration.millis(1000), node);
		rotator.setAxis(Rotate.Y_AXIS);
		rotator.setFromAngle(0);
		rotator.setToAngle(90);
		rotator.setInterpolator(Interpolator.LINEAR);
		rotator.setCycleCount(1);

		return rotator;
	}

	private void doBlink(RotateTransition rotator, ImageView backspinImage, Button btnToSpin) {
		rotator.play();
		rotator.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				rotator.setOnFinished(null);
				btnToSpin.setGraphic(backspinImage);
				rotator.setRate(-1);
				rotator.play();
			}
		});
	}
}

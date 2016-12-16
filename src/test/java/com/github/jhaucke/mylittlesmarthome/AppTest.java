package com.github.jhaucke.mylittlesmarthome;

import org.junit.Ignore;
import org.junit.Test;

import com.github.jhaucke.mylittlesmarthome.database.constants.ActuatorState;
import com.github.jhaucke.mylittlesmarthome.mqtt.MqttPublisher;

/**
 * Unit test.
 */
public class AppTest {
	
	@Ignore
	@Test
	public void testMqtt() {
		MqttPublisher.sendMessage("smarthome/devices/washingmachine/state", ActuatorState.OFF.toString());
	}
}

package com.github.jhaucke.mylittlesmarthome.module;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Module to load the properties file.
 */
public class PropertiesModule extends AbstractModule {

	private final Logger logger;

	public PropertiesModule() {
		super();

		logger = LoggerFactory.getLogger(PropertiesModule.class);
	}

	@Override
	protected void configure() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("mylittlesmarthome.properties"));
			Names.bindProperties(binder(), properties);
		} catch (FileNotFoundException e) {
			logger.error("The configuration file mylittlesmarthome.properties can not be found", e);
		} catch (IOException e) {
			logger.error("I/O Exception during loading configuration", e);
		}
	}
}

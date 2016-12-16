package com.github.jhaucke.mylittlesmarthome.service.hue;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jhaucke.mylittlesmarthome.service.hue.api.PhilipsHueAPI;
import com.github.jhaucke.mylittlesmarthome.service.hue.types.Light;
import com.github.jhaucke.mylittlesmarthome.service.hue.types.State;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The service class for the hue interaction.
 */
public class HueService implements Callback<List<State>> {

	private final Logger logger;

	private final String hueAuthorizedUser;

	@Inject
	public HueService(@Named("hueAuthorizedUser") String hueAuthorizedUser) {
		super();
		logger = LoggerFactory.getLogger(HueService.class);

		this.hueAuthorizedUser = hueAuthorizedUser;
	}

	public void switchLight(int lightId) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://philips-hue/api/" + hueAuthorizedUser + "/")
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		PhilipsHueAPI hueAPI = retrofit.create(PhilipsHueAPI.class);

		try {
			Call<Light> lightCall = hueAPI.getLight(lightId);
			Response<Light> lightResponse = lightCall.execute();

			State newState = new State();
			if (lightResponse.body().getState().getOn()) {
				newState.setOn(false);
			} else {
				newState.setOn(true);
				newState.setBri(230);
				newState.setHue(15342);
			}

			Call<List<State>> stateCall = hueAPI.setState(lightId, newState);
			stateCall.enqueue(this);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void onFailure(Call<List<State>> call, Throwable t) {
		logger.error(t.getMessage());
	}

	@Override
	public void onResponse(Call<List<State>> call, Response<List<State>> response) {
	}
}

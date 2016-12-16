package com.github.jhaucke.mylittlesmarthome.service.hue.api;

import java.util.List;

import com.github.jhaucke.mylittlesmarthome.service.hue.types.Light;
import com.github.jhaucke.mylittlesmarthome.service.hue.types.State;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The interface which defines the methods to communicate with the hue bridge.
 */
public interface PhilipsHueAPI {

	@GET("lights/{id}/")
	Call<Light> getLight(@Path("id") int lightId);

	@PUT("lights/{id}/state")
	Call<List<State>> setState(@Path("id") int lightId, @Body State state);
}

package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;

import java.io.IOException;

public class JokeAdapter extends com.google.gson.TypeAdapter<Joke> {
	@Override
	public void write(JsonWriter out, Joke value) throws IOException {

	}

	@Override
	public Joke read(JsonReader in) throws IOException {
		Gson gson = new Gson();
		Joke j  = gson.fromJson(in, Joke.class);
		return  j;
	}
}


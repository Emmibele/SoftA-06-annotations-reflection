package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;

import java.io.IOException;
import java.util.List;

public class JokeListAdapter extends com.google.gson.TypeAdapter<Joke[]>{

	private Gson gson;
	public  JokeListAdapter(){
		gson = new Gson();
	}

	@Override
	public void write(JsonWriter out, Joke[] value) throws IOException {

	}

	@Override
	public Joke[] read(JsonReader in) throws IOException {
		Joke[] result = null;
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()){
				case "total":
					in.skipValue();
				case "result":
					result = gson.fromJson(in, Joke[].class);
			}
		}
		return result;
	}
}

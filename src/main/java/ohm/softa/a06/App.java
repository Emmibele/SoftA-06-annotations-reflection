package ohm.softa.a06;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;
import java.util.List;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) {
		// TODO fetch a random joke and print it to STDOUT
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(Joke.class, new JokeAdapter())
			.create();

		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("https://api.chucknorris.io/")
			.build();

		CNJDBApi CNJDBApi = retrofit.create(CNJDBApi.class);
		Call<Joke> jokeJson = CNJDBApi.getRandomJoke();
		String s;
		try{

			s = ((Joke)jokeJson.execute().body()).getContent();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println(s);
	}

}

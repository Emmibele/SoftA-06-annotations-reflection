package ohm.softa.a06.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.CNJDBApi;
import ohm.softa.a06.JokeAdapter;
import ohm.softa.a06.model.Joke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
class CNJDBTests {

	private static final Logger logger = LogManager.getLogger(CNJDBTests.class);
	private static final int REQUEST_COUNT = 100;

	private Gson gson;
	private Retrofit retrofit;
	private CNJDBApi cnjdbApi;

	@BeforeEach
	void init() {
		gson = new GsonBuilder()
			.registerTypeAdapter(Joke.class, new JokeAdapter())
			.registerTypeAdapter(Joke[].class, new JokeAdapter())
			.create();

		retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("https://api.chucknorris.io/")
			.build();

		cnjdbApi = retrofit.create(CNJDBApi.class);

	}

	@Test
	void testCollision() throws IOException {
		Set<String> jokeNumbers = new HashSet<>();
		int requests = 0;
		boolean collision = false;

		while (requests++ < REQUEST_COUNT) {

			Call<Joke> jokeJson = cnjdbApi.getRandomJoke();

			// Perform a synchronous request

			Response<Joke> jokeResponse = jokeJson.execute();

			// Extract object

			Joke j = jokeResponse.body();

			if (jokeNumbers.contains(j.getIdentifier())) {
				logger.info(String.format("Collision at joke %s", j.getIdentifier()));
				collision = true;
				break;
			}

			jokeNumbers.add(j.getIdentifier());
			logger.info(j.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}

	@Test
	void testRandomJoke() throws IOException {
		logger.info("Test for fetching single random joke");

		Call<Joke> jokeJson = cnjdbApi.getRandomJoke();
		Response<Joke> jokeResponse = jokeJson.execute();
		Joke j = jokeResponse.body();

		assertNotNull(j);
		assertNotNull(j.getContent());
		assertNotNull(j.getIdentifier());
	}

	@Test
	void testJokeByQuery(){
		logger.info("Test for fetching jokes by query");

		Call<Joke[]> callJokes = cnjdbApi.getJokeBySearch_2("horse");


	}
}

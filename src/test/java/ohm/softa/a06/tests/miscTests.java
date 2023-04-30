package ohm.softa.a06.tests;

import com.google.gson.Gson;
import ohm.softa.a06.model.Joke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class miscTests {
	private final Logger logger = LogManager.getLogger();

	@Test
	void TestDeserialize(){
		logger.info("Testing deserialization of json string to joke object");
		String json = "{\"categories\":[],\"created_at\":\"2020-01-05 13:42:26.194739\",\"icon_url\":\"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\"id\":\"MKQkhlkaTwCOjzkOA4Yuwg\",\"updated_at\":\"2020-01-05 13:42:26.194739\",\"url\":\"https://api.chucknorris.io/jokes/MKQkhlkaTwCOjzkOA4Yuwg\",\"value\":\"Chuck Norris\"}";

		Gson gson = new Gson();
		Joke j  = gson.fromJson(json, Joke.class);

		assertNotNull(j);
		assertEquals(j.getIdentifier(), "MKQkhlkaTwCOjzkOA4Yuwg");
		assertEquals(j.getContent(), "Chuck Norris");
		assertEquals(j.getRubrics().stream().count(), 0);
	}
}

package ohm.softa.a06;


import ohm.softa.a06.model.Joke;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface CNJDBApi {
	@GET("jokes/random")
	public Call<Joke>  getRandomJoke();

	@GET("jokes/search?query={query}")
	public Call<Joke[]> getJokeBySearch(@Path("query") String query);


	@GET("jokes/search")
	public Call<Joke[]> getJokeBySearch_2(@Query("query") String query);

}


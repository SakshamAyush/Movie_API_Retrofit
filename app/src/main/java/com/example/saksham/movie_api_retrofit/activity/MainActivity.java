//get API key from "https://www.themoviedb.org/documentation/api"
//Add retrofit and gson in build gradle
//create activity,adapter,model and rest package
//Move MainActivity to activity package
//Add INTERNET permission in manifest
//Create Movies class in model to store the responses
//We create MovieResponse class for some extra responses like page number etc.
//To send network req. to API, we use the retrofit builder class and specify the base URL
//We create APIclient class under rest package
//Create APIinterface under rest package to define endpoints
//
//
//Create ListView to see fetched results
//Add RecyclerView in gradle
//Add Colors in colors.xml
//Create star.xml in res -> drawable
//Create a layout named list_item_movie under res -> layout.
//Adapter is used to bind view and data
//Create MoviesAdapter in adapter package
//Add recycler view in MainActivity
package com.example.saksham.movie_api_retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.example.saksham.movie_api_retrofit.R;
import com.example.saksham.movie_api_retrofit.adapter.MoviesAdapter;
import com.example.saksham.movie_api_retrofit.model.MovieResponse;
import com.example.saksham.movie_api_retrofit.model.Movies;
import com.example.saksham.movie_api_retrofit.rest.APIclient;
import com.example.saksham.movie_api_retrofit.rest.APIinterface;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "559cc620f8fc321897951690da022cc9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY.isEmpty())
        {
            Toast.makeText(this, "Obtain API key first",Toast.LENGTH_SHORT).show();
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        APIinterface apiservice = APIclient.getClient().create(APIinterface.class);
        retrofit2.Call<MovieResponse> call = apiservice.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                List<Movies> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

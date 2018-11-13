//get API key from "https://www.themoviedb.org/documentation/api"
//Add retrofit and gson in build gradle
//create activity,adapter,model and rest package
//Move MainActivity to activity package
//Add INTERNET permission in manifest
//Create Movies class in model to store the responses
//We create MovieResponse class for some extra responses like page number etc.
//To send network req. to API, we use the retrofit builder class and specify the base URL
//We create APIclient class under rest
package com.example.saksham.movie_api_retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.saksham.movie_api_retrofit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

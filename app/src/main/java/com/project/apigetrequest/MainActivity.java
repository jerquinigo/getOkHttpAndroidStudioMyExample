package com.project.apigetrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OkHttpClient client = new OkHttpClient();
        //need to put a url here. this is for giphy app. Not pushing my api key
        Request request = new Request.Builder().url("will need to put a url here").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String results = response.body().string();
                    JSONObject jsnobject = null;
                    try {
                        jsnobject = new JSONObject(results);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = jsnobject.getJSONArray("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for(int i = 0; i < jsonArray.length(); i++) {
                        try {
                            System.out.println(jsonArray.getJSONObject(i).get("url"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //System.out.println(jsonArray);
                }

            }
        });
    }
}
package com.rio.connecttoapiexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    ProgressBar pbMain;
    ArrayList<Team> listTeam;
    ListTeamAdapter listTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rv_main);
        pbMain = findViewById(R.id.pb_main);
        listTeam = new ArrayList<>();

        listTeamAdapter = new ListTeamAdapter(this);
        listTeamAdapter.setListTeam(listTeam);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(listTeamAdapter);

        loadData();
    }

    private void loadData() {
        URL url = Api.getListTeam();
        Log.e("url", url.toString());
        new TeamAsyncTask().execute(url);
    }

    private class TeamAsyncTask extends AsyncTask<URL, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbMain.setVisibility(View.VISIBLE);
            rvMain.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                result = Network.getFromNetwork(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("result", result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            rvMain.setVisibility(View.VISIBLE);
            pbMain.setVisibility(View.INVISIBLE);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("teams");

                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    Team team = new Team(object);
                    listTeam.add(team);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listTeamAdapter.setListTeam(listTeam);
        }


    }
}

package com.rio.connecttoapiexample;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Api {
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String LOOKUP_ALL_TEAM = "lookup_all_teams.php";
    private static final String ID = "id";
    private static final String PREMIERE_LEAGUE_ID = "4328";

    public static URL getListTeam(){
        // https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(API_KEY)
                .appendPath(LOOKUP_ALL_TEAM)
                .appendQueryParameter(ID, PREMIERE_LEAGUE_ID)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }



}

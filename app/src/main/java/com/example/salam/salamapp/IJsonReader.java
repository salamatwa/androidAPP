package com.example.salam.salamapp;
import android.content.Context;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
/**
 * Created by salam on 12/3/2017.
 */

public class IJsonReader {


    Context context;

    IJsonReader(Context context){
        this.context = context;
    }

    public String parse() {


        InputStream file = context.getResources().openRawResource(R.raw.search);
        byte[] formArray = new byte[0];

        try {
            formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(formArray);
    }

    public String parse(String url){
        BufferedReader bufferedReader = null ;
        try{
            URL urll = new URL(String.format(url));
            HttpURLConnection connection = (HttpURLConnection)urll.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5*1000);
            connection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();
            String line = null ;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line + "\n");
            }

            return builder.toString();

        }catch (MalformedURLException ex){
            return "";

        }catch(IOException ioe){
            return "";
        }


    }


    public ArrayList<ListItem> parseJson(String json){
        ArrayList<ListItem> items = null;
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray products = jo.getJSONArray("products");
            items = new ArrayList<>();
            for(int i = 0 ; i < products.length() ; i++){


                ListItem listItem = new ListItem();
                listItem.setName(String.valueOf(products.getJSONObject(i).get("name")));
                listItem.setImg(String.valueOf(products.getJSONObject(i).get("img")));
                items.add(listItem);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }
}

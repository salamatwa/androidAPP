package com.example.salam.salamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LauncherActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ListItem> items ;
    String text;
    //recycle view
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    IJsonReader api ;
    final String url = "https://platform.shopyourway.com/products/search?q=shirt&" +
            "page=1&token=0_20975_253402300799_1_39c0fd9abf524b96985688e78892212c05f34203a46ac36a4117f211b41c7f5d&hash=1" +
            "6eba7802b35f6cb1b03dbf6262d4db0808f437a14f070019a6fa98da45b3d90\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.click);
        recyclerView = (RecyclerView) findViewById(R.id.rycycle_view);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        this.api = new IJsonReader(getApplicationContext());



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = ((EditText) findViewById(R.id.edit)).getText().toString();
                //data = Collections.nCopies(MAX_RESULTS, text);

                parseJSON();

                show(items);

            }
        });


    }

    private void parseJSON(){

        //IJsonReader iJsonReader = new IJsonReader(getApplicationContext());
        //String json = iJsonReader.parse();



        //JSON Object reader from API!!
        //String json = iJsonReader.parse(url);


        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
        getDataAsyncTask.execute(MainActivity.this.api);

       /* try {
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
        }*/
    }

    public void setData(ArrayList<ListItem> items){
        this.items.addAll(items);
        this.recycleViewAdapter.notifyDataSetChanged();
    }

    private void show(List<ListItem> data) {
        if (recycleViewAdapter == null) {
            recycleViewAdapter = new RecycleViewAdapter(data);
            recyclerView.setAdapter(recycleViewAdapter);
            recyclerView.setLayoutManager(layoutManager);


        } else {
            recycleViewAdapter.setData(data);
            recycleViewAdapter.notifyDataSetChanged();
        }
    }

    class GetDataAsyncTask extends AsyncTask {

        @Override
        protected void onPostExecute(Object response) {
            setData((ArrayList<ListItem>) response);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            IJsonReader api = (IJsonReader) params[0];

            String json = api.parse(url);
            ArrayList<ListItem> response = api.parseJson(json);
            //ScrollingActivity.this.pageNumber++;
            return response;


        }
    };
}

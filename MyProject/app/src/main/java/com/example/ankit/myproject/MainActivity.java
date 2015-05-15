package com.example.ankit.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    JSONObject jsonobject;
    JSONArray jsonarray;
    ProgressDialog mProgressDialog;


    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        new DownloadJSON().execute();
        Cursor myCursor = mydb.getAllMessage();
        ListView list = (ListView) findViewById(R.id.listview);
        MyCursorAdaptor myAdaptor = new MyCursorAdaptor(this,myCursor);
        list.setAdapter(myAdaptor);
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {


            try {
                InputStream is = getAssets().open("foo.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, "UTF-8");
                jsonobject = new JSONObject(json);

                jsonarray = jsonobject.getJSONArray("chats");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);
                    Message message = new Message();
                    message.setId(jsonobject.optString("msg_id"));
                    message.setData(jsonobject.optString("msg_data"));
                    message.setTimestamp(jsonobject.optString("timestamp"));
                    message.setType(jsonobject.optString("msg_type"));

                    mydb.insertMessage(message.getId(), message.getType(), message.getData(), message.getTimestamp());

                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

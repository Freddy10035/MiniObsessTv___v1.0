package com.dex.miniobsesstv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dex.miniobsesstv.adapters.ChannelAdapter;
import com.dex.miniobsesstv.models.Channel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    RecyclerView bigSliderList;
    ChannelAdapter bigSliderAdapter;
    List<Channel> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelList= new ArrayList<>();

        bigSliderList.findViewById(R.id.big_slider_list);
        bigSliderList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        bigSliderAdapter = new ChannelAdapter(channelList,"slider");
        bigSliderList.setAdapter(bigSliderAdapter);

        getChannelData("http://192.168.1.10/livetv-streaming-php-backend/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&cat=News");

    }

    public void getChannelData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: " + response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());

            }
        });
      queue.add(objectRequest);
    }
}
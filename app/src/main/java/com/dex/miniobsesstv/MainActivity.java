package com.dex.miniobsesstv;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dex.miniobsesstv.adapters.ChannelAdapter;
import com.dex.miniobsesstv.models.Channel;
import com.dex.miniobsesstv.services.ChannelDataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    RecyclerView bigSliderList, newsChannelList;
    ChannelAdapter bigSliderAdapter, newsChannelAdapter;
    List<Channel> channelList, newsChannels;
    ChannelDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelList = new ArrayList<>();
        service = new ChannelDataService(this);


        bigSliderList = findViewById(R.id.big_slider_list);
        bigSliderList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bigSliderAdapter = new ChannelAdapter(channelList, "slider");
        bigSliderList.setAdapter(bigSliderAdapter);

        getSliderData("http://192.168.1.10/livetv-streaming-php-backend/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&channels=all");
        getNewsChannels("http://192.168.1.10/livetv-streaming-php-backend/api.php?key=1A4mgi2rBHCJdqggsYVx&id=1&cat=News");

    }

    public void getSliderData(String url) {

        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        //Log.d(TAG, "onResponse: " + channelData.getString("name"));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));

                        channelList.add(c);
                        bigSliderAdapter.notifyDataSetChanged();

                        //Log.d(TAG, "onResponse: " + c);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onErrorResponse: " + error);

            }
        });

    }

    public void getNewsChannels(String url){

        newsChannelList = findViewById(R.id.news_channel_list);
        newsChannels = new ArrayList<>();
        newsChannelList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        newsChannelAdapter = new ChannelAdapter(newsChannels, "category");
        newsChannelList.setAdapter(newsChannelAdapter);

        service.getChannelData(url, new ChannelDataService.OnDataResponse() {
            @Override
            public void onResponse(JSONObject response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject channelData = response.getJSONObject(String.valueOf(i));
                        //Log.d(TAG, "onResponse: " + channelData.getString("name"));
                        Channel c = new Channel();
                        c.setId(channelData.getInt("id"));
                        c.setName(channelData.getString("name"));
                        c.setDescription(channelData.getString("description"));
                        c.setThumbnail(channelData.getString("thumbnail"));
                        c.setLive_url(channelData.getString("live_url"));
                        c.setFacebook(channelData.getString("facebook"));
                        c.setTwitter(channelData.getString("twitter"));
                        c.setYoutube(channelData.getString("youtube"));
                        c.setWebsite(channelData.getString("website"));
                        c.setCategory(channelData.getString("category"));

                        newsChannels.add(c);
                        newsChannelAdapter.notifyDataSetChanged();

                        //Log.d(TAG, "onResponse: " + c);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);

            }
        });

    }
}
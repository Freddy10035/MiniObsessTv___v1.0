package com.dex.miniobsesstv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dex.miniobsesstv.R;
import com.dex.miniobsesstv.models.Channel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    List<Channel> channels;
    String type;

    public ChannelAdapter(List<Channel> channels, String type) {
        this.channels = channels;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        if(type.equals("slider")){

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_slider_view,parent,false);

        }else{

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_cat_view,parent,false);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       /* if (holder.channelName != null && channels.get(position) != null) {
            holder.channelName.setText(channels.get(position).getName());
        }*/

        holder.channelName.setText(channels.get(position).getName());
        Picasso.get().load(channels.get(position).getThumbnail()).into(holder.channelImage);

    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView channelImage;
        TextView channelName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            channelImage = itemView.findViewById(R.id.channelThumbnail);
            channelName = itemView.findViewById(R.id.channelName);
        }
    }

}

package com.example.shivam97.eventos.mainFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam97.eventos.eventClasses.EventDetails;
import com.example.shivam97.eventos.R;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.RecyclerViewHolder> {
    private ArrayList<String> titles, keys;
    private ArrayList<Bitmap> imgs;
    private Context ctx;


    EventsAdapter(Context c, ArrayList<String> titles, ArrayList<Bitmap> imgs, ArrayList<String> keys) {
        ctx = c;
        this.titles = titles;
        this.imgs = imgs;
        this.keys = keys;

    }

    public void updateData(ArrayList<String> titles, ArrayList<Bitmap> imgs, ArrayList<String> keys) {
        this.titles = titles;
        this.imgs = imgs;
        this.keys = keys;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.event_card, parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        holder.populate(titles.get(position), imgs.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, EventDetails.class);
                i.putExtra("key", keys.get(holder.getAdapterPosition()));
                i.putExtra("pos", holder.getAdapterPosition());
                ctx.startActivity(i);

            }
        });
    }


    @Override
    public int getItemCount() {
        if (titles != null)
            return titles.size();
        else
            return 0;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        View mview;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        void populate(String s1, Bitmap s2) {
            TextView title = mview.findViewById(R.id.event_name);
            ImageView imageView = mview.findViewById(R.id.event_image);
            title.setText(s1);
            imageView.setImageBitmap(s2);


        }
    }
}





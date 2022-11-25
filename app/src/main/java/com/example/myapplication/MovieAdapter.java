package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MovieAdapter implements ListAdapter {
    Context context;
    ArrayList<MovieModel> arrayList;
    String user;
    String pass;
    public MovieAdapter(Context context, ArrayList<MovieModel> list, String user, String pass){
        this.context = context;
        this.arrayList = list;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieModel movie = arrayList.get(position);
        FragmentHome fragmentHome = new FragmentHome();
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.movie_row, null);
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MainActivity.class);
                v.getContext().startActivity(intent);
            });
            TextView tittle = convertView.findViewById(R.id.movie_title);
            ImageView imageView = convertView.findViewById(R.id.thumbnail);
            tittle.setText(movie.getName());
            Picasso.with(context)
                    .load(movie.thumbnail)
                    .into(imageView);
        }
        return convertView;
    }

    /*protected void (String user, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JSONObject postData = new JSONObject();
        try {
            postData.put("user", user);
            postData.put("pass", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, postData, response -> {
            try {
                if(response.getString("status").equals("Sucesso")){
                    Intent intent = new Intent(getActivity(), VideosActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("pass", password);
                    startActivity(intent);
                    System.out.println("granted");
                }else{
                    System.out.println("invalid login");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println(error.toString()));
        requestQueue.add(jsonObjectRequest);
    }*/

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }


}

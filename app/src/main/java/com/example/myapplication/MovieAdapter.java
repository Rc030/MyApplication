package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter implements ListAdapter, Filterable {
    Context context;
    ArrayList<MovieModel> arrayList;
    ArrayList<MovieModel> orig;
    String user;
    String pass;
    public MovieAdapter(Context context, ArrayList<MovieModel> list, String user, String pass){
        super();
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<MovieModel> results = new ArrayList<>();
                System.out.println("PESQUISA " + constraint);

                if (orig == null) {
                    orig = arrayList;
                }
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (MovieModel g : orig) {
                            if (g.getName().toLowerCase().contains(constraint.toString())) {
                                results.add(g);
                                System.out.println("funciona: " + g.getName());
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                arrayList = (ArrayList<MovieModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieModel movie = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.movie_row, null);
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayActivity.class);
                intent.putExtra("user", this.user);
                intent.putExtra("pass", this.pass);
                intent.putExtra("movieId", Integer.toString(movie.getId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


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

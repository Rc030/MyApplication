package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        movieListRequest("e");
        return view;
    }

    protected void movieListRequest(String search){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JSONObject postData = new JSONObject();
        try {
            postData.put("test", "test");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://34.175.83.209:8080/search/" + search, postData, response -> {
            try {
                ArrayList<MovieModel> arrayList = new ArrayList<>();
                final ListView list = view.findViewById(R.id.list);
                for(int i=0; i<response.getJSONArray("movies").length(); i++){
                    JSONObject jo = response.getJSONArray("movies").getJSONObject(i);
                    arrayList.add(new MovieModel(jo.getString("name"), jo.getInt("id"), "http://34.175.83.209:8080/download/thumbnail/" + jo.getInt("id")));
                }
                MovieAdapter movieAdapter = new MovieAdapter(getActivity().getApplicationContext(), arrayList);
                list.setAdapter(movieAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println(error.toString()));
        requestQueue.add(jsonObjectRequest);
    }

}

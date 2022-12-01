package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements SearchView.OnQueryTextListener{
    View view;
    private SearchView searchView;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        movieListRequest("");
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

        @SuppressLint("CutPasteId") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://34.175.83.209:8080/search/all" + search, postData, response -> {
            try {
                ArrayList<MovieModel> arrayList = new ArrayList<>();
                searchView = view.findViewById(R.id.search);
                listView = view.findViewById(R.id.list);
                final ListView list = view.findViewById(R.id.list);
                for(int i=0; i<response.getJSONArray("movies").length(); i++){
                    JSONObject jo = response.getJSONArray("movies").getJSONObject(i);
                    arrayList.add(new MovieModel(jo.getString("name"), jo.getInt("id"), "http://34.175.83.209:8080/download/thumbnail/" + jo.getInt("id")));
                }
                MovieAdapter movieAdapter = new MovieAdapter(getActivity().getApplicationContext(), arrayList, getArguments().getString("user"), getArguments().getString("pass"));
                list.setAdapter(movieAdapter);
                listView.setTextFilterEnabled(true);
                setupSearchView();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println(error.toString()));
        requestQueue.add(jsonObjectRequest);
    }

    private void setupSearchView()
    {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText);
        }
        return true;
    }
}

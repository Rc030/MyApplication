package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class FragmentHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        final ListView list = view.findViewById(R.id.list);
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        arrayList.add(new MovieModel("Test", 42, "https://i.imgur.com/DvpvklR.png"));
        arrayList.add(new MovieModel("Test4", 42, "https://i.imgur.com/DvpvklR.png"));
        arrayList.add(new MovieModel("Test8", 42, "https://i.imgur.com/DvpvklR.png"));

        MovieAdapter movieAdapter = new MovieAdapter(getActivity().getApplicationContext(), arrayList);
        list.setAdapter(movieAdapter);
        return view;
    }
}

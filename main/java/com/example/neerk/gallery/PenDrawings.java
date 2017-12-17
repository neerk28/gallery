package com.example.neerk.gallery;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by neerk on 16/12/17.
 */

public class PenDrawings extends Fragment {
    final Integer[] imageList= {R.drawable.taj_mahal,R.drawable.boy_girl};
    private String[] imageName ={"Symbol of Love","Cute Tiny Couple"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pen, container, false);
        ListView listView =(ListView) view.findViewById(R.id.listView);
        final Integer[] imageList= {R.drawable.taj_mahal,R.drawable.boy_girl};
        ImageAdapter imageAdapter= new ImageAdapter(getActivity(),imageList);
        listView.setAdapter(imageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast= Toast.makeText(getActivity(),imageName[i], Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }
}

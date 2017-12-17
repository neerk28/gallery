package com.example.neerk.gallery;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by neerk on 16/12/17.
 */

public class PencilDrawings extends Fragment {

    private Integer[] imageList= {R.drawable.frozen_sisters,R.drawable.fairy_rapunzel};
    private String[] imageName ={"Elsa and Anna","Rapunzel"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pencil, container, false);
        ListView listView =(ListView) view.findViewById(R.id.listView);
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

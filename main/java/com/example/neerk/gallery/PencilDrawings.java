package com.example.neerk.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
                Toast toast= Toast.makeText(getActivity(),"Downloading...", Toast.LENGTH_SHORT);
                toast.show();
             //   FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
             //   final int position =i;
             //   fab.setOnClickListener(new View.OnClickListener() {
             //       @Override
             //       public void onClick(View view) {

                        Bitmap image = BitmapFactory.decodeResource(getResources(),imageList[i]);
                        File path = Environment.getExternalStorageDirectory();
                        File dir = new File(path+"/gallery/");
                        dir.mkdirs();

                        File file= new File(dir,imageName[i]+".png");
                        OutputStream out = null;
                        try {
                            out= new FileOutputStream(file);
                            image.compress(Bitmap.CompressFormat.PNG,100,out);
                            out.flush();
                            out.close();
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                toast= Toast.makeText(getActivity(),"Download complete", Toast.LENGTH_SHORT);
                toast.show();
                        // Intent googleplusIntent = getOpenGPlusIntent(Main2Activity.this);
                        // startActivity(googleplusIntent);
                        //  Snackbar.make(view, "Wait for this feature!", Snackbar.LENGTH_LONG)
                        //          .setAction("Action", null).show();
                    }
                });



        return view;
    }
}

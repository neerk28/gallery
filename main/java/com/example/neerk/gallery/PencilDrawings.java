package com.example.neerk.gallery;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
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
    private ProgressDialog waitDialog;

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
                new ImageDownloader(i).execute();
            }
        });

        return view;
    }

    private class ImageDownloader extends AsyncTask<String,Void,Boolean>{
        private int position;

        public ImageDownloader(int i){
            this.position=i;
        }

        @Override
        protected void onPreExecute() {
            waitDialog= ProgressDialog.show(getContext(),"Please wait","Downloading image");
            waitDialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Boolean flag) {

            waitDialog.dismiss();

            if(flag){

                AlertDialog dialog = new AlertDialog.Builder(getContext()).setPositiveButton("OK",null).setTitle("Success").setMessage("\n\nDownload complete!").show();
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTextColor(Color.BLUE);
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                // override the text color of positive button
                positiveButton.setTextColor(getResources().getColor(android.R.color.black));
                Log.d("status:" +getStatus()," image: "+imageName[position]+".jpg");

            }else{

                AlertDialog dialog = new AlertDialog.Builder(getContext()).setPositiveButton("OK",null).setTitle("Error").setMessage("\n\nDownload Incomplete, Try again...").show();
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTextColor(Color.RED);
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                // override the text color of positive button
                positiveButton.setTextColor(getResources().getColor(android.R.color.black));

            }
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Bitmap image = BitmapFactory.decodeResource(getResources(),imageList[position]);
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File dir = new File(path+"/");
            dir.mkdirs();
            Log.d("imagename: "+imageList[position]," path: "+dir.getPath());
            File file= new File(dir,imageName[position]+".jpg");
            OutputStream out = null;
            try {
                out= new FileOutputStream(file);
                image.compress(Bitmap.CompressFormat.JPEG,100,out);
                out.flush();
                out.close();
                Log.d("status: complete"," image: "+imageName[position]+".jpg");

            } catch (java.io.IOException e) {
                e.printStackTrace();
                Log.d("status: incomplete"," image: "+imageName[position]+".jpg");
                return false;
            }
            return true;
        }

    }
}

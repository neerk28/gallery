package com.example.neerk.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Integer[] imageList= {R.drawable.frozen_sisters,R.drawable.taj_mahal,R.drawable.boy_girl,R.drawable.fairy_rapunzel};
        ImageAdapter imageAdapter= new ImageAdapter(this,imageList);

        GridView gridView =(GridView) this.findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast= Toast.makeText(ThirdActivity.this,""+i, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}

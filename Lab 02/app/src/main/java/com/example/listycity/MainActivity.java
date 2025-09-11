package com.example.listycity;

import android.graphics.pdf.models.ListItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;
    boolean isRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View v1 = findViewById(R.id.confirm_button);
        v1.setVisibility(View.INVISIBLE);
        TextView t1 = findViewById(R.id.text_field_1);
        t1.setVisibility(View.INVISIBLE);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Washington", "Berlin", "Moscow"};
        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);


        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityList.setSelector(android.R.color.darker_gray);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void confirmButton(View v){
        TextView t = findViewById(R.id.text_field_1);
        cityAdapter.add(t.getText().toString());
        t.setVisibility(View.INVISIBLE);
        v.setVisibility(View.INVISIBLE);

    }

    public void addButton(View v){
        TextView t = findViewById(R.id.text_field_1);
        t.setVisibility(View.VISIBLE);
        View v1 = findViewById(R.id.confirm_button);
        v1.setVisibility(View.VISIBLE);
    }

    public void removeButton(View v){

        if(cityList.getCheckedItemPosition() < 0){
            return;
        }
        else{
            ListView l1 = findViewById(R.id.city_list);
            datalist.remove(l1.getCheckedItemPosition());
            cityAdapter.notifyDataSetChanged();
            l1.clearChoices();
        }
    }

}
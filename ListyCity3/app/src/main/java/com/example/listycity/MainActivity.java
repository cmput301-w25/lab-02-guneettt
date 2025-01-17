package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView city_list;
    ArrayAdapter<String> cityAdapter;
    EditText city_edit_text;
    Button add_city, delete_city;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        String [] cities = {"EDM","PAR","VAN"};
        city_edit_text = findViewById(R.id.edt_city);
        add_city = findViewById(R.id.add_city_btn);
        city_list = findViewById(R.id.city_list);
        delete_city = findViewById(R.id.delete_city);
        dataList =  new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content,dataList);
        city_list.setAdapter(cityAdapter);

        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = city_edit_text.getText().toString();
                dataList.add(value);
                cityAdapter.notifyDataSetChanged();
                city_edit_text.setText("");
            }
        });
        city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                delete_city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}